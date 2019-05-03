import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.*;
import java.util.zip.Deflater;

public class Security {
    public static void main(String[] args) throws Exception {
        System.out.println(compress("Hello World".getBytes("UTF-8")));
    }
    public static byte[] encrypt(String msg, PrivateKey Ra, PublicKey Ub){
        byte[] encryptedbuffer=null;
        try {
            byte[] messagebyte= msg.getBytes("UTF-8");
            byte[] hashedMsg=digestMessage(messagebyte);
            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,Ra);
            byte[] encryptedDigest=cipher.doFinal(hashedMsg);
            ByteBuffer buffer=ByteBuffer.allocate(encryptedDigest.length+messagebyte.length+4);
            buffer.putInt(encryptedDigest.length);
            buffer.put(encryptedDigest);
            buffer.put(messagebyte);
            byte[] totalMessage=buffer.array();
            byte[] compressedMsg=compress(totalMessage);
            cipher.init(Cipher.ENCRYPT_MODE,Ra);//change to shared
            byte[] compressEncryptedWithShared=cipher.doFinal(compressedMsg);
            cipher.init(Cipher.ENCRYPT_MODE,Ub);
            byte[] sharedEncryptedwithPublic=cipher.doFinal(Ra.getEncoded());//change to shared
            buffer.clear();
            buffer.putInt(compressEncryptedWithShared.length);
            buffer.put(compressEncryptedWithShared);
            buffer.put(sharedEncryptedwithPublic);
            encryptedbuffer = buffer.array();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedbuffer;
    }

    public static byte[] digestMessage(byte[] messagebyte){
        byte[] digested=null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(messagebyte);
            digested = md.digest();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return digested;
    }

    public static byte[] compress(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        deflater.finish();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer); // returns the generated code... index
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        byte[] output = outputStream.toByteArray();
        System.out.println("Original: " + data.length+ " bytes");
        System.out.println("Compressed: " + output.length + " bytes");
        return output;
    }
}