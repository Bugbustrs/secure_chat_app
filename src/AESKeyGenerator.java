import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESKeyGenerator {



    static public Key getSecretKey(){
        KeyGenerator keyGenerator= null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(256,secureRandom);



        String encodedKey = Base64.getEncoder().encodeToString(keyGenerator.generateKey().getEncoded());
       // System.out.println(encodedKey+"\n");
        byte[] decodeKey = Base64.getDecoder().decode(encodedKey);//decode the encoded key
        return new SecretKeySpec(decodeKey,0,decodeKey.length,"AES");
    }








}
