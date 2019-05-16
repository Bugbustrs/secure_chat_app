import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESKeyGenerator {

    private SecretKey secretKey;

    public AESKeyGenerator()throws NoSuchAlgorithmException {
        //generate the key in here
        KeyGenerator keyGenerator= KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(256,secureRandom);

      this.secretKey =keyGenerator.generateKey();

    }

    /**
     * returns a new secret key or shared key
     * @return
     * @throws NoSuchAlgorithmException
     */
    public Key getSecretKey() throws NoSuchAlgorithmException  {

        return this.secretKey;
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

    public static void main(String args[]){
        try {
            AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
            String encodedKey = Base64.getEncoder().encodeToString(aesKeyGenerator.getSecretKey().getEncoded());
            System.out.println(encodedKey);
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();

        }


    }

}
