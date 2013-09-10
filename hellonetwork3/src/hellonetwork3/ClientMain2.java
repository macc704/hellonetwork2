package hellonetwork3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain2 {

	public static void main(String[] args) throws Exception {
		ClientMain2 main = new ClientMain2();
		main.run();
//main.test();
	}
	
	void test() throws Exception {
		System.out.println(input());
	}

	void run() throws Exception {
		Socket sock = new Socket("192.168.1.163", 10000);	
		while(true){
		InputStream in = sock.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(in);
		System.out.println(ois);
		}
	}
	
	String input() throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}

}
