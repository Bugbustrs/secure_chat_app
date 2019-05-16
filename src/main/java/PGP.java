
public class PGP {



private static final String errorString ="Message was intercepted or corrupted, ask sender to resend it";






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
            byte [] digest= MessageSignature.getHash(message.getBytes("UTF-8"));

            byte [] cipher = RSAUtil.encrypt(digest,RSAUtil.getPrivateKey(KeyManager.getKeys().get("clientPrivateKey")));
       byte [] concat = CompressUtil.concat(cipher,message.getBytes("UTF-8"));
            byte [] compressed = CompressUtil.compress(concat);
            byte [] sharedCipher= AESUtil.encrypt(compressed);
            byte [] encryptedSharedKey = RSAUtil.encrypt(AESUtil.getSecretKey().getEncoded(),RSAUtil.getPublicKey(KeyManager.getKeys().get("serverPublicKey")));
            //concatenate shared cipher and encryptedSharedKey
         sharedAndEncryptedSharedKeyConcat = CompressUtil.concat(sharedCipher,encryptedSharedKey);

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
        try {
            Payload payload=new Payload(encryptedPayload);
            byte[] sharedCipher=payload.getFirst();
            byte[] encryptedSharedKey=payload.getSecond();
            byte[] sharedKey=RSAUtil.decrypt(encryptedSharedKey,RSAUtil.getPrivateKey(KeyManager.getKeys().get("serverPrivateKey")));
            byte[] compressed=AESUtil.decrypt(sharedCipher, AESUtil.getSecretKey(sharedKey));
            byte[] decompressed=CompressUtil.decompress(compressed);

            payload=new Payload(decompressed);
            byte[] cipher=payload.getFirst();
            byte[] messageBytes=payload.getSecond();

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
