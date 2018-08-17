package wersja_piotr;

public class WypiszBierzaceKursy {

	public static void main(String[] args) {
		
		Tabela tabela = ObslugaNBP.pobierzBiezaceKursy();
		System.out.println(tabela);
		
		for (Waluta waluta : tabela.getWszystkieWaluty()) {
			System.out.println(waluta);
		}
		
		
		
		

	}

}
