import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main {
    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
    private static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";

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
