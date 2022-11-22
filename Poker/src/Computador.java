public class Computador extends Jogador
{
	public Computador(String nome, int fichas){
		super(nome, fichas);
	}
  
	public int aposta() {
		return 1;
	}
  
	public Carta trocaCarta() {
		return null;
	}
	
}