package Ceasar;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
	
	public static String decode(String enc, int offset) {
        return encode(enc, 26-offset);
    }
 
    public static String encode(String enc, int offset) {
        offset = offset % 26 + 26;
        StringBuilder encoded = new StringBuilder();
        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26 ));
                } else {
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26 ));
                }
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }
	public static void main(String[] arg) {
		String texto, strEncode;
		ServerSocket s;
		try {
			s = new ServerSocket(6789);
			System.out.println("Servidor iniciado na porta 6789");
			while (true) {
				Socket cliente = s.accept();
				System.out.println("Conexão estabelecida " + "(" + cliente + ")");
				DataInputStream in = new DataInputStream(cliente.getInputStream());
				DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
				
				//int i =  in.readInt();
				texto = in.readUTF();
				
				
					System.out.println("String recebida : " + texto);
					out.writeUTF(encode(texto, 12));
					System.out.println(encode(texto, 12));
					//System.out.println("String recebida : " + texto);
					//out.writeUTF(decode(texto, 12));
				
					
					
				
				
				cliente.close();
				System.out.println("Conexão encerrada.");
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
