import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ConseillerBankonet {

	public void launch(){
		System.out.println("****** APPLICATION CONSEILLER BANCAIRE ******");
		
		List<String> actions=new ArrayList<String>
				(Arrays.asList("0. Arr�ter le programme",
								"1. Ouvrir un compte"));
			
		for (String string : actions) {
			System.out.println(string);
		}
		
		System.out.println("Veuillez choisir une action");
	}
	
}
