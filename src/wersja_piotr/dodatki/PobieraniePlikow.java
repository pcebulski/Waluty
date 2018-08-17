package wersja_piotr.dodatki;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PobieraniePlikow {

	public static void main(String[] args) {
		
		try {
			URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/d/d2/Quagga_photo.jpg");
			// samo utworzenie obiektu jeszcze nie pobiera danych
			
		try {
			InputStream	stream = url.openStream();
			Files.copy(stream, Paths.get("obrazek.jpg"));
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}
