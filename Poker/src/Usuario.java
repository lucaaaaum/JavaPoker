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

		if !(resposta)
			return null;

		int quantidade = Utilitarios.perguntaNumerica("Quantas cartas você deseja trocar?", 1, (getMao().getCartas().length-1));
		int[] numerosCartas = new int[quantidade];
		for (int i = 0; i < quantidade; i++) {
			indiceCarta = Utilitarios.perguntaNumerica("Qual carta?", 0, (getMao().getCartas().length-1));	
		}
		
		return getMao().retiraCarta(indiceCarta);
	}
	
	public void imprimeInfo() {
		String[] info = new String[] {
				"Nome: "+getNome(),
				"T�tulo: "+getTitulo(),
				"Fichas: "+getFichas()
		};
		Utilitarios.imprimeCaixaTexto(info, "INFORMAÇÕES DO USUÁRIO");
	}
}