import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressUtil {

    public static byte[] compress(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        deflater.finish();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        byte[] output = outputStream.toByteArray();
        return output;
    }

    public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        byte[] output = outputStream.toByteArray();
        return output;
    }

    public static byte[] concat(byte[] a,byte[] b) {
     //added 4 bytes to store length of the first array
     ByteBuffer buffer=ByteBuffer.allocate(a.length+b.length+4);
     buffer.putInt(a.length);
     buffer.put(a);
     buffer.put(b);
     return buffer.array();
    }

    public static void main(String[] args) {
        try {
            byte[] arr=CompressUtil.compress("HELLO WORLDHELLO".getBytes("UTF-8"));
            arr=CompressUtil.decompress(arr);
            System.out.println(new String(arr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
