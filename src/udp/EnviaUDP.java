package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EnviaUDP {
	public static void main(String[] args) throws Exception {
		InetAddress end = InetAddress.getByName("192.168.98.66");
		String s = "Fora isso, tudo tranquilo neh?";
		byte[] msg = new byte[s.length()];
		msg = s.getBytes();
		DatagramPacket packet = new DatagramPacket(msg, msg.length, end, 1234);
		DatagramSocket socket = new DatagramSocket();
		socket.send(packet);
		socket.close();
	}
}
