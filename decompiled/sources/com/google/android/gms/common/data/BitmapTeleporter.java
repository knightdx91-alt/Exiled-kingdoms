package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveFile;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@ShowFirstParty
@KeepForSdk
@SafeParcelable.Class(creator = "BitmapTeleporterCreator")
public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {

    @KeepForSdk
    public static final Parcelable.Creator<BitmapTeleporter> CREATOR = new zaa();

    @SafeParcelable.Field(id = 3)
    private final int mType;

    @SafeParcelable.VersionField(id = 1)
    private final int zale;

    @SafeParcelable.Field(id = 2)
    private ParcelFileDescriptor zalf;
    private Bitmap zalg;
    private boolean zalh;
    private File zali;

    @SafeParcelable.Constructor
    BitmapTeleporter(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) ParcelFileDescriptor parcelFileDescriptor, @SafeParcelable.Param(id = 3) int i3) {
        this.zale = i2;
        this.zalf = parcelFileDescriptor;
        this.mType = i3;
        this.zalg = null;
        this.zalh = false;
    }

    private static void zaa(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e2) {
            Log.w("BitmapTeleporter", "Could not close stream", e2);
        }
    }

    private final FileOutputStream zabz() {
        File file = this.zali;
        if (file == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File fileCreateTempFile = File.createTempFile("teleporter", ".tmp", file);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                this.zalf = ParcelFileDescriptor.open(fileCreateTempFile, DriveFile.MODE_READ_ONLY);
                fileCreateTempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException unused) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Could not create temporary file", e2);
        }
    }

    @KeepForSdk
    public Bitmap get() {
        if (!this.zalh) {
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zalf));
            try {
                try {
                    byte[] bArr = new byte[dataInputStream.readInt()];
                    int i2 = dataInputStream.readInt();
                    int i3 = dataInputStream.readInt();
                    Bitmap.Config configValueOf = Bitmap.Config.valueOf(dataInputStream.readUTF());
                    dataInputStream.read(bArr);
                    zaa(dataInputStream);
                    ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, configValueOf);
                    bitmapCreateBitmap.copyPixelsFromBuffer(byteBufferWrap);
                    this.zalg = bitmapCreateBitmap;
                    this.zalh = true;
                } catch (IOException e2) {
                    throw new IllegalStateException("Could not read from parcel file descriptor", e2);
                }
            } catch (Throwable th) {
                zaa(dataInputStream);
                throw th;
            }
        }
        return this.zalg;
    }

    @KeepForSdk
    public void release() {
        if (this.zalh) {
            return;
        }
        try {
            this.zalf.close();
        } catch (IOException e2) {
            Log.w("BitmapTeleporter", "Could not close PFD", e2);
        }
    }

    @KeepForSdk
    public void setTempDir(File file) {
        if (file == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.zali = file;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        if (this.zalf == null) {
            Bitmap bitmap = this.zalg;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getHeight() * bitmap.getRowBytes());
            bitmap.copyPixelsToBuffer(byteBufferAllocate);
            byte[] bArrArray = byteBufferAllocate.array();
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(zabz()));
            try {
                try {
                    dataOutputStream.writeInt(bArrArray.length);
                    dataOutputStream.writeInt(bitmap.getWidth());
                    dataOutputStream.writeInt(bitmap.getHeight());
                    dataOutputStream.writeUTF(bitmap.getConfig().toString());
                    dataOutputStream.write(bArrArray);
                } catch (IOException e2) {
                    throw new IllegalStateException("Could not write into unlinked file", e2);
                }
            } finally {
                zaa(dataOutputStream);
            }
        }
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zalf, i2 | 1, false);
        SafeParcelWriter.writeInt(parcel, 3, this.mType);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        this.zalf = null;
    }

    @KeepForSdk
    public BitmapTeleporter(Bitmap bitmap) {
        this.zale = 1;
        this.zalf = null;
        this.mType = 0;
        this.zalg = bitmap;
        this.zalh = true;
    }
}
