public class Utilitarios
{
	private static int tamanho = 50;
	private static char c = '-';
	private static int sleepTime = 1;
	
	public static void imprimeCaixaTexto(String texto) {
		imprimeLinha();
		imprimeTexto(texto);
		imprimeLinha();
	}
	
	public static void imprimeCaixaTexto(String[] textos) {
		imprimeLinha();
		for (String texto : textos)
			imprimeTexto(texto);
		imprimeLinha();
	}
	
	public static void imprimeCaixaTexto(int[] numeros) {
		imprimeLinha();
		for (int numero : numeros)
			imprimeTexto(Integer.toString(numero));
		imprimeLinha();
	}
	
	public static void imprimeCaixaTexto(int[] numeros, String titulo) {
		imprimeTitulo(titulo);
		for (int numero : numeros)
			imprimeTexto(Integer.toString(numero));
		imprimeLinha();
	}
	
	public static void imprimeCaixaTexto(String texto, String titulo) {
		imprimeTitulo(titulo);
		imprimeTexto(texto);
		imprimeLinha();
	}
	
	public static void imprimeCaixaTexto(String[] textos, String titulo) {
		imprimeTitulo(titulo);
		for (String texto : textos)
			imprimeTexto(texto);
		imprimeLinha();
	}
	
	private static void imprimeLinha() {
		for (int i = 0; i < (tamanho); i++)
		{
			System.out.print(c);
			sleep(sleepTime);
		}		
		System.out.println();
	}
	
	private static void imprimeLinha(int tamanho) {
		for (int i = 0; i < (tamanho); i++) {
			System.out.print(c);
			sleep(sleepTime);
		}	
	}
	
	public static void imprimeTexto(String texto) {
		int tamanhoLinhaAtual = 0;
		
		System.out.print(c+" ");
		for (int i = 0; i < texto.length(); i++) {
			System.out.print(texto.charAt(i));
			tamanhoLinhaAtual++;
			if (tamanhoLinhaAtual == (tamanho-4)) {
				System.out.println(" "+c);
				tamanhoLinhaAtual = 0;
			}
			sleep(sleepTime);
		}
		preencheLinha(tamanho - (tamanhoLinhaAtual + 2));
	}
	
	public static void imprimeTitulo(String titulo) {
		int caracteresEsquerda = (tamanho - titulo.length())/2;
		imprimeLinha(caracteresEsquerda);
		System.out.print(titulo);
		preencheLinha(tamanho - (caracteresEsquerda + titulo.length()), c);
	}
	
	public static void preencheLinha(int tamanho) {
		for (int i = 0; i < (tamanho-1); i++) {
			sleep(sleepTime);
			System.out.print(' ');
		}
		System.out.println(c);
	}
	
	public static void preencheLinha(int tamanho, char caractere) {
		for (int i = 0; i < (tamanho-1); i++) {
			sleep(sleepTime);
			System.out.print(caractere);
		}
		System.out.println(c);
	}
	
	public static boolean perguntaBooleana(String pergunta) {		
		String[] opcoesPositivas = opcoesBooleanasPositivas();
		String[] opcoesNegativas = opcoesBooleanasNegativas();
		
		while (true) {
			imprimeCaixaTexto(pergunta);
			
			String resposta = Teclado.leString();
			for (String opcao : opcoesPositivas)
				if (resposta.equalsIgnoreCase(opcao))
					return true;
			
			for (String opcao : opcoesBooleanasNegativas())
				if (resposta.equalsIgnoreCase(opcao))
					return false;
			
			respostaInvalida();
		}
	}
	
	public static int perguntaNumerica(String pergunta, int limiteInferior, int limiteSuperior) {
		while (true) {
			imprimeCaixaTexto(pergunta);
			
			int resposta = Teclado.leInt();			
			if (resposta < limiteInferior)
				respostaInvalida("NÃšMERO MUITO PEQUENO");
			else if (resposta > limiteSuperior)
				respostaInvalida("NÃšMERO MUITO GRANDE");
			else
				return resposta;
		}
	}
	
	public static int perguntaNumerica(String pergunta, int limiteInferior, int limiteSuperior, String titulo) {
		while (true) {
			imprimeCaixaTexto(pergunta, titulo);
			
			int resposta = Teclado.leInt();			
			if (resposta < limiteInferior)
				respostaInvalida("NÃšMERO MUITO PEQUENO");
			else if (resposta > limiteSuperior)
				respostaInvalida("NÃšMERO MUITO GRANDE");
			else
				return resposta;
		}
	}

	public static String perguntaString(String pergunta) {
			imprimeCaixaTexto(pergunta);
			String resposta = Teclado.leString();
			return resposta;
	}
	
	public static void respostaInvalida() {
		Teclado.leString("RESPOSTA INVÁLIDA. APERTE ENTER PARA TENTAR NOVAMENTE.");
	}
	
	public static void respostaInvalida(String motivo) {
		Teclado.leString("RESPOSTA INVÁLIDA: "+motivo.toUpperCase()+". APERTE ENTER PARA TENTAR NOVAMENTE.");
	}
	
	private static String[] opcoesBooleanasPositivas() {
		String[] retorno = new String[7];
		retorno[0] = "SIM";
		retorno[1] = "SI";
		retorno[2] = "S";
		retorno[3] = "Y";
		retorno[4] = "YES";
		retorno[5] = "YEAH";
		retorno[6] = "CLARO";
		return retorno;
	}
	
	private static String[] opcoesBooleanasNegativas() {
		String[] retorno = new String[5];
		retorno[0] = "NÃO";
		retorno[1] = "NAO";
		retorno[2] = "NO";
		retorno[3] = "NAH";
		retorno[4] = "NEM";
		return retorno;
	}
	
    public static void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch(Exception e){}
    }
    
    public static void limpaConsole() {
    	System.out.println("\u000C");
    }
    
    public static int[] incrementaArray(int[] array) {
    	int[] retorno = new int[array.length+1];
    	for (int i = 0; i < array.length; i++)
    		retorno[i] = array[i];
    	return retorno;
    }
}