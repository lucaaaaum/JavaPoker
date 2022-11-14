import java.util.Random;

class Main
{
	public static void main(String[] args) {
		Mesa mesa = new Mesa(alimentaMesa(), 0);
		mesa.iniciaJogo();
		mesa.distribuiCartas();
	}

	public static Jogador[] alimentaMesa(){
		Random rd = new Random();
		Jogador[] jogadores = new Jogador[5];
		int posicaoDealer = rd.nextInt(1, jogadores.length);
		
		jogadores[posicaoDealer] = new Dealer("Jogador "+posicaoDealer, 100, new Baralho(alimentaBaralho()));
		jogadores[0] = new Usuario(Teclado.leString("Digite o seu nome:"), 100);

		for (int i = 1; i < jogadores.length; i++){
			if (jogadores[i] == null)
				jogadores[i] = new Computador("Jogador "+i, 100);
		}
    
		return jogadores;
	}

	public static Carta[] alimentaBaralho(){
		Carta[] retorno = new Carta[52];
		char[] naipes = new char[] { 'C', 'O', 'P', 'E'};

		int posicao = 0;
		for (char naipe : naipes){
			for (int i = 1; i <= 13; i++){
				retorno[posicao] = new Carta(naipe, i);
				posicao++;
			}
		}
    
		return retorno;
	}
}