package MultiClientServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class MultiClient {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			InetAddress grupo = InetAddress.getByName("227.1.2.3");
			MulticastSocket s = new MulticastSocket(1234);
			s.joinGroup(grupo);
			//byte[] buf = new byte[1000];
			System.out.println("Insira seu username ?");
			String username = scanner.nextLine();
			LeituraThread leitura = new LeituraThread(s);
			leitura.start();
			String message;
			String pacote;
			do {
				message = scanner.nextLine();
				pacote = username + ":" + message;
				byte[] msg = pacote.getBytes();
				DatagramPacket datagram = new DatagramPacket(msg, msg.length,
						grupo, 1234);
				s.send(datagram);
				if ("EXIT".equals(message)) {
					leitura.stop();
					break;
				}
			} while (!"EXIT".equals(message));
			s.leaveGroup(grupo);
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}
}

class LeituraThread extends Thread {
	private MulticastSocket s;
	public LeituraThread(MulticastSocket s) {
		this.s = s;
	}
	
	public void run() {
		
		byte[] buf = new byte[1000];
		
		try {
			while ( Boolean.TRUE ) {
				DatagramPacket datagramIn = new DatagramPacket(buf, buf.length);
				s.receive(datagramIn);
				String str = new String(buf, 0, datagramIn.getLength());
				System.out.println(str);
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

