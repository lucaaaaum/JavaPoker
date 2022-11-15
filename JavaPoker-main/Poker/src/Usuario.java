public class Usuario extends Jogador
{
	public Usuario(String nome, int fichas){
		super(nome, fichas);
	}
  
	public void aposta(){
		super.aposta(Teclado.leInt("Digite a aposta: "));
	}
  
	public Carta trocaCarta() {
		System.out.println("Sou um jogador usuário!");
		return null;
	}
}