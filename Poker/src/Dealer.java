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
  
  public Baralho getBaralho() {
	  return baralho;
  }
  
  public void setBaralho(Baralho baralho) {
	  this.baralho = baralho;
  }
}