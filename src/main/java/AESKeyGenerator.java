import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESKeyGenerator {

    private SecretKey sharedKey;

    public AESKeyGenerator()throws NoSuchAlgorithmException {
        //generate the key in here
        KeyGenerator keyGenerator= KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(256,secureRandom);

        this.sharedKey=keyGenerator.generateKey();

    }

    public Key getSharedKey() {
        //can read key from the file and return it here
        return sharedKey;
    }

    public void writeToFile(byte[] key,String filename)throws FileNotFoundException {
        File file =new File(filename);
        FileOutputStream fos= new FileOutputStream(file);
        try {
            fos.write(key);
            fos.flush();//clear the stream of anything that might be in the stream
            fos.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
