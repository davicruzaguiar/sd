package SendRecUDP;

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
		int delay = 5000; // delay de 5 seg.
		boolean flag = true;

		do {

			String s = "Mensagem " + (++count);
			byte[] msg = new byte[s.length()];
			msg = s.getBytes();
			DatagramPacket packet = new DatagramPacket(msg, msg.length, end, 1234);
			DatagramSocket socket = new DatagramSocket();
			
			try {
				socket.send(packet);
				//1 - Aqui verificar se deve-se mudar a variavel FLAG e  encerrar o loop WHILE ou não
			} catch (IOException e) {
				
				for(int i = 0; i < 5; i++){
					System.out.println("Não foi possível enviar o pacote!");
					Thread.sleep(delay);
					socket.send(packet);
					
					ServerSocket server;
					server = new ServerSocket(6789);
					Socket servidor = server.accept();
					DataInputStream in = new DataInputStream(servidor.getInputStream());
					String entrada = "";
					entrada = in.readUTF();

					if(entrada.equals("Msg Recebida!")){
						break;
					}else if(entrada.equals("Msg Não recebida!") && i == 5){
						count = 0;
					}
				}
				
				e.printStackTrace();
			}
			socket.close();
		
		} while (flag);

	}
}