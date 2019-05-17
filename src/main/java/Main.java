import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main {

    public static void main(String args[])throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {

//        String hash = MessageSignature.getHash("Clayton Sibanda");
//        System.out.println(hash);
//        String encrypted = Base64.getEncoder().encodeToString(RSAUtil.encrypt(hash.getBytes(),privateKey));
//        System.out.println("encrypted: "+encrypted);
//        System.out.println(RSAUtil.encrypt(hash.getBytes(),privateKey).length);
//        System.out.println("decrypted: "+new String(RSAUtil.decrypt(encrypted.getBytes(),publicKey)));

        RSAKeyPairGenerator keyPairGenerator = new RSAKeyPairGenerator();
        //System.out.println("private key: " +Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded())+"\npublic key "+Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));

String test ="Lorem Ipsum is simply dummy text of the printing and \n" +
        "typesetting industry. Lorem Ipsum has been the industry's\n " +
        "standard dummy text ever since the 1500s, when an unknown\n printer" +
        " took a galley of type and scrambled it to make a type\n specimen book. " +
        "It has survived not only five centuries, but also the\n leap into electronic" +
        " typesetting, remaining essentially unchanged.\n" +
        " It was popularised in the 1960s with the release\n" +
        " of Letraset sheets containing Lorem Ipsum passages,\n" +
        " and more recently with desktop publishing software\n" +
        " like Aldus PageMaker including versions of Lorem Ipsum.";
        System.out.println(PGP.decrypt(PGP.encrypt(test)));

    }
}
