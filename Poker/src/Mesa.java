public class Mesa
{
	private Jogador[] jogadores;
	private Dealer dealer;
	private Usuario usuario;
	private int posicaoDealer;
	private int posicaoUsuario;
	private int pote;

	public Mesa(Jogador[] jogadores, int pote){
		this.jogadores = jogadores;
		this.pote = pote;
		posicaoDealer = encontraDealer();
		dealer = new Dealer();
		posicaoUsuario = encontraUsuario();
		usuario = (Usuario) jogadores[posicaoUsuario];
	}

	public void iniciaJogo() {
		dealer.embaralhar();
		defineTitulos();

		while (getAtivos() > 1) {
			iniciaRodada();
		}
	}
	
	public int encontraDealer() {
		for (int i = 0; i < jogadores.length; i++)
			if (jogadores[i].getTitulo().equalsIgnoreCase("Dealer"))
				return i;
		return 0;
	}
	
	public int encontraUsuario() {
		for (int i = 0; i < jogadores.length; i++)
			if (jogadores[i] instanceof Usuario)
				return i;
		return 0;
	}
	
	public void defineTitulos() {
		(jogadores[posicaoDealer]).setTitulo("Dealer");
		(jogadores[andarHorario(posicaoDealer, 1)]).setTitulo("Small Blind");
		(jogadores[andarHorario(posicaoDealer, 2)]).setTitulo("Big Blind");
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
		usuario.imprimeInfo();
		usuario.getMao().imprimeCartas();
		apostaInicial();
		etapaDeTrocas();
		usuario.imprimeInfo();
		usuario.getMao().imprimeCartas();
		resetaCartas();
		Teclado.leString("fim da rodada");
	}
  
	public void apostaInicial() {
		Jogador smallBlind = jogadores[andarHorario(posicaoDealer, 1)];
		Jogador bigBlind = jogadores[andarHorario(posicaoDealer, 2)];
		int apostaBigBlind = bigBlind.aposta(10);
		int apostaSmallBlind = smallBlind.aposta(apostaBigBlind/2);
		Utilitarios.imprimeCaixaTexto(new String[] {bigBlind.getTitulo()+" "+bigBlind.getNome()+" apostou "+apostaBigBlind+".",
													smallBlind.getTitulo()+" "+smallBlind.getNome()+" apostou "+apostaSmallBlind+"."},
									 "APOSTA INICIAL");
	}

	public void distribuiCartas() {
		Baralho baralho = dealer.getBaralho();
		Carta[] cartas = baralho.getCartas();
	 
		int contaCartas = 0;
		for (int i = 0; i < jogadores.length; i++) {
			Mao mao = jogadores[i].getMao();
			for (int j = 0; j < mao.getCartas().length; j++) {
				mao.insereCarta(cartas[contaCartas]);
				cartas[contaCartas] = null;
				contaCartas++;
			}
			mao.ordenaMao();
			mao.verificaTipo();
		}

		baralho.setCartas(cartas);
		dealer.ordenar();
	}
  
	public void etapaDeTrocas() {
		for (Jogador jogador : jogadores) {
			Carta cartaDevolvida = jogador.trocaCarta();
			if (cartaDevolvida != null)
				troca(cartaDevolvida, jogador);
	  	}
		dealer.ordenar();
	}
	
	public void troca(Carta cartaDevolvida, Jogador jogador) {
		dealer.devolveCarta(cartaDevolvida);
		Carta cartaNova = jogador.getMao().insereCarta(dealer.retiraCarta());
		jogador.getMao().ordenaMao();
		Utilitarios.imprimeCaixaTexto(jogador.getNome()+" trocou "+cartaDevolvida.toString()+" por "+cartaNova.toString(), "TROCA");
	}

	public void resetaCartas() {
		dealer.setBaralho(dealer.alimentaBaralho());
		for (Jogador jogador : jogadores)
			jogador.resetaCartas();
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