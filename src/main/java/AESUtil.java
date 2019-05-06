import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AESUtil {

    static final String secretKey = "oM/FA/2PYQmZMfkGWoE2DLHdzU8FBEBzlazlrgjI3Bc=";


    byte[] encrypt(String data,String key)throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//modes influence the behaviour of the algorithm
        cipher.init(Cipher.ENCRYPT_MODE,getSecretKey(key));
    return null;}



    private Key getSecretKey(String key) {

return null;
    }

}
