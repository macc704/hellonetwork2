package hellonetwork3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain2 {

	public static void main(String[] args) throws Exception {
		ServerMain2 main = new ServerMain2();
		main.run();
	}

	List<ObjectOutputStream> outputs = new ArrayList<ObjectOutputStream>();

	// List<ObjectInputStream> inputs = new ArrayList<ObjectInputStream>();

	void run() throws Exception {

		System.out.println("Hello Server2!");

		Thread th1 = new Thread() {
			public void run() {
				try {
					ServerSocket serverSock = new ServerSocket(10000);
					while (true) {
						Socket sock = serverSock.accept();
						OutputStream out = sock.getOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(out);
						outputs.add(oos);
						System.out.println("1 client added.");
					}
					// serverSock.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		th1.start();

		Thread th2 = new Thread() {
			public void run() {
				try {
					ServerSocket serverSock = new ServerSocket(10001);
					while (true) {
						final Socket sock = serverSock.accept();
						System.out.println("1 sending client added.");
						Thread th = new Thread() {
							public void run() {
								try {
									InputStream in = sock.getInputStream();
									ObjectInputStream ois = new ObjectInputStream(
											in);
									while (true) {
										Object o = ois.readObject();
										if (o instanceof String) {
											send((String) o);
										}
									}
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
						};
						th.start();
					}
					// serverSock.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		th2.start();

		// System.out.println(serverSock.getLocalSocketAddress());
		while (true) {
			System.out.println(">>");
			String message = input();

			send(message);
		}
	}

	void send(String message) throws Exception {
		for (ObjectOutputStream output : outputs) {
			output.writeObject(message);
		}
	}

	String input() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		return reader.readLine();
	}

}
