import java.nio.ByteBuffer;

public class Payload {
    private byte[] first;
    private byte[] second;

    public Payload(byte[] payload){
        deconcatenate(payload);
    }
    void deconcatenate(byte[] payload){
        ByteBuffer buffer=ByteBuffer.wrap(payload);
        int firstLength=buffer.getInt();
        first=new byte[firstLength];
        buffer.get(first);
        second = new byte[buffer.remaining()];
        buffer.get(second);
    }
    public byte[] getFirst(){
        return first;
    }
    public byte[] getSecond(){
        return second;
    }
}
