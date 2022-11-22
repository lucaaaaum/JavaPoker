public class Dealer
{
	private Baralho baralho;

	public Dealer(){
		this.baralho = alimentaBaralho();
	}
  
	public void embaralhar() {
		baralho.embaralhar();
	}
  
	public void ordenar() {
		baralho.ordenaNull();
	}
  
	public Carta retiraCarta() {
		return baralho.retiraCarta();
	}
  
	public void devolveCarta(Carta carta) {
		baralho.insereCarta(carta);
	}
  
	public Baralho alimentaBaralho() {
		Carta[] cartas = new Carta[52];	
		char[] naipes = new char[] { 'C', 'O', 'P', 'E'};
		int posicao = 0;
		for (char naipe : naipes){
			for (int i = 2; i <= 14; i++){
				cartas[posicao] = new Carta(naipe, i);
				posicao++;
			}
		}
		return new Baralho(cartas);
	}
	
	public Baralho getBaralho() {
		return baralho;
	}
  
	public void setBaralho(Baralho baralho) {
		this.baralho = baralho;
	}
}