package SendRecUDP;

public class Pacote {

	private int cabecalho;
	private String mensagem;
	
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public int getCabecalho() {
		return cabecalho;
	}
	public void setCabecalho(int cabecalho) {
		this.cabecalho = cabecalho;
	}
	
}
