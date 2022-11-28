public class Usuario extends Jogador
{
	public Usuario(String nome, int fichas){
		super(nome, fichas);
	}
  
	public int aposta(int quantiaMinima){
		int valor = 0;
		boolean respostaValida = false;
		while (!respostaValida) {
			valor = Utilitarios.perguntaNumerica("Digite a sua aposta: ", quantiaMinima, getFichas(), "APOSTA MÍNIMA: "+quantiaMinima);
			boolean confirmacao = false;
			if (valor == getFichas())
				confirmacao = Utilitarios.perguntaBooleana("Tem certeza que quer apostar todas as suas fichas?");
			respostaValida = confirmacao;
		}
		return valor;
	}
  
	public @Override Carta[] trocaCarta() {
		boolean resposta = Utilitarios.perguntaBooleana("Deseja trocar alguma carta?");
		if (!resposta)
			return null;

		int quantidade = Utilitarios.perguntaNumerica("Quantas cartas você deseja trocar?", 1, (getMao().getCartas().length));
		
		Carta[] cartas = new Carta[quantidade];
		
		if (quantidade == getMao().getCartas().length) {
			for (int i = 0; i < getMao().getCartas().length; i++)
				cartas[i] = getMao().retiraCarta(i);
			return cartas;
		}
			
		int[] indicesCartas = new int[quantidade];
		for (int i = 0; i < indicesCartas.length; i++)
			indicesCartas[i] = -1;
		
		int indiceCartaTemp = 0;

		for (int i = 0; i < quantidade; i++) {
			boolean respostaValida = false;
			while (!respostaValida) {
				respostaValida = true;
				indiceCartaTemp = Utilitarios.perguntaNumerica("Qual carta?", 0, (getMao().getCartas().length-1));
				for (int j = 0; j < quantidade; j++) {
					if (indicesCartas[j] == indiceCartaTemp) {
						Utilitarios.imprimeCaixaTexto("A carta na posição "+indiceCartaTemp+" já foi escolhida para trocar.", "CARTA JÁ ESCOLHIDA");
						respostaValida = false;
					}
				}
			}
			indicesCartas[i] = indiceCartaTemp;
			cartas[i] = getMao().retiraCarta(indiceCartaTemp);
		}
		
		return cartas;
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