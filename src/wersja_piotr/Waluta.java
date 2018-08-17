package wersja_piotr;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Waluta {

	// obiekt tej klasy reprezentuje kurs z tabeli walut NBP.
	// klasa jest immutable.

	private final String kod;
	private final String nazwa;
	private final BigDecimal kurs;

	public Waluta(String kod, String nazwa, BigDecimal kurs) {
		this.kod = kod;
		this.nazwa = nazwa;
		this.kurs = kurs;
	}

	public String getKod() {
		return kod;
	}

	public String getNazwa() {
		return nazwa;
	}

	public BigDecimal getKurs() {
		return kurs;
	}
	
	public BigDecimal przeliczNaZlote(BigDecimal kwota) {
		return kwota.multiply(kurs).setScale(2);
	}
	
	@Override
	public String toString() {
		
		return kod +" (" + nazwa +"): " + kurs;
	}
	
	public BigDecimal przeliczNaWalute(BigDecimal kwota) {
		return kwota.divide(kurs, 2, RoundingMode.HALF_UP);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kod == null) ? 0 : kod.hashCode());
		result = prime * result + ((kurs == null) ? 0 : kurs.hashCode());
		result = prime * result + ((nazwa == null) ? 0 : nazwa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Waluta other = (Waluta) obj;
		if (kod == null) {
			if (other.kod != null)
				return false;
		} else if (!kod.equals(other.kod))
			return false;
		if (kurs == null) {
			if (other.kurs != null)
				return false;
		} else if (!kurs.equals(other.kurs))
			return false;
		if (nazwa == null) {
			if (other.nazwa != null)
				return false;
		} else if (!nazwa.equals(other.nazwa))
			return false;
		return true;
	}
	
	
}
