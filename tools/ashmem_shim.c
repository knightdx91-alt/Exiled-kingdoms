/* ashmem shim: 2012 bionic has no dlopen-time libc here, so this .so is
   freestanding and talks to the kernel with raw int 0x80 syscalls.
   It intercepts open("/dev/ashmem") and hands back a plain tmpfile fd,
   and makes the ashmem ioctls succeed. */
#define SYS_open   5
#define SYS_ioctl  54
#define SYS_ftruncate 93
#define SYS_unlink 10

static inline int sys3(int n, long a, long b, long c) {
    int r;
    __asm__ volatile ("int $0x80" : "=a"(r) : "a"(n), "b"(a), "c"(b), "d"(c) : "memory");
    return r;
}

static int streq_ashmem(const char *p) {
    const char *q = "/dev/ashmem";
    while (*q) { if (*p != *q) return 0; p++; q++; }
    return *p == 0;
}

static char tmpl[] = "/tmp/adata/ashmem.XXXXXX";
static unsigned seed = 0x12345;

int open(const char *path, int flags, int mode) {
    if (streq_ashmem(path)) {
        int i;
        for (i = 0; i < 64; i++) {
            unsigned s = (seed = seed * 1103515245 + 12345);
            char *x = tmpl + 18;
            int k;
            for (k = 0; k < 6; k++) { x[k] = 'a' + ((s >> (k * 4)) & 15); }
            /* O_RDWR|O_CREAT|O_EXCL = 2|0100|0200 = 0302 */
            int fd = sys3(SYS_open, (long)tmpl, 0302, 0600);
            if (fd >= 0) { sys3(SYS_unlink, (long)tmpl, 0, 0); return fd; }
        }
        return -1;
    }
    return sys3(SYS_open, (long)path, flags, mode);
}

/* ASHMEM_SET_NAME / ASHMEM_SET_SIZE / ASHMEM_GET_SIZE etc. all live in the
   0x77xx ioctl space; back SET_SIZE with a real ftruncate so mmap works. */
int ioctl(int fd, unsigned long req, long arg) {
    if ((req & 0xff00) == 0x7700) {
        if ((req & 0xff) == 3) return sys3(SYS_ftruncate, fd, arg, 0); /* SET_SIZE */
        return 0;
    }
    return sys3(SYS_ioctl, fd, (long)req, arg);
}
