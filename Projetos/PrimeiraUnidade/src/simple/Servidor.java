package simple;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	public static void main(String[] arg) {
		ServerSocket s;
		try {
			s = new ServerSocket(6789);
			System.out.println("Servidor iniciado na porta 6789");
			while (true) {
				Socket cliente = s.accept();
				System.out.println("Conexão estabelecida " + "(" + cliente
						+ ")");

				DataOutputStream out = new DataOutputStream(
						cliente.getOutputStream());

				out.writeUTF("<r>Vermelho</r>");
				out.writeUTF("<g>Verde</g>");
				out.writeUTF("<b>Azul</b>");

				// cliente.close();
				// System.out.println("Conexão encerrada.");
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
