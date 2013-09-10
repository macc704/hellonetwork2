package hellonetwork3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		ClientMain main = new ClientMain();
		main.run();
//main.test();
	}
	
	void test() throws Exception {
		System.out.println(input());
	}

	void run() throws Exception {
		Socket sock = new Socket("192.168.1.163", 10000);		
		OutputStream out = sock.getOutputStream();
		ObjectOutputStream ois = new ObjectOutputStream(out);
		System.out.print(">>");
		String input = input();
		ois.writeObject(input);
		out.close();
		sock.close();
	}
	
	String input() throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}

}
