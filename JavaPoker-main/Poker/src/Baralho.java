import java.util.Random;

public class Baralho
{
	private Carta[] cartas;

	public Baralho(Carta[] cartas){
		this.cartas = cartas;
	}

	public void embaralhar(){
		Random rd = new Random();
		Carta cartaTemp = null;
		int sorteado = 0;
    
		for (int i = 0; i < cartas.length; i++){
			sorteado = rd.nextInt(cartas.length);
			cartaTemp = cartas[i];
			cartas[i] = cartas[sorteado];
			cartas[sorteado] = cartaTemp;
		}
	}

	public void imprimeCartas(){
		for (Carta carta : cartas){
			if (carta != null)
				carta.imprimeCarta();
		}
	}
  
	public Carta[] getCartas(){
		return cartas;
	}

	public void setCartas(Carta[] cartas){
		this.cartas = cartas;
	}
}