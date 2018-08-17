package wersja_piotr;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import wersja_piotr.Waluta;

class WalutaTest {
	
	private Waluta euro = new Waluta("EUR", "euro", new BigDecimal("4.2000"));
	private Waluta dolar = new Waluta("USD", "dolar", new BigDecimal("3.0000"));

	@Test
	void testToString() {
		Waluta euro = new Waluta("EUR", "euro", new BigDecimal("4.2000"));
		assertEquals("EUR (euro): 4.2000", euro.toString());
	}

	@Test
	void testPrzelicznaZlote() {
		BigDecimal kwota = new BigDecimal("100.00");
		BigDecimal wynik = euro.przeliczNaZlote(kwota);
		
		BigDecimal wynikOczekiwany = new BigDecimal("420.00");
		
		// Asercje języka Java: możliwość sprawdzenia warunku logicznego
		// assert wynikOczekiwany.equals(wynik);
		
		// W praktyce w testach używa się gotowych asercji, np. assertEquals
		assertEquals(wynikOczekiwany, wynik);
		
		// normalnie w testach nie używa się printów, a ten jest tylko po to, aby pokazać że nie bylo wyjątku
		System.out.println("Było OK");
	}
	
	@Test
	void testPrzelicznaWalute() {
		BigDecimal kwota = new BigDecimal(420.00);
		BigDecimal wynik = euro.przeliczNaWalute(kwota);
		BigDecimal wynikOczekiwany = new BigDecimal("100.00");
		System.out.println(euro.przeliczNaWalute(kwota));
		assertEquals(wynikOczekiwany, wynik);
		
	}

	@Test
	void testPrzelicznaWaluteZaokraglanie1() {
		BigDecimal kwota = new BigDecimal(100.00);
		BigDecimal wynik = dolar.przeliczNaWalute(kwota);
		BigDecimal wynikOczekiwany = new BigDecimal("33.33");
		System.out.println(dolar.przeliczNaWalute(kwota));
		assertEquals(wynikOczekiwany, wynik);
		
	}
	
	@Test
	void testPrzelicznaWaluteZaokraglanie2() {
		BigDecimal kwota = new BigDecimal(200.00);
		BigDecimal wynik = dolar.przeliczNaWalute(kwota);
		BigDecimal wynikOczekiwany = new BigDecimal("66.67");
		System.out.println(dolar.przeliczNaWalute(kwota));
		assertEquals(wynikOczekiwany, wynik);
		
	}
}
