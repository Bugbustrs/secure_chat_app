import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESUtil {

    static final String secretKey = "oM/FA/2PYQmZMfkGWoE2DLHdzU8FBEBzlazlrgjI3Bc=";

    /**
     * should take in compressed
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
   public static byte[] encrypt(byte[] data)throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,BadPaddingException

    {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//modes influence the behaviour of the algorithm
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(secretKey));
    return cipher.doFinal(data);
    }

    /**
     * decrypts compressed data in pgp
     * @param data
     * @return
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
  public static  byte[] decrypt(byte[] data)throws IllegalBlockSizeException,BadPaddingException, InvalidKeyException,  NoSuchAlgorithmException, NoSuchPaddingException{
        Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,getSecretKey(secretKey));
        return cipher.doFinal(data);

    }

    /**
     * converts an encoded key from string to SecretKey object
     * @param key
     * @return
     */
    static private Key getSecretKey(String key) {
byte[] decodeKey =  Base64.getDecoder().decode(secretKey);//decode the encoded key
return new SecretKeySpec(decodeKey,0,decodeKey.length,"AES");
    }


}
