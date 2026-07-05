package com.badlogic.gdx.files;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.d;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.n0;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: compiled from: FileHandle.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class a {
    protected File file;
    protected d.a type;

    protected a() {
    }

    private static void copyDirectory(a aVar, a aVar2) {
        aVar2.mkdirs();
        for (a aVar3 : aVar.list()) {
            a aVarChild = aVar2.child(aVar3.name());
            if (aVar3.isDirectory()) {
                copyDirectory(aVar3, aVarChild);
            } else {
                copyFile(aVar3, aVarChild);
            }
        }
    }

    private static void copyFile(a aVar, a aVar2) {
        try {
            aVar2.write(aVar.read(), false);
        } catch (Exception e2) {
            throw new m("Error copying source file: " + aVar.file + " (" + aVar.type + ")\nTo destination: " + aVar2.file + " (" + aVar2.type + ")", (Throwable) e2);
        }
    }

    private int estimateLength() {
        int length = (int) length();
        return length != 0 ? length : GL20.GL_NEVER;
    }

    public static a tempDirectory(String str) {
        try {
            File fileCreateTempFile = File.createTempFile(str, null);
            if (!fileCreateTempFile.delete()) {
                throw new IOException("Unable to delete temp file: " + fileCreateTempFile);
            }
            if (fileCreateTempFile.mkdir()) {
                return new a(fileCreateTempFile);
            }
            throw new IOException("Unable to create temp directory: " + fileCreateTempFile);
        } catch (IOException e2) {
            throw new m("Unable to create temp file.", (Throwable) e2);
        }
    }

    public static a tempFile(String str) {
        try {
            return new a(File.createTempFile(str, null));
        } catch (IOException e2) {
            throw new m("Unable to create temp file.", (Throwable) e2);
        }
    }

    public a child(String str) {
        return this.file.getPath().length() == 0 ? new a(new File(str), this.type) : new a(new File(this.file, str), this.type);
    }

    public void copyTo(a aVar) {
        if (!isDirectory()) {
            if (aVar.isDirectory()) {
                aVar = aVar.child(name());
            }
            copyFile(this, aVar);
            return;
        }
        if (!aVar.exists()) {
            aVar.mkdirs();
            if (!aVar.isDirectory()) {
                throw new m(a.a.i(aVar, "Destination directory cannot be created: "));
            }
        } else if (!aVar.isDirectory()) {
            throw new m(a.a.i(aVar, "Destination exists but is not a directory: "));
        }
        copyDirectory(this, aVar.child(name()));
    }

    public boolean delete() {
        d.a aVar = this.type;
        if (aVar == d.a.f1644a) {
            throw new m("Cannot delete a classpath file: " + this.file);
        }
        if (aVar != d.a.f1645b) {
            return file().delete();
        }
        throw new m("Cannot delete an internal file: " + this.file);
    }

    public boolean deleteDirectory() {
        d.a aVar = this.type;
        if (aVar == d.a.f1644a) {
            throw new m("Cannot delete a classpath file: " + this.file);
        }
        if (aVar != d.a.f1645b) {
            return deleteDirectory(file());
        }
        throw new m("Cannot delete an internal file: " + this.file);
    }

    public void emptyDirectory() {
        emptyDirectory(false);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.type == aVar.type && path().equals(aVar.path());
    }

    public boolean exists() {
        int iOrdinal = this.type.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                return file().exists();
            }
            if (file().exists()) {
                return true;
            }
        }
        StringBuilder sb = new StringBuilder("/");
        sb.append(this.file.getPath().replace('\\', '/'));
        return a.class.getResource(sb.toString()) != null;
    }

    public String extension() {
        String name = this.file.getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf == -1 ? "" : name.substring(iLastIndexOf + 1);
    }

    public File file() {
        return this.type == d.a.f1646c ? new File(Gdx.files.getExternalStoragePath(), this.file.getPath()) : this.file;
    }

    public int hashCode() {
        return path().hashCode() + ((this.type.hashCode() + 37) * 67);
    }

    public boolean isDirectory() {
        if (this.type == d.a.f1644a) {
            return false;
        }
        return file().isDirectory();
    }

    public long lastModified() {
        return file().lastModified();
    }

    public long length() {
        d.a aVar = this.type;
        if (aVar != d.a.f1644a && (aVar != d.a.f1645b || this.file.exists())) {
            return file().length();
        }
        InputStream inputStream = read();
        try {
            long jAvailable = inputStream.available();
            n0.a(inputStream);
            return jAvailable;
        } catch (Exception unused) {
            n0.a(inputStream);
            return 0L;
        } catch (Throwable th) {
            n0.a(inputStream);
            throw th;
        }
    }

    public a[] list() {
        if (this.type == d.a.f1644a) {
            throw new m("Cannot list a classpath directory: " + this.file);
        }
        String[] list = file().list();
        if (list == null) {
            return new a[0];
        }
        a[] aVarArr = new a[list.length];
        int length = list.length;
        for (int i2 = 0; i2 < length; i2++) {
            aVarArr[i2] = child(list[i2]);
        }
        return aVarArr;
    }

    public ByteBuffer map() {
        return map(FileChannel.MapMode.READ_ONLY);
    }

    public void mkdirs() {
        d.a aVar = this.type;
        if (aVar == d.a.f1644a) {
            throw new m("Cannot mkdirs with a classpath file: " + this.file);
        }
        if (aVar != d.a.f1645b) {
            file().mkdirs();
        } else {
            throw new m("Cannot mkdirs with an internal file: " + this.file);
        }
    }

    public void moveTo(a aVar) {
        int iOrdinal = this.type.ordinal();
        if (iOrdinal == 0) {
            throw new m("Cannot move a classpath file: " + this.file);
        }
        if (iOrdinal == 1) {
            throw new m("Cannot move an internal file: " + this.file);
        }
        if ((iOrdinal == 2 || iOrdinal == 3) && file().renameTo(aVar.file())) {
            return;
        }
        copyTo(aVar);
        delete();
        if (exists() && isDirectory()) {
            deleteDirectory();
        }
    }

    public String name() {
        return this.file.getName();
    }

    public String nameWithoutExtension() {
        String name = this.file.getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf == -1 ? name : name.substring(0, iLastIndexOf);
    }

    public a parent() {
        File parentFile = this.file.getParentFile();
        if (parentFile == null) {
            parentFile = this.type == d.a.f1647d ? new File("/") : new File("");
        }
        return new a(parentFile, this.type);
    }

    public String path() {
        return this.file.getPath().replace('\\', '/');
    }

    public String pathWithoutExtension() {
        String strReplace = this.file.getPath().replace('\\', '/');
        int iLastIndexOf = strReplace.lastIndexOf(46);
        return iLastIndexOf == -1 ? strReplace : strReplace.substring(0, iLastIndexOf);
    }

    public InputStream read() {
        d.a aVar = this.type;
        if (aVar == d.a.f1644a || ((aVar == d.a.f1645b && !file().exists()) || (this.type == d.a.f1648e && !file().exists()))) {
            InputStream resourceAsStream = a.class.getResourceAsStream("/" + this.file.getPath().replace('\\', '/'));
            if (resourceAsStream != null) {
                return resourceAsStream;
            }
            throw new m("File not found: " + this.file + " (" + this.type + ")");
        }
        try {
            return new FileInputStream(file());
        } catch (Exception e2) {
            if (file().isDirectory()) {
                throw new m("Cannot open a stream to a directory: " + this.file + " (" + this.type + ")", (Throwable) e2);
            }
            throw new m("Error reading file: " + this.file + " (" + this.type + ")", (Throwable) e2);
        }
    }

    public byte[] readBytes() {
        InputStream inputStream = read();
        try {
            try {
                n0.a aVar = new n0.a(Math.max(0, estimateLength()));
                byte[] bArr = new byte[4096];
                while (true) {
                    int i2 = inputStream.read(bArr);
                    if (i2 == -1) {
                        return aVar.toByteArray();
                    }
                    aVar.write(bArr, 0, i2);
                }
            } catch (IOException e2) {
                throw new m("Error reading file: " + this, (Throwable) e2);
            }
        } finally {
            n0.a(inputStream);
        }
    }

    public String readString() {
        return readString(null);
    }

    public Reader reader() {
        return new InputStreamReader(read());
    }

    public a sibling(String str) {
        if (this.file.getPath().length() != 0) {
            return new a(new File(this.file.getParent(), str), this.type);
        }
        throw new m("Cannot get the sibling of the root.");
    }

    public String toString() {
        return this.file.getPath().replace('\\', '/');
    }

    public d.a type() {
        return this.type;
    }

    public OutputStream write(boolean z2) {
        d.a aVar = this.type;
        if (aVar == d.a.f1644a) {
            throw new m("Cannot write to a classpath file: " + this.file);
        }
        if (aVar == d.a.f1645b) {
            throw new m("Cannot write to an internal file: " + this.file);
        }
        parent().mkdirs();
        try {
            return new FileOutputStream(file(), z2);
        } catch (Exception e2) {
            if (file().isDirectory()) {
                throw new m("Cannot open a stream to a directory: " + this.file + " (" + this.type + ")", (Throwable) e2);
            }
            throw new m("Error writing file: " + this.file + " (" + this.type + ")", (Throwable) e2);
        }
    }

    public void writeBytes(byte[] bArr, boolean z2) {
        OutputStream outputStreamWrite = write(z2);
        try {
            try {
                outputStreamWrite.write(bArr);
            } catch (IOException e2) {
                throw new m("Error writing file: " + this.file + " (" + this.type + ")", (Throwable) e2);
            }
        } finally {
            n0.a(outputStreamWrite);
        }
    }

    public void writeString(String str, boolean z2) {
        writeString(str, z2, null);
    }

    public Writer writer(boolean z2) {
        return writer(z2, null);
    }

    public a(String str) {
        this.file = new File(str);
        this.type = d.a.f1647d;
    }

    public void emptyDirectory(boolean z2) {
        d.a aVar = this.type;
        if (aVar == d.a.f1644a) {
            throw new m("Cannot delete a classpath file: " + this.file);
        }
        if (aVar != d.a.f1645b) {
            emptyDirectory(file(), z2);
        } else {
            throw new m("Cannot delete an internal file: " + this.file);
        }
    }

    public ByteBuffer map(FileChannel.MapMode mapMode) throws Throwable {
        RandomAccessFile randomAccessFile;
        if (this.type == d.a.f1644a) {
            throw new m(a.a.i(this, "Cannot map a classpath file: "));
        }
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(this.file, mapMode == FileChannel.MapMode.READ_ONLY ? "r" : "rw");
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            MappedByteBuffer map = randomAccessFile.getChannel().map(mapMode, 0L, this.file.length());
            map.order(ByteOrder.nativeOrder());
            n0.a(randomAccessFile);
            return map;
        } catch (Exception e3) {
            e = e3;
            throw new m("Error memory mapping file: " + this + " (" + this.type + ")", (Throwable) e);
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile2 = randomAccessFile;
            n0.a(randomAccessFile2);
            throw th;
        }
    }

    public String readString(String str) {
        StringBuilder sb = new StringBuilder(estimateLength());
        InputStreamReader inputStreamReader = null;
        try {
            try {
                inputStreamReader = str == null ? new InputStreamReader(read()) : new InputStreamReader(read(), str);
                char[] cArr = new char[256];
                while (true) {
                    int i2 = inputStreamReader.read(cArr);
                    if (i2 == -1) {
                        n0.a(inputStreamReader);
                        return sb.toString();
                    }
                    sb.append(cArr, 0, i2);
                }
            } catch (IOException e2) {
                throw new m("Error reading layout file: " + this, (Throwable) e2);
            }
        } catch (Throwable th) {
            n0.a(inputStreamReader);
            throw th;
        }
    }

    public Reader reader(String str) {
        InputStream inputStream = read();
        try {
            return new InputStreamReader(inputStream, str);
        } catch (UnsupportedEncodingException e2) {
            n0.a(inputStream);
            throw new m(a.a.i(this, "Error reading file: "), (Throwable) e2);
        }
    }

    public void writeString(String str, boolean z2, String str2) {
        Writer writer = null;
        try {
            try {
                writer = writer(z2, str2);
                writer.write(str);
            } catch (Exception e2) {
                throw new m("Error writing file: " + this.file + " (" + this.type + ")", (Throwable) e2);
            }
        } finally {
            n0.a(writer);
        }
    }

    public Writer writer(boolean z2, String str) {
        d.a aVar = this.type;
        if (aVar == d.a.f1644a) {
            throw new m("Cannot write to a classpath file: " + this.file);
        }
        if (aVar == d.a.f1645b) {
            throw new m("Cannot write to an internal file: " + this.file);
        }
        parent().mkdirs();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file(), z2);
            return str == null ? new OutputStreamWriter(fileOutputStream) : new OutputStreamWriter(fileOutputStream, str);
        } catch (IOException e2) {
            if (file().isDirectory()) {
                throw new m("Cannot open a stream to a directory: " + this.file + " (" + this.type + ")", (Throwable) e2);
            }
            throw new m("Error writing file: " + this.file + " (" + this.type + ")", (Throwable) e2);
        }
    }

    public a(File file) {
        this.file = file;
        this.type = d.a.f1647d;
    }

    protected a(String str, d.a aVar) {
        this.type = aVar;
        this.file = new File(str);
    }

    public void writeBytes(byte[] bArr, int i2, int i3, boolean z2) {
        OutputStream outputStreamWrite = write(z2);
        try {
            try {
                outputStreamWrite.write(bArr, i2, i3);
            } catch (IOException e2) {
                throw new m("Error writing file: " + this.file + " (" + this.type + ")", (Throwable) e2);
            }
        } finally {
            n0.a(outputStreamWrite);
        }
    }

    private static boolean deleteDirectory(File file) {
        emptyDirectory(file, false);
        return file.delete();
    }

    public a[] list(FileFilter fileFilter) {
        if (this.type != d.a.f1644a) {
            String[] list = file().list();
            if (list == null) {
                return new a[0];
            }
            a[] aVarArr = new a[list.length];
            int i2 = 0;
            for (String str : list) {
                a aVarChild = child(str);
                if (fileFilter.accept(aVarChild.file())) {
                    aVarArr[i2] = aVarChild;
                    i2++;
                }
            }
            if (i2 >= list.length) {
                return aVarArr;
            }
            a[] aVarArr2 = new a[i2];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, i2);
            return aVarArr2;
        }
        throw new m("Cannot list a classpath directory: " + this.file);
    }

    protected a(File file, d.a aVar) {
        this.file = file;
        this.type = aVar;
    }

    private static void emptyDirectory(File file, boolean z2) {
        File[] fileArrListFiles;
        if (!file.exists() || (fileArrListFiles = file.listFiles()) == null) {
            return;
        }
        int length = fileArrListFiles.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (!fileArrListFiles[i2].isDirectory()) {
                fileArrListFiles[i2].delete();
            } else if (z2) {
                emptyDirectory(fileArrListFiles[i2], true);
            } else {
                deleteDirectory(fileArrListFiles[i2]);
            }
        }
    }

    public BufferedReader reader(int i2) {
        return new BufferedReader(new InputStreamReader(read()), i2);
    }

    public int readBytes(byte[] bArr, int i2, int i3) {
        InputStream inputStream = read();
        int i4 = 0;
        while (true) {
            try {
                try {
                    int i5 = inputStream.read(bArr, i2 + i4, i3 - i4);
                    if (i5 <= 0) {
                        n0.a(inputStream);
                        return i4 - i2;
                    }
                    i4 += i5;
                } catch (IOException e2) {
                    throw new m("Error reading file: " + this, (Throwable) e2);
                }
            } catch (Throwable th) {
                n0.a(inputStream);
                throw th;
            }
        }
    }

    public BufferedReader reader(int i2, String str) {
        try {
            return new BufferedReader(new InputStreamReader(read(), str), i2);
        } catch (UnsupportedEncodingException e2) {
            throw new m(a.a.i(this, "Error reading file: "), (Throwable) e2);
        }
    }

    public BufferedInputStream read(int i2) {
        return new BufferedInputStream(read(), i2);
    }

    public OutputStream write(boolean z2, int i2) {
        return new BufferedOutputStream(write(z2), i2);
    }

    public void write(InputStream inputStream, boolean z2) {
        OutputStream outputStreamWrite = null;
        try {
            try {
                outputStreamWrite = write(z2);
                byte[] bArr = new byte[4096];
                while (true) {
                    int i2 = inputStream.read(bArr);
                    if (i2 == -1) {
                        return;
                    } else {
                        outputStreamWrite.write(bArr, 0, i2);
                    }
                }
            } catch (Exception e2) {
                throw new m("Error stream writing to file: " + this.file + " (" + this.type + ")", (Throwable) e2);
            }
        } finally {
            n0.a(inputStream);
            n0.a(outputStreamWrite);
        }
    }

    public a[] list(FilenameFilter filenameFilter) {
        if (this.type != d.a.f1644a) {
            File file = file();
            String[] list = file.list();
            if (list == null) {
                return new a[0];
            }
            a[] aVarArr = new a[list.length];
            int i2 = 0;
            for (String str : list) {
                if (filenameFilter.accept(file, str)) {
                    aVarArr[i2] = child(str);
                    i2++;
                }
            }
            if (i2 >= list.length) {
                return aVarArr;
            }
            a[] aVarArr2 = new a[i2];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, i2);
            return aVarArr2;
        }
        throw new m("Cannot list a classpath directory: " + this.file);
    }

    public a[] list(String str) {
        if (this.type != d.a.f1644a) {
            String[] list = file().list();
            if (list == null) {
                return new a[0];
            }
            a[] aVarArr = new a[list.length];
            int i2 = 0;
            for (String str2 : list) {
                if (str2.endsWith(str)) {
                    aVarArr[i2] = child(str2);
                    i2++;
                }
            }
            if (i2 >= list.length) {
                return aVarArr;
            }
            a[] aVarArr2 = new a[i2];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, i2);
            return aVarArr2;
        }
        throw new m("Cannot list a classpath directory: " + this.file);
    }
}
