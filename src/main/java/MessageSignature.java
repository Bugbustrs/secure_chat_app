import java.util.Arrays;
import java.security.MessageDigest;
import org.bouncycastle.crypto.digests.GeneralDigest;
import org.bouncycastle.util.encoders.Hex;

public class MessageSignature {


    /**
     * Method returns a SHA-256 hash of a message
     * @param message
     * @return
     */
    static String getHash(String message){

        MessageDigest mdigest;
        byte[] hash=null;
        try{
            mdigest=MessageDigest.getInstance("SHA-256");
            hash = mdigest.digest(message.getBytes("UTF-8"));
        }
        catch(Exception e){
            System.out.println("No such Algorithm");
        }

String hsh = new String(Hex.encode(hash));
        return hsh;}

        // method for encrypting the hash



}
