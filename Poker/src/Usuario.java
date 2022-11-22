public class Usuario extends Jogador
{
	public Usuario(String nome, int fichas){
		super(nome, fichas);
	}
  
	public void aposta(){
		super.aposta(Teclado.leInt("Digite a aposta: "));
	}
  
	public @Override Carta trocaCarta() {
		int indiceCarta = 0;
		boolean resposta = Utilitarios.perguntaBooleana("Deseja trocar alguma carta?");
	
		if (resposta)
			indiceCarta = Utilitarios.perguntaNumerica("Digite o n√∫mero da carta", 0, (getMao().getCartas().length-1));
		else
			return null;

		return getMao().retiraCarta(indiceCarta);
	}
	
	public void imprimeInfo() {
		String[] info = new String[] {
				"Nome: "+getNome(),
				"TÌtulo: "+getTitulo(),
				"Fichas: "+getFichas()
		};
		Utilitarios.imprimeCaixaTexto(info, "INFORMA«’ES DO USU¡RIO");
	}
}