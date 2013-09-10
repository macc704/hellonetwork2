package hellonetowork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class ClientMain4 {
	public static void main(String args[]) throws Exception{
		new ClientMain4().run();
	}

	void run() throws Exception {
		Socket sock = new Socket("192.168.1.163", 10001); //send
		Socket readSock = new Socket("192.168.1.163", 10000); //read
		new SocketReader(readSock).start();
		OutputStream out = sock.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		while(true){
			System.out.print(">>");
			String input = input();
			if(input.equals("end")) break;
			oos.writeObject(input);
		}
		readSock.close();
		out.close();
		sock.close();
	}

	String input() throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}

	class SocketReader extends Thread {
		ObjectInputStream ois;
		public SocketReader(Socket sock){
			try {
				InputStream in = sock.getInputStream();
				ois = new ObjectInputStream(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void run(){
			try {
				while(true){
					System.out.println(ois.readObject());
				}
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}
