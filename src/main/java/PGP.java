import org.bouncycastle.jcajce.provider.symmetric.AES;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PGP {


    private static final String serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
    private static final String serverPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";
    private static final String clientPublicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCIQmrleQI3SJ3LMt6ZKoqEEbY4mCW3DMCdc7xY+y7tH3gPFma4aOdCcTMvpEJ/WM+hlDwwzS5E1QMDutzFnYcoSvrAo/Nb8GozIGSSJ5hv/8+8L61RhYF+pB6+6AuwfpAeExVmFHR+dNIoBzHYkk89Pn06xRP/xxC/v2z8D0H4JQIDAQAB";
    private  static final String clientPrivateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIhCauV5AjdIncsy3pkqioQRtjiYJbcMwJ1zvFj7Lu0feA8WZrho50JxMy+kQn9Yz6GUPDDNLkTVAwO63MWdhyhK+sCj81vwajMgZJInmG//z7wvrVGFgX6kHr7oC7B+kB4TFWYUdH500igHMdiSTz0+fTrFE//HEL+/bPwPQfglAgMBAAECgYBVKsZr5eXnriDKuGH/9GIdyIBQAXFZZ8Qg5g0sxNuA5PGC5KqRyyf5FI480fuqY1VsFh5FFoo8BbRbqXmCn0KxjwMP2kmm81/6QeLUM5xyPEe8JPftH/cyyhpRsqZ3UCq3qXZXzuFcqepE8trFdIQb8VkrCLdJPeFrcvKOYHVSYQJBAPbkFFUlHGx0oTTKXAh2wMD1WC98ciNoceWg75GMHzTTLb2OxG91KgiCGP5/ZiXqLC5MStB6+/YbNK6WwNM5LhkCQQCNSWhfhSCBTCJ8T1wvUFg3UpREj3pCkR+vQR8x2ZeomxxxNhq4xRY+op9NyzMQUrCsd24FnpVhKcf1ge9/CAPtAkEA2TGwyW7KYIcBwmU7LZ7610V+/NdnucqKWE6KMuqoEVquZJISMVNLVQzOXCVLgZNOprQrJNjsi4dHg0fP/oHREQJAcXatamCILS4OV9SHzLtyTON1jOXIoqLXVjAvNCJxqAcBPW3c4dvtNFn3I0t3c7lkhuzWn46umjwqiGBUDKlMNQJBAKb5/vQMm1HkvW/w8+fmmroZjV0JWF+HOfC5ulNPHN3FK+c4zwohdo1UC5trIbZPBPwmFxwmYT4ft2PbdhVa+sw=";


    static byte [] encrypt(String message){
        //hash it

        byte [] digest= MessageSignature.getHash(message);
        //byte [] cipher;
byte []  sharedAndEncryptedSharedKeyConcat=null;
        try{
      byte [] cipher = RSAUtil.encrypt(digest,RSAUtil.getPrivateKey(clientPrivateKey));
       byte [] concat = CompressUtil.concat(cipher,message.getBytes("UTF-8"));
            byte [] compressed = CompressUtil.compress(concat);
            byte [] sharedCipher= AESUtil.encrypt(compressed);
            byte [] encryptedSharedKey = RSAUtil.encrypt(AESUtil.getSecretKey().getEncoded(),RSAUtil.getPublicKey(serverPublicKey));
            //concatenate shared cipher and encryptedSharedKey
         sharedAndEncryptedSharedKeyConcat = CompressUtil.concat(sharedCipher,encryptedSharedKey);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return sharedAndEncryptedSharedKeyConcat;
    }

    static String decrypt(byte[] encryptedPayload){
        try {
            ByteBuffer buffer=ByteBuffer.wrap(encryptedPayload);
            int sharedCipherLength=buffer.getInt();
            byte[] sharedCipher=new byte[sharedCipherLength];
            buffer.get(sharedCipher);
            byte[] encryptedSharedKey=new byte[buffer.remaining()];
            buffer.get(encryptedSharedKey);
            byte[] sharedKey=RSAUtil.decrypt(encryptedSharedKey,RSAUtil.getPrivateKey(serverPrivateKey));
            byte[] compressed=AESUtil.decrypt(sharedCipher, AESUtil.getSecretKey(sharedKey));
            byte[] decompressed=CompressUtil.decompress(compressed);

            buffer=ByteBuffer.wrap(decompressed);
            int cipherLength=buffer.getInt();
            byte[] cipher =new byte[cipherLength];
            buffer.get(cipher);
            byte[] messageBytes=new byte[buffer.remaining()];
            buffer.get(messageBytes);
            return new String(messageBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
