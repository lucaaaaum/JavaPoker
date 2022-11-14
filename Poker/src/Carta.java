public class Carta
{
	private char naipe;
	private int numero;

	public Carta(char naipe, int numero){
		this.naipe = naipe;
		this.numero = numero;
	}
  
	public void imprimeCarta() {
		System.out.println(toString());
	}
	
	public String toString(){
		String nome = "";
		switch (numero){
			case 1:
				nome += "Ás";
				break;
			case 11:
				nome += "Valete";
				break;
			case 12:
				nome += "Rainha";
				break;
			case 13:
				nome += "Rei";
				break;
			default:
				nome += (numero);
				break;
		}

		nome += " de ";

		if (numero != 14){
			switch (naipe){
				case 'C':
					nome += "Copas";
					break;
				case 'O':
					nome += "Ouro";
					break;
				case 'P':
					nome += "Paus";
					break;
				case 'E':
					nome += "Espadas";
					break;
			}
		}
		
		return nome;
	}
  
	public char getNaipe(){
		return naipe;
	}

	public int getNumero(){
		return numero;
	}

	public void setNaipe(char naipe){
		this.naipe = naipe;
	}

	public void setNumero(int numero){
		this.numero = numero;
	}

}