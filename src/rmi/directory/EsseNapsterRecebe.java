package rmi.directory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class EsseNapsterRecebe {
	public static void main(String[] args) {

		// Criando Classe cliente para receber arquivo
		EsseNapsterRecebe cliente = new EsseNapsterRecebe();
		// Solicitando arquivo
		cliente.getFileFromFileServe();
	}

	@SuppressWarnings("resource")
	private void getFileFromFileServe() {
		Socket sock = null;
		FileOutputStream file = null;
		InputStream is = null;
		DataInputStream in = null;
		DataOutputStream out = null;

		try {

			sock = new Socket("127.0.0.1", 6789);
			in = new DataInputStream(sock.getInputStream());
			String files = "";

			do {
				files = in.readUTF();
				System.out.println(files);
			} while (!files.equals("fim"));

			System.out.println("Informe nome do arquivo para download:");

			Scanner scn = new Scanner(System.in);
			String arquivo = scn.nextLine();

			String baixar = "C:\\client_files\\" + arquivo;

			// Cria arquivo local no cliente
			file = new FileOutputStream(new File(baixar));
			System.out.println("");
			System.out.println("O arquivo ser� criado em C:\\client_files");
			System.out.println("");

			out = new DataOutputStream(sock.getOutputStream());
			out.writeUTF(arquivo);

			// Criando conex�o com o servidor
			System.out.println("Solicitando arquivo " + arquivo
					+ " ao Servidor");
			System.out.println("");
			// socket = new Socket("localhost", 1234);
			is = sock.getInputStream();

			// Prepara variaveis para transferencia
			byte[] cbuffer = new byte[1024];
			int bytesRead;

			// Copia conteudo do canal
			System.out.println("Download em andamento...");
			System.out.println("");
			while ((bytesRead = is.read(cbuffer)) != -1) {
				file.write(cbuffer, 0, bytesRead);
				file.flush();
			}

			System.out.println("Download conclu�do com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sock != null) {
				try {
					sock.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (file != null) {
				try {
					file.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (is != null) {
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}