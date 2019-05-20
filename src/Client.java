
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dube_
 */
public class Client {

    public static ObjectInputStream obin;
    public static ObjectOutputStream obout;
    public static Socket client;

    public static boolean connect(String serverAdress, int portNumber) {
	try {
	    client = new Socket(serverAdress, portNumber);
	    obin = new ObjectInputStream(client.getInputStream());
	    obout = new ObjectOutputStream(client.getOutputStream());
	    obout.flush();
	    return true;
	} catch (IOException ex) {
	    JOptionPane.showMessageDialog(null, "Please check the provided server address and port number and ensure they are correct");
	    return false;
	}
    }

    public static void sendMessage(String s){
	try{
	    obout.writeObject(PGP.encrypt(s));
	    obout.flush();
	}catch (IOException e){
	   System.out.println(e.getMessage());
	}
    }
    
    public static void disconnect() {
	try {
	    obout.writeObject("Disconnect");
            client.shutdownOutput();
	    client.close();
	} catch (IOException ex) {
	    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
