package chat;


/**
 * @version 1.10 1997-06-27
 * @author Cay Horstmann
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadedEchoServer {

	public static List<PrintWriter> incomingList = new ArrayList<PrintWriter>();

	public static void main(String[] args) {
		int i = 1;
		try {
			ServerSocket s = new ServerSocket(8189);

			for (;;) {
				Socket incoming = s.accept();
				System.out.println("Spawning " + i);
				// ThreadedEchoServer.incomingList.add(incoming);//(posição,
				// elemento)
				new ThreadedEchoHandler(incoming, i).start();
				i++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class ThreadedEchoHandler extends Thread {
	public ThreadedEchoHandler(Socket i, int c) {
		incoming = i;
		counter = c;
	}

	public void run() {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			PrintWriter out = new PrintWriter(incoming.getOutputStream(), true /* autoFlush */);
			ThreadedEchoServer.incomingList.add(out);

			out.println("Hello! Enter BYE to exit. ");

			boolean done = false;
			while (!done) {
				String str = in.readLine();
				System.out.println(incoming + " - " + str);
				if (str == null)
					done = true;
				else {
					for (PrintWriter outText : ThreadedEchoServer.incomingList) {
						outText.println("Echo (" + counter + "): " + str);
					}

					if (str.trim().equals("BYE"))
						done = true;
				}
			}

			incoming.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("Ended client connection: " + counter);
		ThreadedEchoServer.incomingList.remove(incoming);
	}

	private Socket incoming;
	private int counter;
}
