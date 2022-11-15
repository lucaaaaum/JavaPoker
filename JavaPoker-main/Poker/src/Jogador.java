public class Jogador
{
	private String nome;
	private int fichas;
	private Mao mao;
	private boolean ativo;
	private String titulo;

	public Jogador(String nome, int fichas){
	    this.fichas = fichas;
	    this.mao = new Mao(new Carta[5]);
	    this.ativo = true;
	    this.titulo = "";
	}
	
	public void verificaAtivo(){
	if (fichas <= 0)
      ativo = false;
	}
	
	public int aposta(int quantidade){
		fichas -= quantidade;
	    return quantidade;
	}	
	
	public Carta trocaCarta() {
		System.out.println("Sou um jogador genérico!");
		return null;
	}
	
	public String getNome(){
		return nome;
	}
	  
	public int getFichas(){
		return fichas;
	}
	
	public Mao getMao(){
		return mao;
	}
	  
	public boolean getAtivo() {
		return ativo;
	}
	  
	public String getTitulo() {
		return titulo;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	  
	public void setFichas(int fichas){
		this.fichas = fichas;
	}
	
 	public void setMao(Mao mao){
 		this.mao = mao;
 	}
	  
 	public void setAtivo(boolean ativo) {
 		this.ativo = ativo;
 	}
	  
 	public void setTitulo(String titulo) {
 		this.titulo = titulo;
 	}
}