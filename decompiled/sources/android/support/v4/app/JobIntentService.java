package android.support.v4.app;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import java.util.HashMap;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class JobIntentService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    b f164b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    a f165c;

    final class a extends AsyncTask<Void, Void, Void> {
        a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0054 A[SYNTHETIC] */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        protected final Void doInBackground(Void[] voidArr) {
            b.a aVar;
            while (true) {
                JobIntentService jobIntentService = JobIntentService.this;
                jobIntentService.f164b.getClass();
                b bVar = jobIntentService.f164b;
                synchronized (bVar.f168b) {
                    try {
                        JobParameters jobParameters = bVar.f169c;
                        if (jobParameters != null) {
                            JobWorkItem jobWorkItemDequeueWork = jobParameters.dequeueWork();
                            if (jobWorkItemDequeueWork != null) {
                                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(bVar.f167a.getClassLoader());
                                aVar = bVar.new a(jobWorkItemDequeueWork);
                            }
                            if (aVar != null) {
                                return null;
                            }
                            JobIntentService jobIntentService2 = JobIntentService.this;
                            aVar.f170a.getIntent();
                            jobIntentService2.a();
                            synchronized (b.this.f168b) {
                                try {
                                    JobParameters jobParameters2 = b.this.f169c;
                                    if (jobParameters2 != null) {
                                        jobParameters2.completeWork(aVar.f170a);
                                    }
                                } finally {
                                }
                            }
                        }
                    } finally {
                    }
                }
                aVar = null;
                if (aVar != null) {
                }
            }
        }

        @Override // android.os.AsyncTask
        protected final void onCancelled(Void r1) {
            JobIntentService.this.getClass();
        }

        @Override // android.os.AsyncTask
        protected final void onPostExecute(Void r1) {
            JobIntentService.this.getClass();
        }
    }

    static final class b extends JobServiceEngine {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final JobIntentService f167a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Object f168b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        JobParameters f169c;

        final class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final JobWorkItem f170a;

            a(JobWorkItem jobWorkItem) {
                this.f170a = jobWorkItem;
            }
        }

        b(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f168b = new Object();
            this.f167a = jobIntentService;
        }

        @Override // android.app.job.JobServiceEngine
        public final boolean onStartJob(JobParameters jobParameters) {
            this.f169c = jobParameters;
            JobIntentService jobIntentService = this.f167a;
            if (jobIntentService.f165c != null) {
                return true;
            }
            a aVar = jobIntentService.new a();
            jobIntentService.f165c = aVar;
            aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public final boolean onStopJob(JobParameters jobParameters) {
            a aVar = this.f167a.f165c;
            if (aVar != null) {
                aVar.cancel(false);
            }
            synchronized (this.f168b) {
                this.f169c = null;
            }
            return true;
        }
    }

    static {
        new HashMap();
    }

    protected abstract void a();

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        b bVar = this.f164b;
        if (bVar != null) {
            return bVar.getBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.f164b = new b(this);
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i2, int i3) {
        return 2;
    }
}
