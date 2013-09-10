package hellonetwork3;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public static void main(String[] args) throws Exception {
		ServerMain main = new ServerMain();
		main.run();
	}

	void run() throws Exception {
		System.out.println("Hello Server!");

		ServerSocket serverSock = new ServerSocket(10000);
		// System.out.println(serverSock.getLocalSocketAddress());
		while (true) {
			System.out.println("Listening...");
			Socket sock = serverSock.accept();
			System.out.println("Accepted.");
			InputStream in = sock.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			Object o = ois.readObject();
			System.out.println("Read.");
			OutputStream out = sock.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			System.out.println(o);
			oos.writeObject(o);
			sock.close();
		}
		//serverSock.close();
	}

}
