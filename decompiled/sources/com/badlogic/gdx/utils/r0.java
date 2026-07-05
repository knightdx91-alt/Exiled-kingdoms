package com.badlogic.gdx.utils;

import java.io.DataInputStream;
import java.io.IOException;

/* JADX INFO: compiled from: UBJsonReader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class r0 implements d {
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:20:0x0058
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:226)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:196)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:63)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    protected static com.badlogic.gdx.utils.t b(java.io.DataInputStream r17, byte r18) {
        /*
            Method dump skipped, instruction units count: 525
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.r0.b(java.io.DataInputStream, byte):com.badlogic.gdx.utils.t");
    }

    protected static long c(byte b2, DataInputStream dataInputStream, boolean z2) {
        if (b2 == 105) {
            return (short) (dataInputStream.readByte() & 255);
        }
        if (b2 == 73) {
            return dataInputStream.readShort() & 65535;
        }
        if (b2 == 108) {
            return dataInputStream.readInt();
        }
        if (b2 == 76) {
            return dataInputStream.readLong();
        }
        if (!z2) {
            return -1L;
        }
        return ((long) (dataInputStream.readByte() & 255)) | (((long) (b2 & 255)) << 24) | (((long) (dataInputStream.readByte() & 255)) << 16) | (((long) (dataInputStream.readByte() & 255)) << 8);
    }

    protected static String d(byte b2, DataInputStream dataInputStream, boolean z2) throws IOException {
        long jC = b2 == 83 ? c(dataInputStream.readByte(), dataInputStream, true) : b2 == 115 ? (short) (dataInputStream.readByte() & 255) : z2 ? c(b2, dataInputStream, false) : -1L;
        if (jC < 0) {
            throw new m("Unrecognized data type, string expected");
        }
        if (jC <= 0) {
            return "";
        }
        byte[] bArr = new byte[(int) jC];
        dataInputStream.readFully(bArr);
        return new String(bArr, "UTF-8");
    }

    @Override // com.badlogic.gdx.utils.d
    public final t a(com.badlogic.gdx.files.a aVar) throws Throwable {
        DataInputStream dataInputStream;
        try {
            DataInputStream dataInputStream2 = null;
            try {
                try {
                    dataInputStream = new DataInputStream(aVar.read(8192));
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    t tVarB = b(dataInputStream, dataInputStream.readByte());
                    n0.a(dataInputStream);
                    return tVarB;
                } finally {
                    n0.a(dataInputStream);
                }
            } catch (IOException e3) {
                e = e3;
                dataInputStream2 = dataInputStream;
                throw new h0(e);
            } catch (Throwable th2) {
                th = th2;
                dataInputStream2 = dataInputStream;
                n0.a(dataInputStream2);
                throw th;
            }
        } catch (Exception e4) {
            throw new h0(a.a.i(aVar, "Error parsing file: "), e4);
        }
    }
}
