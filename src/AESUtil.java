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


    // for CBC mode
    static private final byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private static final IvParameterSpec ivspec = new IvParameterSpec(iv);

    private static Key secretKey =AESKeyGenerator.getSecretKey();//generates key everytime you send a message

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
  public static  byte[] decrypt(byte[] data,Key sharedKey)throws Exception, IllegalBlockSizeException,BadPaddingException, InvalidKeyException,  NoSuchAlgorithmException, NoSuchPaddingException{


        Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,sharedKey,ivspec);
        return cipher.doFinal(data);

    }

    /**
     * converts an encoded key from string to SecretKey object
     * @param
     * @return
     */

    static public Key getSecretKey() {
        try {

            //byte[] decodeKey = Base64.getDecoder().decode(KeyManager.getKeys().get("secretKey"));//decode the encoded key
            //return new SecretKeySpec(decodeKey,0,decodeKey.length,"AES");
            return secretKey;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    static public Key getSecretKey(byte[] decodeKey) {
        return new SecretKeySpec(decodeKey,0,decodeKey.length,"AES");
    }
}