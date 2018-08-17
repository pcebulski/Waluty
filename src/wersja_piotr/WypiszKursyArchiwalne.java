package wersja_piotr;

import javax.swing.JOptionPane;

public class WypiszKursyArchiwalne {

	public static void main(String[] args) {

		String data = JOptionPane.showInputDialog("Podaj datę", "2005-01-03");

		Tabela tabela = ObslugaNBP.pobierzKursyHistoryczne(data);
		if (tabela == null) {
			JOptionPane.showMessageDialog(null, "Nie udało się pobrać danych dla tej daty");
		} else {
			System.out.println(tabela);

			for (Waluta waluta : tabela.getWszystkieWaluty()) {
				System.out.println(waluta);
			}
		}
	}

}
