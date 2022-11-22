import java.util.Random;

class Main
{
	public static void main(String[] args) {
		Mesa mesa = new Mesa(alimentaMesa(), 0);
		mesa.iniciaJogo();
	}

	public static Jogador[] alimentaMesa(){
		Random rd = new Random();
		Jogador[] jogadores = new Jogador[5];
		
		jogadores[0] = new Usuario(Teclado.leString("Digite o seu nome:"), 200);
		jogadores[0].setTitulo("Dealer");

		for (int i = 1; i < jogadores.length; i++){
			if (jogadores[i] == null)
				jogadores[i] = new Computador("Jogador "+i, 200);
		}
    
		return jogadores;
	}
}