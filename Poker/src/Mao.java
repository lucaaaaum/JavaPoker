public class Mao extends Baralho
{
	String tipo;
	int forca;
	
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
		ordenaNumericamente();
		//ordenaNaipe();
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
	
	private void ordenaNumericamente() {
		Carta temp = null;
		
		for (int i = 0; i < getCartas().length; i++)
			for (int j = (i+1); j < getCartas().length; j++) {
				if (getCartas()[i].getNumero() > getCartas()[j].getNumero()) {
					temp = getCartas()[i];
					getCartas()[i] = getCartas()[j];
					getCartas()[j] = temp;
				}
			}
	}
  
	private void ordenaNaipe() {
		Carta menor = getCartas()[0];
		Carta temp = null;
	  
		for (int i = 1; i < getCartas().length; i++) {
			if (charParaInt(menor.getNaipe()) > charParaInt(getCartas()[i].getNaipe()) && menor.getNumero() == getCartas()[i].getNumero()) {
				temp = menor;
				menor = getCartas()[i];
				getCartas()[i] = temp;
			}
		}
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
		if (verificaRoyalFlush()) {
			setTipo("ROYAL FLUSH");
			setForca(100);
		} else if (verificaStraightFlush()) {
			setTipo("STRAIGHT FLUSH");
			setForca(90);
		} else if (verificaQuadra()) {
			setTipo("FOUR OF A KIND");
			setForca(80);
		} else if (verificaCor()) {
			setTipo("FLUSH");
			setForca(70);
		} else if (verificaSequencia()) {
			setTipo("SEQUÊNCIA");
			setForca(60);
		}
			
	}
  
	private boolean verificaRoyalFlush() {
		if (maiorQuantidadeMesmoNaipe() == 5) {
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
		if (maiorQuantidadeMesmoNaipe() == 5) {
			int inicial = getCartas()[0].getNumero();
			for (int i = 1; i < getCartas().length; i++) {
				if (getCartas()[i].getNumero() != (inicial + i)) {
					System.out.println(getCartas()[i].getNumero());
					System.out.println(inicial+i);
					return false;					
				}
			}
			return true;
		}
		return false;
	}

	private boolean verificaQuadra() {
		if (maiorQuantidadeMesmoNaipe() == 4) {
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

	private boolean verificaCor() {
		if (maiorQuantidadeMesmoNaipe() == 5)
			return true;
		return false;
	}

	private boolean verificaSequencia() {
		int inicial = getCartas()[0].getNumero();
		for (int i = 1; i < getCartas().length; i++) {
			if (getCartas()[i].getNumero() != (inicial + i))
				return false;
		}
		return true;
	}
  
	private int maiorQuantidadeMesmoNaipe() {
		int[] quantidades = new int[4];
		int posicao = 0;
		
		for (int i = 0; i < getCartas().length; i++)
			for (int j = i; j < getCartas().length; j++)
				if (getCartas()[i].getNaipe() == getCartas()[j].getNaipe())
					quantidades[getCartas()[i].naipeToInt()]++;
		
		int maior = quantidades[0];
		for (int i = 1; i < quantidades.length; i++)
			if (quantidades[i] > maior)
				maior = quantidades[i];
		
		return maior;
	}
	
	public String getTipo() {
		return tipo;
	}

	public int getForca() {
		return forca;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setForca(int forca) {
		this.forca = forca;
	}
	
}