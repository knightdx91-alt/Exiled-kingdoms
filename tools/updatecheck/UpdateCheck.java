import com.android.apksig.ApkVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import java.io.File; import java.nio.ByteBuffer; import java.security.MessageDigest;
import java.util.zip.ZipFile; import java.io.InputStream;

/** Prints "<cert-sha256> <package> <versionCode> <verified>" for an APK. */
public class UpdateCheck {
  public static void main(String[] a) throws Exception {
    File f = new File(a[0]);
    ApkVerifier.Result r = new ApkVerifier.Builder(f).setMinCheckedPlatformVersion(16).build().verify();
    String cert = "none";
    if (!r.getSignerCertificates().isEmpty()) {
      byte[] d = MessageDigest.getInstance("SHA-256").digest(r.getSignerCertificates().get(0).getEncoded());
      StringBuilder s = new StringBuilder(); for (byte b : d) s.append(String.format("%02X", b)); cert = s.toString();
    }
    byte[] mf; try (ZipFile z = new ZipFile(f); InputStream in = z.getInputStream(z.getEntry("AndroidManifest.xml"))) { mf = in.readAllBytes(); }
    AndroidBinXmlParser p = new AndroidBinXmlParser(ByteBuffer.wrap(mf));
    String pkg = "?"; long vc = -1;
    for (int ev = p.getEventType(); ev != AndroidBinXmlParser.EVENT_END_DOCUMENT; ev = p.next()) {
      if (ev == AndroidBinXmlParser.EVENT_START_ELEMENT && p.getDepth() == 1 && "manifest".equals(p.getName())) {
        for (int i = 0; i < p.getAttributeCount(); i++) {
          if ("package".equals(p.getAttributeName(i))) pkg = p.getAttributeStringValue(i);
          if ("versionCode".equals(p.getAttributeName(i))) vc = p.getAttributeIntValue(i) & 0xffffffffL;
        }
        break;
      }
    }
    System.out.println(cert + " " + pkg + " " + vc + " " + r.isVerified());
  }
}
