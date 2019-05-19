import java.security.MessageDigest;


public class MessageSignature {


    /**
     * Method returns a SHA-256 hash of a message
     * @param message
     * @return
     */
    static byte[] getHash(byte [] message){

        MessageDigest mdigest;
        byte[] hash=null;
        try{
            mdigest=MessageDigest.getInstance("SHA-256");
            hash = mdigest.digest(message);
        }
        catch(Exception e){
            System.out.println("No such Algorithm");
        }

        return hash;
    }

        // method for encrypting the hash



}
