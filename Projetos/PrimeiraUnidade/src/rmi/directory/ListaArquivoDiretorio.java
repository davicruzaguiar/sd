package rmi.directory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ListaArquivoDiretorio {
	public static void main(String args[]) {

		// Scanner sc = new Scanner(System.in);
		// String dir = sc.next();

		File diretorio = new File("C:\\server_files");
		File fList[] = diretorio.listFiles();
		
		System.out.println("Numero de arquivos no diretorio : " + fList.length);

		ServerSocket server;
		try {

			server = new ServerSocket(6789);
			System.out.println("Servidor iniciado na porta 6789");

			while (true) {
				Socket cliente = server.accept();
				System.out.println("Conexão estabelecida " + "(" + cliente
						+ ")");

				DataInputStream in = new DataInputStream(cliente.getInputStream());
				DataOutputStream out = new DataOutputStream(
						cliente.getOutputStream());

				for (int i = 0; i < fList.length; i++) {
					System.out.println(fList[i].getName());
					out.writeUTF("Arquivo "+ i+1 +": " + fList[i].getName());
					
					if(i + 1 == fList.length){
						out.writeUTF("fim");
					}
					//out.writeUTF("Caminho para Download: " + "C:\\server_files\\" + fList[i].getName());
				}
				
				String fName = in.readUTF();
				File f = new File("C:\\server_files\\" + fName);  
		        FileInputStream fi = new FileInputStream(f);
		        OutputStream output = cliente.getOutputStream();
		        
		        byte[] cbuffer = new byte[1024];    
		        int bytesRead;    
		        while ((bytesRead = fi.read(cbuffer)) != -1) {
					output.write(cbuffer, 0, bytesRead);
					output.flush();
				}
		        
				fi.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
