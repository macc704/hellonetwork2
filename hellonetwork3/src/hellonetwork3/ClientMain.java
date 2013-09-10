package hellonetwork3;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		ClientMain main = new ClientMain();
		main.run();

	}

	void run() throws Exception {
		Socket sock = new Socket("192.168.1.163", 10000);		
		OutputStream out = sock.getOutputStream();
		ObjectOutputStream ois = new ObjectOutputStream(out);
		ois.writeObject("Test");
		out.close();
		sock.close();
	}

}
