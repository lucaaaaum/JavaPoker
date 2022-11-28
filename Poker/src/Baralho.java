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
		checaBaralho();
	}

	public void checaBaralho() {
		for (int i = 0; i < cartas.length; i++) {
			for (int j = i+1; j < cartas.length; j++) {
				if (cartas[i] != null &&
					cartas[j] != null &&
					cartas[i].getNaipe() == cartas[j].getNaipe() &&
					cartas[i].getNumero() == cartas[j].getNumero())
					System.out.println("A CARTA ["+i+"] == "+cartas[i].toString()+" É IGUAL À CARTA ["+j+"] == "+cartas[j].toString());
			}
		}
	}
	
	public Carta insereCarta(Carta carta) {
		for (int i = (cartas.length-1); i > 0; i--)
			if (cartas[i] == null) {
				cartas[i] = carta;
				break;
			}
		return carta;
	}
	
	public Carta retiraCarta() {
		Carta retorno = null;
		for (int i = 0; i < cartas.length; i++) {
			if (cartas[i] != null) {
				retorno = cartas[i];
				cartas[i] = null;
				return retorno;
			}
		}
		return null;
	}

	public void imprimeCartas(){
		String[] textos = new String[cartas.length];
		for (int i = 0; i < cartas.length; i++){
			if (cartas[i] != null)
				textos[i] = "["+i+"] == "+cartas[i].toString();				
			else
				textos[i] = "["+i+"] = Não tem carta aqui.";
		}
		Utilitarios.imprimeCaixaTexto(textos);
	}
  
	public void ordenaNull() {
		//imprimeCartas();
		for (int i = 0; i < cartas.length; i++) {
			if (cartas[i] == null)
				for (int j = i; j < cartas.length; j++)
					if (cartas[j] != null) {
						cartas[i] = cartas[j];
						cartas[j] = null;
						break;
					}
		}
		//imprimeCartas();
	}
	
	
	public Carta[] getCartas(){
		return cartas;
	}

	public void setCartas(Carta[] cartas){
		this.cartas = cartas;
	}
}