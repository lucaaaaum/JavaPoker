public class Mao
{
	private Carta[] cartas;

	public Mao(Carta[] cartas){
		this.cartas = cartas; //ordenaCartas(cartas);
	}

	public void ordenaCartas() {
		ordenaNumericamente();
		ordenaNaipe();
	}
  
	private void ordenaNumericamente() {
		Carta menor = cartas[0];
		Carta temp = null;
	  
		for (int i = 1; i < cartas.length; i++) {
			if (menor.getNumero() < cartas[i].getNumero()) {
				temp = menor;
				menor = cartas[i];
				cartas[i] = temp;
			}
		}
	}
  
	private void ordenaNaipe() {
		Carta menor = cartas[0];
		Carta temp = null;
	  
		for (int i = 1; i < cartas.length; i++) {
			if (charParaInt(menor.getNaipe()) > charParaInt(cartas[i].getNaipe()) && menor.getNumero() == cartas[i].getNumero()) {
				temp = menor;
				menor = cartas[i];
				cartas[i] = temp;
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
  
	public void insereCarta(Carta carta){
		for (int i = 0; i < cartas.length; i++)
			if (cartas[i] == null) {
				cartas[i] = carta;
				break;
			}
	}
	
	public void trocaCarta(int indiceCarta, Carta carta) {
		cartas[indiceCarta] = carta;
	}
  
	public String verificaTipo() {
		if (verificaRoyalFlush())
			return "Royal Flush";
	  
		return "Uma porcaria";
	}
  
	private boolean verificaRoyalFlush() {
		return true;  
	}
  
	private boolean verificaStraightFlush() {
		return true;
	}
  
	private int quantidadeMesmoNaipe() {
		int quantidade = 0;
		char[] naipes = new char[cartas.length];
	  
		for (int i = 0; i < cartas.length; i++) {
			naipes[i] = cartas[i].getNaipe();  
		}
	  
		for (char naipe : naipes) {
			if (naipes[0] != naipe)
				quantidade++;
		}
	  
		return quantidade;
	}
  
	public void imprimeMao(){
		for (Carta c : cartas){
			c.imprimeCarta();
		}
	}
  
	public Carta[] getCartas(){
		return cartas;
	}

	public void setCartas(Carta[] cartas){
		this.cartas = cartas;
	}
}