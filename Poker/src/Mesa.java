public class Mesa
{
	private Jogador[] jogadores;
	private Dealer dealer;
	private Usuario usuario;
	private int posicaoDealer;
	private int posicaoUsuario;
	private int pote;
	private int[] apostas;

	public Mesa(Jogador[] jogadores, int pote){
		this.jogadores = jogadores;
		this.pote = pote;
		posicaoDealer = encontraDealer();
		dealer = new Dealer();
		posicaoUsuario = encontraUsuario();
		usuario = (Usuario) jogadores[posicaoUsuario];
		apostas = new int[0];
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
		imprimeInformacoes();
		apostaInicial();
		etapaDeTrocas();
		Utilitarios.limpaConsole();
		imprimeInformacoes();
		
		resetaCartas();
		Utilitarios.imprimeCaixaTexto("A rodada chegou ao fim com o jogador tal sendo vitorioso.", "FIM DA RODADA");
		Utilitarios.limpaConsole();
	}
  
	public void apostaInicial() {
		Jogador smallBlind = jogadores[andarHorario(posicaoDealer, 1)];
		Jogador bigBlind = jogadores[andarHorario(posicaoDealer, 2)];
		int apostaBigBlind = bigBlind.apostaInicial(10);
		apostas = Utilitarios.incrementaArray(apostas);
		apostas[0] = apostaBigBlind;
		int apostaSmallBlind = smallBlind.apostaInicial(apostaBigBlind/2);
		apostas = Utilitarios.incrementaArray(apostas);
		apostas[1] = apostaSmallBlind;
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
		}

		baralho.setCartas(cartas);
		dealer.ordenar();
	}
  
	public void etapaDeTrocas() {
		for (Jogador jogador : jogadores) {
			Carta[] cartasDevolvidas = jogador.trocaCarta();
			if (cartasDevolvidas != null)
				troca(cartasDevolvidas, jogador);
	  	}
		dealer.ordenar();
		Utilitarios.perguntaString("Aperte Enter para continuar.");
	}
	
	public void etapaDeApostas() {
		for (Jogador jogador : jogadores)
			jogador.aposta(apostas[apostas.length-1]);
	}
	
	public void troca(Carta[] cartasDevolvidas, Jogador jogador) {
		for (int i = 0; i < cartasDevolvidas.length; i++) {
			dealer.devolveCarta(cartasDevolvidas[i]);
			Carta cartaNova = jogador.getMao().insereCarta(dealer.retiraCarta());
			Utilitarios.imprimeCaixaTexto(jogador.getNome()+" trocou "+cartasDevolvidas[i].toString()+" por "+cartaNova.toString(), "TROCA");	
		}
		jogador.getMao().ordenaMao();
	}
	
	public void imprimeInformacoes() {
		usuario.imprimeInfo();
		if (quantidadeComputadores() > 0)
				Utilitarios.imprimeCaixaTexto(informacoesDemaisJogadores(), "INFORMAÇÕES DE DEMAIS JOGADORES");
		usuario.getMao().imprimeCartas();
	}
	
	private String[] informacoesDemaisJogadores() {
		String[] retorno = new String[quantidadeComputadores()];
		for (int i = 0; i < jogadores.length; i++)
			if (jogadores[i] instanceof Computador)
				for (int j = 0; j < retorno.length; j++)
					if (retorno[j] == null) {
						retorno[j] = jogadores[i].getTitulo()+" "+jogadores[i].getNome()+" tem "+jogadores[i].getFichas()+" fichas.";
						break;
					}
		return retorno;
	}
	
	private int quantidadeComputadores() {
		int quantidadeComputadores = 0;
		for (int i = 0; i < jogadores.length; i++)
			if (jogadores[i] instanceof Computador)
				quantidadeComputadores++;
		return quantidadeComputadores;
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