import com.github.cyborgmg.cyberpontua.PntFrase;

public class Main {

	public static void main(String[] args) {
		
		/*
		 * getInstace(double match, double order)
		 * match Cruzamento entre palavras
		 * order Ordem em q as palavras estão
		 */
		
		/*
		 * getPonto(String frasePrincipal, String fraseCompara)
		 * frasePrincipal Frase base de comparação (Asume que é a correta)
		 * fraseCompara Frase a ser comparada
		 */
		
		double pnt =  PntFrase.getInstace(80,20).getPonto("Renata Sooares Maia", "Renata Soares Maia");
		
		System.out.println(pnt);

	}

}