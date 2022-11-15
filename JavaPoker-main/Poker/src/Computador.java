public class Computador extends Jogador
{
	public Computador(String nome, int fichas){
		super(nome, fichas);
	}
  
	public int aposta() {
		return 1;
	}
  
	public Carta trocaCarta() {
		System.out.println("Sou um jogador computador!");
		return null;
	}
}