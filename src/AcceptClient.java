/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Meluleki
 */
public class AcceptClient extends Thread {

    public Socket clientSocket;
    public ObjectInputStream obin;
    public ObjectOutputStream obout;
    private boolean cont;

    public AcceptClient(Socket cs) throws IOException {
	this.cont = true;
	this.clientSocket = cs;
	this.clientSocket.setKeepAlive(true);
	this.obout = new ObjectOutputStream(cs.getOutputStream());
	obout.flush();
	this.obin = new ObjectInputStream(cs.getInputStream());
    }

    @Override
    public void run() {
	while (this.cont) {
	    Object message;
	    try {
		message = obin.readObject();
		if (message instanceof String && message.equals("Disconnect")) {
		    return;
		} else {
		    Server.sg.addMessage(PGP.decrypt((byte[])message));
		}
	    } catch (IOException ex) {
		System.err.println("Server error:" + ex.getMessage());
		return;
	    } catch (ClassNotFoundException ex) {
		Logger.getLogger(AcceptClient.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

    }

}
