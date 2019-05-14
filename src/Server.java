
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
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
public class Server {
    private static final long PORT_NUMBER = 6666;
    private final ServerSocket server;
    public static  ServerGUI sg; 
    
    public Server() throws IOException{
	server = new ServerSocket((int) PORT_NUMBER);
	sg = new ServerGUI();
    }
    
     public final void connect() throws UnknownHostException {
        System.out.println("Server is running!!!! Waiting for connections");
	InetAddress ip =  InetAddress.getLocalHost();
	sg.setAddress(ip.getHostAddress());
	sg.setPort(""+PORT_NUMBER);
	sg.setVisible(true);
        while (true) {
            try {
                Socket clienSocket = this.server.accept();
                Thread runThread = new Thread(new AcceptClient(clienSocket));
		runThread.start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     public static void main(String args[]) throws IOException{
	 Server s = new Server();
	 s.connect();
     }
}
