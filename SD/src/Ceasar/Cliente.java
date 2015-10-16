package Ceasar;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente{

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
		double numero;
		int key;
		String texto;
		Socket s = null;
		
		try {
			
			System.out.println("Conectando...");
			s = new Socket("192.168.96.88", 6789);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			
			System.out.println("Conectado.");
			System.out.println("Escolha as opcoes: \n 1 - encriptar \n 2- desencriptar");
			Scanner sc = new Scanner(System.in);
			
			key  =  sc.nextInt();
			
			switch (key) {
			case 1:
				System.out.println("Digite a string a ser encryptada:");
				texto = sc.nextLine();
				System.out.println("Enviando ...");
				out.writeUTF(texto);
				break;
			case 2:
				System.out.println("Digite a string a ser desencryptada:");
				texto = sc.nextLine();
				System.out.println("Enviando ...");
				out.writeUTF(texto);
				break;
			default:
				break;
			}
			
			
			
			texto = in.readUTF();
			System.out.println("Texto recebido do servidor" + texto);
			
			out.flush();
		
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			try {
				if (s != null)
					s.close();
			} catch (Exception e2) {
			}
		}
		System.out.println("Conexão encerrada");
		try {
			System.in.read();
		} catch (Exception e3) {
		}
	}
}



