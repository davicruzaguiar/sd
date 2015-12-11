package tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EnviaUDP {
	public static void main(String[] args) throws Exception {

		InetAddress end = InetAddress.getByName("192.168.0.101");
		int count = 0;
		int delay = 2000; // delay de 2 seg.
		boolean flag = true;

		do {

			String s = "Mensagem " + (++count);
			byte[] msg = new byte[s.length()];
			msg = s.getBytes();
			DatagramPacket packet = new DatagramPacket(msg, msg.length, end,
					6789);
			DatagramSocket socket = new DatagramSocket();

			ServerSocket server;
			server = new ServerSocket(6789);
			Socket servidor = server.accept();
			DataInputStream in = new DataInputStream(servidor.getInputStream());
			String entrada = "";

			try {
				socket.send(packet);
				entrada = in.readUTF();

				if (entrada.equals("s")) {
					System.out.println("Mensagem enviada.");
				} else {

					for (int i = 0; i < 5; i++) {
						System.out.println("Não foi possível enviar o pacote!");
						Thread.sleep(delay);
						socket.send(packet);
						entrada = in.readUTF();
						
						if (entrada.equals("s")) {
							System.out.println("Mensagem enviada.");
							break;
						}else if(i == 5){
							count = 0;
						}
						
					}

				}
				
				server.close();
				
			} catch (IOException e) {
				System.out.println("Erro ao tentar enviar o pacote!");
				flag = false;
				e.printStackTrace();
			}
			socket.close();

		} while (flag);

	}
}
