public class Mao extends Baralho
{
	private String tipo;
	private int forca;
	private Carta[] copas;
	private Carta[] ouros;
	private Carta[] paus;
	private Carta[] espadas;
	private Carta[] maiorPar;
	private Carta[][] pares;
	private Carta[] maiorTrinca;
	private Carta[][] trincas;
	
	public Mao(Carta[] cartas){
		super(cartas);
		tipo = "PADRÃO";
	}

	@Override public Carta insereCarta(Carta carta) {
		for (int i = 0; i < getCartas().length; i++)
			if (getCartas()[i] == null) {
				getCartas()[i] = carta;
				break;
			}
		return carta;
	}
	
	public Carta retiraCarta(int indice) {
		Carta retorno = getCartas()[indice];
		getCartas()[indice] = null;
		return retorno;
	}
	
	public void ordenaMao() {
		Carta temp = null;
		
		for (int i = 0; i < getCartas().length; i++)
			for (int j = (i+1); j < getCartas().length; j++) {
				if (getCartas()[i].getNumero() > getCartas()[j].getNumero()) {
					temp = getCartas()[i];
					getCartas()[i] = getCartas()[j];
					getCartas()[j] = temp;
				}
			}
		agrupaNaipes();
		verificaTipo();
	}
  
	@Override public void imprimeCartas(){
		String[] textos = new String[getCartas().length];
		for (int i = 0; i < getCartas().length; i++){
			if (getCartas()[i] != null)
				textos[i] = "["+i+"] == "+getCartas()[i].toString();				
			else
				textos[i] = "["+i+"] = Não tem carta aqui.";
		}
		Utilitarios.imprimeCaixaTexto(textos, tipo);
	}

	private int charParaInt(char c) {
		char[] listaNaipes = new char[] { 'C', 'O', 'P', 'E' };
		for (int i = 0; i < listaNaipes.length; i++)
			if (c == listaNaipes[i])
				return i;
		return 'C';
	}
	
	public void trocaCarta(int indiceCarta, Carta carta) {
		getCartas()[indiceCarta] = carta;
	}
  
	public void verificaTipo() {
		if (verificaRoyalFlush())
			setTipo("ROYAL FLUSH");
		else if (verificaStraightFlush())
			setTipo("STRAIGHT FLUSH");
	 	else if (verificaFourOfAKind())
			setTipo("FOUR OF A KIND");
		 else if (verificaFullHouse())
			setTipo("FULL HOUSE");
		 else if (verificaFlush())
			setTipo("FLUSH");
		 else if (verificaStraight())
			setTipo("STRAIGHT");
		 else if (verificaThreeOfAKind())
			setTipo("THREE OF A KIND");
		 else if (verificaTwoPairs())
			setTipo("TWO PAIRS");
		 else if (verificaPair())
			setTipo("PAIR");
		 else
			setTipo("HIGH CARD: " + getHighCard().toString());
	}
  
	private boolean verificaRoyalFlush() {
		if (maiorQuantidadeMesmoNaipe(naipeMaiorQuantidade()) == 5) {
			int inicial = 10;
			for (int i = 0; i < getCartas().length; i++) {
				if (getCartas()[i].getNumero() != (inicial + i))
					return false;
			}
			return true;
		}
		return false;
	}
  
