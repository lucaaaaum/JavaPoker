public class Dealer extends Jogador
{
  private Baralho baralho;

  public Dealer(String nome, int fichas, Baralho baralho){
    super(nome, fichas);
    this.baralho = baralho;
  }
  
  public void embaralhar() {
	  baralho.embaralhar();
  }
  
  public Carta puxaCarta() {
	  Carta[] cartas = baralho.getCartas();
	  Carta retorno = null;
	  for (int i = 0; i < cartas.length; i++) {
		  if (cartas[i] != null) {
			  retorno = cartas[i];
			  cartas[i] = null;
		  }
	  }
	  return retorno;
  }
  
  public void devolveCarta(Carta carta) {
	  Carta[] cartas = baralho.getCartas();
	  for (int i = 0; i < cartas.length; i++) {
		  if (cartas[i] != null) {
			  cartas[i-1] = cartas[i];
		  }
	  }
	  cartas[cartas.length-1] = carta;
	  baralho.setCartas(cartas);
  }
  
  public Baralho getBaralho() {
	  return baralho;
  }
  
  public void setBaralho(Baralho baralho) {
	  this.baralho = baralho;
  }
}