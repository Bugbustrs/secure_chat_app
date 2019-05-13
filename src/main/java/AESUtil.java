import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
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
   public static byte[] encrypt(byte[] data)throws Exception, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,BadPaddingException

    {
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//modes influence the behaviour of the algorithm
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(),ivspec);
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
  public static  byte[] decrypt(byte[] data)throws Exception, IllegalBlockSizeException,BadPaddingException, InvalidKeyException,  NoSuchAlgorithmException, NoSuchPaddingException{

      byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
      IvParameterSpec ivspec = new IvParameterSpec(iv);
        Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,getSecretKey(),ivspec);
        return cipher.doFinal(data);

    }

    /**
     * converts an encoded key from string to SecretKey object
     * @param
     * @return
     */
    static public Key getSecretKey() {
byte[] decodeKey =  Base64.getDecoder().decode(secretKey);//decode the encoded key
return new SecretKeySpec(decodeKey,0,decodeKey.length,"AES");
    }

    static public Key getSecretKey(byte[] decodeKey) {
        return new SecretKeySpec(decodeKey,0,decodeKey.length,"AES");
    }
}