	private boolean verificaStraightFlush() {
		if (maiorQuantidadeMesmoNaipe(naipeMaiorQuantidade()) == 5) {
			int inicial = getCartas()[0].getNumero();
			for (int i = 1; i < getCartas().length; i++) {
				if (getCartas()[i].getNumero() != (inicial + i)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private boolean verificaFourOfAKind() {
		if (maiorQuantidadeMesmoNaipe(naipeMaiorQuantidade()) == 4) {
			int inicial = getCartas()[0].getNumero();
			int contaIguais = 0;
			for (int i = 1; i < getCartas().length; i++)
				if (getCartas()[i].getNumero() == inicial)
					contaIguais++;
			if (contaIguais == 4)
				return true;
		}
		return false;
	}

	private boolean verificaFullHouse() {
		if (getTrinca() != null && getPrimeiroPar() != null)
			return true;
		return false;
	}
	
	
	private boolean verificaFlush() {
		if (maiorQuantidadeMesmoNaipe(naipeMaiorQuantidade()) == 5)
			return true;
		return false;
	}

	private boolean verificaStraight() {
		int inicial = getCartas()[0].getNumero();
		for (int i = 1; i < getCartas().length; i++) {
			if (getCartas()[i].getNumero() != (inicial + i))
				return false;
		}
		return true;
	}
	
	private boolean verificaThreeOfAKind() {
		if (getTrinca() != null)
			return true;
		return false;
	}
	
	private boolean verificaTwoPairs() {
		boolean contaParUm = false;
		boolean contaParDois = false;
		int contaPar = 0;
	
		for (int i = 0; i < getCartas().length; i++) {
			int inicial = getCartas()[i].getNumero();
			for (int j = i; j < getCartas().length; j++)
				if (inicial == getCartas()[j].getNumero())
					contaPar++;
			if (contaPar == 2 && contaParUm == false)
				contaParUm = true;
			else if (contaPar == 2)
				contaParDois = true;
		}
		
		if (contaParUm && contaParDois)
			return true;
		return false;
	}
	
	private boolean verificaPair() {
		if (getPrimeiroPar() != null)
			return true;
		return false;
	}
 	
	private char naipeMaiorQuantidade() {
		char retorno = 'C';
		int maior = copas.length;
		
		if (ouros.length > maior) {
			maior = ouros.length;
			retorno = 'O';
		}
			
		if (paus.length > maior) {
			maior = paus.length;
			retorno = 'P';
		}
		
		if (espadas.length > maior) {
			maior = espadas.length;
			retorno = 'E';
		}
		
		return retorno;
	}
	
	public Carta getHighCard() {
		Carta maior = getCartas()[0];
		for (int i = 1; i < getCartas().length; i++)
			if (getCartas()[i].getNumero() > maior.getNumero())
				maior = getCartas()[i];
		return maior;
	}
	
	private int maiorQuantidadeMesmoNaipe(char naipe) {
		switch (naipe) {
			case 'C':
				return copas.length;
			case 'O':
				return ouros.length;
			case 'P':
				return paus.length;
			case 'E':
				return espadas.length;
			default:
				return 0;
		}
	}
	
	private void defineQuantidadeMesmoNaipe() {
		int copas = 0;
		int ouros = 0;
		int paus = 0;
		int espadas = 0;
		
		for (int i = 0; i < getCartas().length; i++) {
			switch (getCartas()[i].getNaipe()) {
				case 'C':
					copas++;
					break;
				case 'O':
					ouros++;
					break;
				case 'P':
					paus++;
					break;
				case 'E':
					espadas++;
					break;
				default:
					break;
			}
		}
		
		setCopas(new Carta[copas]);
		setOuros(new Carta[ouros]);
		setPaus(new Carta[paus]);
		setEspadas(new Carta[espadas]);
	}
	
	public void agrupaNaipes() {
		defineQuantidadeMesmoNaipe();
		for (int i = 0; i < getCartas().length; i++) {
			switch (getCartas()[i].getNaipe()) {
				case 'C':
					for (Carta carta : copas)
						if (carta == null)
							carta = getCartas()[i];
					break;
				case 'O':
					for (Carta carta : ouros)
						if (carta == null)
							carta = getCartas()[i];
					break;
				case 'P':
					for (Carta carta : paus)
						if (carta == null)
							carta = getCartas()[i];
					break;
				case 'E':
					for (Carta carta : espadas)
						if (carta == null)
							carta = getCartas()[i];
					break;
				default:
					break;
			}
		}
	}
	
	public int[] getTrinca() {
		for (int i = 0; i < getCartas().length; i++) {
			int[] trinca = new int[3];
			int posicaoTrinca = 0;
			for (int j = i; j < getCartas().length; j++) {
				if (getCartas()[i].getNumero() == getCartas()[j].getNumero()) {
					trinca[posicaoTrinca] = j;
					posicaoTrinca++;
				}
			}
			if (posicaoTrinca == 3)
				return trinca;
			posicaoTrinca = 0;
		}
		return null;
	}
	
	public int[] getPrimeiroPar() {
		for (int i = 0; i < getCartas().length; i++) {
			int[] par = new int[2];
			int posicaoPar = 0;
			for (int j = i; j < getCartas().length; j++) {
				if (getCartas()[i].getNumero() == getCartas()[j].getNumero()) {
					par[posicaoPar] = j;
					posicaoPar++;
				}
			}
			if (posicaoPar == 2) 
				return par;
			posicaoPar = 0;
		}
		return null;
	}
	
	public int[] getSegundoPar(int numeroPrimeiroPar) {
		for (int i = 0; i < getCartas().length; i++) {
			if (getCartas()[i].getNumero() != numeroPrimeiroPar) {
				int[] par = new int[2];
				int posicaoPar = 0;
				for (int j = i; j < getCartas().length; j++) {
					if (getCartas()[i].getNumero() == getCartas()[j].getNumero()) {
						par[posicaoPar] = j;
						posicaoPar++;
					}
				}
				if (par.length == 2)
					return par;
			}
		}
		return null;
	}
	
	public Carta getCarta(int posicao) {
		return getCartas()[posicao];
	}
	
	public String getTipo() {
		return tipo;
	}

	public int getForca() {
		return forca;
	}
	
	public Carta[] getCopas() {
		return copas;
	}
	
	public Carta[] getOuros() {
		return ouros;
	}
	
	public Carta[] getPaus() {
		return paus;
	}
	
	public Carta[] getEspadas() {
		return espadas;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setForca(int forca) {
		this.forca = forca;
	}
	
	public void setCopas(Carta[] copas) {
		this.copas = copas;
	}

	public void setOuros(Carta[] ouros) {
		this.ouros = ouros;
	}

	public void setPaus(Carta[] paus) {
		this.paus = paus;
	}

	public void setEspadas(Carta[] espadas) {
		this.espadas = espadas;
	}
}