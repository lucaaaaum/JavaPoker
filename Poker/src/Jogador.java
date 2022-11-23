public class Jogador
{
	private String nome;
	private int fichas;
	private Mao mao;
	private boolean ativo;
	private String titulo;

	public Jogador(String nome, int fichas){
		this.nome = nome;
	    this.fichas = fichas;
	    this.mao = new Mao(new Carta[5]);
	    this.ativo = true;
	    this.titulo = "Padrão";
	}
	
	public void verificaAtivo(){
	if (fichas <= 0)
      ativo = false;
	}
	
	public int aposta(int quantidade){
		if (fichas - quantidade >= 0) {
			fichas -= quantidade;	
		} else {
			quantidade = fichas;
			fichas = 0;
		}
		return quantidade;
	}
	
	public Carta trocaCarta() {
		return null;
	}

	public void resetaCartas() {
		Carta[] cartas = mao.getCartas();
		for (int i = 0; i < cartas.length; i++) {
			cartas[i] = null;
		}
		setMao(new Mao(cartas));
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