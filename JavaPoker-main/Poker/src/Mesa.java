public class Mesa
{
	private Jogador[] jogadores;
	private int pote;

	public Mesa(Jogador[] jogadores, int pote){
		this.jogadores = jogadores;
		this.pote = pote;
	}

	public void iniciaJogo() {
		((Dealer)jogadores[encontraDealer()]).embaralhar();
		
		defineTitulos();
		
		while (getAtivos() > 1) {
			iniciaRodada();
		}
	}
	
	public int encontraDealer() {
		for (int i = 0; i < jogadores.length; i++)
			if (jogadores[i] instanceof Dealer)
				return i;
		return 0;
	}
	
	public void defineTitulos() {
		int posicaoDealer = encontraDealer();
		(jogadores[posicaoDealer]).setTitulo("Dealer");
		(jogadores[andarHorario(posicaoDealer, 1)]).setTitulo("SB");
		(jogadores[andarHorario(posicaoDealer, 2)]).setTitulo("BB");
	}

	public int andarHorario(int posicaoInicial, int casas) {
		if (jogadores.length <= posicaoInicial + casas) {
			casas -= jogadores.length - posicaoInicial;
			posicaoInicial = 0;
		}
		return posicaoInicial + casas;
	}
	
	public void iniciaRodada() {
		distribuiCartas();
		apostaInicial();
		etapaDeTrocas();
	}
  
	public void apostaInicial() {
		int posicaoDealer = encontraDealer();
		pote += (jogadores[andarHorario(posicaoDealer, 1)]).aposta(5);
		pote += (jogadores[andarHorario(posicaoDealer, 2)]).aposta(10);
	}

	public void distribuiCartas() {
		int posicaoDealer = 0;
		for (int i = 0; i < jogadores.length; i++)
			if (jogadores[i] instanceof Dealer)
				posicaoDealer = i;
		
		Baralho baralho = ((Dealer)jogadores[posicaoDealer]).getBaralho();
		Carta[] cartas = baralho.getCartas();
	 
		int contaCartas = 0;
		for (int i = 0; i < jogadores.length; i++) {
			Mao mao = jogadores[i].getMao();
			for (int j = 0; j < mao.getCartas().length; j++) {
				mao.insereCarta(cartas[contaCartas]);
				contaCartas++;
			}
			System.out.println("Mão " + i);
			mao.imprimeMao();
		}
		
		ordenaMaos();
	}
	
	public void ordenaMaos() {
		for (Jogador jogador : jogadores) {
			jogador.getMao().ordenaCartas();
		}
	}
  
	public void etapaDeTrocas() {
		for (Jogador jogador : jogadores) {
			jogador.trocaCarta();
	  	}
	}
  
	public int getAtivos() {
		int ativos = 0;
		for (int i = 1; i < jogadores.length; i++)
			if (( jogadores[i]).getAtivo())
				ativos++;
		return ativos;
	}
 
	public Jogador[] rotacionaMesa() {
		Jogador[] retorno = new Jogador[jogadores.length];
		return retorno;
	}
	
	public Jogador[] getPessoas(){
		return jogadores;
	}

	public int getPote(){
		return pote;
	}

	public void setPessoas(Jogador[] jogadores){
		this.jogadores = jogadores;
	}

	public void setPote(int pote){
		this.pote = pote;
	}
}