import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PGP {



private static final String errorString ="Message was intercepted or corrupted, ask sender to resend it";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";




    /**
     * encrypts a payload
     * @param message
     * @return
     */
    static byte [] encrypt(String message){
        //hash it

        //byte [] cipher;
byte []  sharedAndEncryptedSharedKeyConcat=null;
        try{

            //print keys
            System.out.println("\nPUBLIC KEYS\n===========================================================================================================================================================================================================\n");

            KeyManager.getKeys().forEach((k,v)->System.out.println(ANSI_GREEN+k+":"+ANSI_RESET+ANSI_YELLOW+v+ANSI_RESET));


            System.out.println("\nSHARED KEY\n===========================================================================================================================================================================================================\n");
            byte[] decodeKey = Base64.getEncoder().encode(AESUtil.getSecretKey().getEncoded());
            System.out.println(new SecretKeySpec(decodeKey,0,decodeKey.length,"AES"));

            System.out.println("\n\nENCRYPTION STAGES");
            System.out.println("\n===========================================================================================================================================================================================================\n");

            byte [] digest= MessageSignature.getHash(message.getBytes("UTF-8"));
            System.out.println("Hash of a message generated using SHA-256 in base64:\n "+ANSI_YELLOW+new String(Base64.getEncoder().encode(digest))+ANSI_RESET+"\n");

            byte [] cipher = RSAUtil.encrypt(digest,RSAUtil.getPrivateKey(KeyManager.getKeys().get("clientPrivateKey")));
            System.out.println("Signed hash using private key of the client in base64:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(cipher))+ANSI_RESET+"\n");
       byte [] concat = CompressUtil.concat(cipher,message.getBytes("UTF-8"));
            System.out.println("concatenated hash and  message:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(concat))+ANSI_RESET+"\n");

            byte [] compressed = CompressUtil.compress(concat);
            System.out.println("compressed data:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(concat))+ANSI_RESET+"\n");

            byte [] sharedCipher= AESUtil.encrypt(compressed);
            System.out.println("cipher encrypted with private key:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(sharedCipher))+ANSI_RESET+"\n");

            byte [] encryptedSharedKey = RSAUtil.encrypt(AESUtil.getSecretKey().getEncoded(),RSAUtil.getPublicKey(KeyManager.getKeys().get("serverPublicKey")));
            //concatenate shared cipher and encryptedSharedKey
            System.out.println("encrypted shared key:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(encryptedSharedKey))+ANSI_RESET+"\n");

            sharedAndEncryptedSharedKeyConcat = CompressUtil.concat(sharedCipher,encryptedSharedKey);
            System.out.println("final cipher sent to the server:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(sharedAndEncryptedSharedKeyConcat))+ANSI_RESET+"\n");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return sharedAndEncryptedSharedKeyConcat;
    }

    /**
     * returns a decrypted string
     * @param encryptedPayload
     * @return
     */
    static String decrypt(byte[] encryptedPayload){

        System.out.println("\n\nDECRYPTION STAGES");
        System.out.println("\n===========================================================================================================================================================================================================\n");

        try {
            Payload payload=new Payload(encryptedPayload);
            byte[] sharedCipher=payload.getFirst();
            System.out.println("sharedCipher:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(sharedCipher))+ANSI_RESET+"\n");

            byte[] encryptedSharedKey=payload.getSecond();
            System.out.println("encrypted shared key:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(encryptedSharedKey))+ANSI_RESET+"\n");

            byte[] sharedKey=RSAUtil.decrypt(encryptedSharedKey,RSAUtil.getPrivateKey(KeyManager.getKeys().get("serverPrivateKey")));
            System.out.println("shared key:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(sharedKey))+ANSI_RESET+"\n");

            byte[] compressed=AESUtil.decrypt(sharedCipher, AESUtil.getSecretKey(sharedKey));
            System.out.println("compressed stuff:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(compressed))+ANSI_RESET+"\n");

            byte[] decompressed=CompressUtil.decompress(compressed);
            System.out.println("decompressed stuff:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(decompressed))+ANSI_RESET+"\n");

            payload=new Payload(decompressed);
            byte[] cipher=payload.getFirst();
            byte[] messageBytes=payload.getSecond();
            System.out.println("message received in base64:\n "+ANSI_YELLOW+ new String(Base64.getEncoder().encode(messageBytes))+ANSI_RESET+"\n");

            return checkFingerPrint(cipher,messageBytes)? new String(messageBytes):errorString;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * checks if the signature of the sender is correct
     * @param cipher
     * @param message
     * @return
     */
    static boolean checkFingerPrint(byte [] cipher,byte [] message){

        try {
            byte [] oldHash = RSAUtil.decrypt(cipher,RSAUtil.getPublicKey(KeyManager.getKeys().get("clientPublicKey")));
            byte [] newHash = MessageSignature.getHash(message);


            return  checkBytes(oldHash,newHash);

        } catch (Exception e) {
            e.printStackTrace();
        }


   return false;
    }

    /**
     * checks equality
     * @param oldHash
     * @param newHash
     * @return
     */

    static boolean checkBytes(byte [] oldHash,byte [] newHash){
        if  (oldHash.length==newHash.length) {
            for (int i = 0; i < oldHash.length;i++){
                if(oldHash[i]!=newHash[i])
                    return false;
            }

        }
        return  true;
    }
}
