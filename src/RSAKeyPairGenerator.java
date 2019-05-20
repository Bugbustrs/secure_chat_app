import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class RSAKeyPairGenerator {




    private PrivateKey privateKey;
    private PublicKey publicKey;

    public RSAKeyPairGenerator() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair pair = keyPairGenerator.generateKeyPair();
        this.privateKey=pair.getPrivate();
        this.publicKey= pair.getPublic();

    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);

        fos.write(key);
        fos.flush();
        fos.close();
    }
}
