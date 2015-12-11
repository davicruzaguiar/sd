package SendRecUDP;


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
		DatagramSocket socket = new DatagramSocket(1234);
		List<String> mensages = new ArrayList<String>();

		try {
			socket.setSoTimeout(10000);
			ServerSocket server;
			server = new ServerSocket(6789);
			Socket cliente = server.accept();
			DataOutputStream out = new DataOutputStream(
					cliente.getOutputStream());
			
			for (int i = 0; i < 5; i++) {
				try {
					socket.receive(packet);
					s = new String(buffer, 0, packet.getLength());
					mensages.add(s);
					out.writeUTF("Msg Recebida!");
					System.out.println("From: "
							+ packet.getAddress().getHostName() + ":"
							+ packet.getPort() + ":" + s);
					
				} catch (InterruptedIOException e) {
					out.writeUTF("Msg Não recebida!");
					server.close();
					System.out.println("TimeOut: " + e);
				}
				
			}
		} catch (SocketException e) {
			System.out.println("Erro de Socket: " + e);
		}
	}
}