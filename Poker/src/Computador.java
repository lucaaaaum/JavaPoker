public class Computador extends Jogador
{
	public Computador(String nome, int fichas){
		super(nome, fichas);
	}
  
	public int aposta(int quantidadeMinima) {
		if (getMao().getForca() < 20)
			return super.aposta(quantidadeMinima);
		
		switch (getMao().getForca()) {
		case 20:
			return super.aposta(quantidadeMinima + (int)(getFichas()*0.05));
		case 30:
			return super.aposta(quantidadeMinima + (int)(getFichas()*0.1));
		case 40:
			return super.aposta(quantidadeMinima + (int)(getFichas()*0.15));
		case 50:
			return super.aposta(quantidadeMinima + (int)(getFichas()*0.2));
		case 60:
			return super.aposta(quantidadeMinima + (int)(getFichas()*0.25));
		case 70:
			return super.aposta(quantidadeMinima + (int)(getFichas()*0.30));
		case 80:
			return super.aposta(quantidadeMinima + (int)(getFichas()*0.35));
		case 90:
			return super.aposta(quantidadeMinima + (int)(getFichas()*0.5));
		case 100:
			return super.aposta(getFichas());
		default:
			return 0;
		}
	}
  
	@Override public Carta[] trocaCarta() {
		if (getMao().getForca() < 20)
			return trocaHighCard();
		
		switch (getMao().getForca()) {
		case 20:
			return trocaPair();
		case 30:
			return trocaThreeOfAKind();
		default:
			return null;
		}
	}
	
	private Carta[] trocaHighCard() {
		Carta maior = getMao().getHighCard();
		Carta[] retorno = new Carta[4];
		int indiceRetorno = 0;
		for (int i = 0; i < getMao().getCartas().length; i++) {
			if (getMao().getCartas()[i] != maior) {
				retorno[indiceRetorno] = getMao().retiraCarta();
				indiceRetorno++;
			}
		}
		return retorno;
	}
	
	private Carta[] trocaPair() {
		int[] posicoesPar = getMao().getPrimeiroPar();
		Carta[] retorno = new Carta[3];
		int indiceRetorno = 0;
		for (int i = 0; i < getMao().getCartas().length; i++)
			if (i != posicoesPar[0] && i != posicoesPar[1]) {
				retorno[indiceRetorno] = getMao().retiraCarta(i);
				indiceRetorno++;
			}
		return retorno;
	}
	
	private Carta[] trocaThreeOfAKind() {
		int[] posicoesTrinca = getMao().getTrinca();
		Carta[] retorno = new Carta[2];
		int indiceRetorno = 0;
		for (int i = 0; i < getMao().getCartas().length; i++)
			if (i != posicoesTrinca[0] && i != posicoesTrinca[1] && i != posicoesTrinca[2]) {
				retorno[indiceRetorno] = getMao().retiraCarta(i);
				indiceRetorno++;
			}
		return retorno;
	}
	
	
}