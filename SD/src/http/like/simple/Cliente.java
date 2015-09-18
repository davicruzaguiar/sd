package http.like.simple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Cliente extends JFrame{
	
	
	
	public static String selectTag(String entrada){
		// formata o texto para HTML   com as  cores de  acordo com as  cores dastags
		String[] textoArray;
		String texto = "";
		String tag = "";
		
		textoArray = entrada.split(">");
		tag = textoArray[0].substring(1);
		
		texto = entrada.substring(entrada.indexOf(">") + 1, entrada.indexOf("/") - 1);
		System.out.println(texto);

		switch (tag) {
		case "u":
			//out.writeUTF(texto.toUpperCase());
			//System.out.println("Upper Case " + texto.toUpperCase());
			texto  = "<html><b style=color:red>"+ texto +"</b></html>";
			break;
		case "l":
			//System.out.println("Low CAse " + texto.toLowerCase());
			texto  = "<html><b style=color:green>"+ texto +"</b></html>";
			break;
		case"r":
			//System.out.println("Color Red" + texto);
			texto  = "<html><b style=color:blue>"+ texto +"</b></html>";
			break;
		default:
			texto = "Não existe Tags no texto";
		}
		
		return texto;
	}
	
	private void exibeFrame(String text) {

    	JPanel panel = new JPanel();
    	
    	JTextPane jep =  new JTextPane(); 
    	jep.setContentType("text/html");
    	jep.setText(text);
    	jep.setPreferredSize(new Dimension(200, 200));
       
        panel.add(jep);
        
        add(panel);

        pack();
        JFrame frame =  new JFrame("Hugo Chrome version 1.0");
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 200);
        frame.setVisible(true);
        
    }
	
	public static void main(String[] arg) {
		double numero;
		int c, total = (int) (10 * Math.random());
		String text;
		
		Socket s = null;
		
		try {
			System.out.println("Conectando...");
			s = new Socket("192.168.96.88", 6789);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			System.out.println("Conectado. Enviando " + total + " números...");
		
			String entrada = "";
			entrada = in.readUTF();
			
			//entrada = selectTag(entrada);
			
			Cliente cliente = new Cliente();
			cliente.exibeFrame(selectTag(entrada));
			
			out.flush();
			//out.writeDouble(-1.0);
		//	System.out.println("Somatório = " + in.readUT());
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



