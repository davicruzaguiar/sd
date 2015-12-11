package tcp;

import java.io.DataOutputStream;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class RecebeUDP {
	public static void main(String[] args) throws Exception {
		byte[] buffer = new byte[1024];
		String s;
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		DatagramSocket socket = new DatagramSocket(6789);
		List<String> mensages = new ArrayList<String>();

     	ServerSocket server = new ServerSocket(6789);
		Socket cliente = server.accept();
		DataOutputStream out = new DataOutputStream(cliente.getOutputStream());

		try {
			
			socket.setSoTimeout(10000);

			try {
				socket.receive(packet);
				s = new String(buffer, 0, packet.getLength());
				mensages.add(s);
				out.writeUTF("s");
				System.out.println("From: " + packet.getAddress().getHostName()
						+ ":" + packet.getPort() + ":" + s);

			} catch (InterruptedIOException e) {
				out.writeUTF("n");
				System.out.println("TimeOut: " + e);
			}

			server.close();
			socket.close();

		} catch (SocketException e) {
			out.writeUTF("n");
			System.out.println("Erro de Socket: " + e);
		}
	}
}
