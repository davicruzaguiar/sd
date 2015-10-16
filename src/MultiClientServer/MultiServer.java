package MultiClientServer;

import java.io.*;
import java.net.*;

public class MultiServer {

	public static void main(String[] args) {
		try {
			InetAddress grupo = InetAddress.getByName("227.1.2.3");
			MulticastSocket s = new MulticastSocket(1234);
			s.joinGroup(grupo);
			byte[] buf = new byte[1000];
			DatagramPacket dIn = new DatagramPacket(buf, buf.length);
			s.receive(dIn);
			DatagramPacket dOut = new DatagramPacket(buf, buf.length, grupo,
					1234);
			s.send(dOut);
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}
}
