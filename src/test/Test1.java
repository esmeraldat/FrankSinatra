package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 extends BaseTest{

	
	public static void main(String[] args) {
		final String titulo = "Vasos vacios";
		final String duracion = "300";
		final String fecha = "10/13/1985";
		final String letra = "Siempre habrá vasos vacios, con agua de la ciudad.";
		final String linkText = "edit this song";
		
		navigateTo("http://songs-by-sinatra.herokuapp.com/"); //Ready
		verifyHomePageElements(); //Ready
		logInBySinatra("frank", "sinatra"); //Ready
		verifyWelcome("frank"); //Ready
		
		crearCancion(titulo, 
				duracion, 
				fecha, 
				letra); //Ready
		verifyConfirmationMessage();
		verifyDatosCancion(titulo, 
				duracion, 
				fecha, 
				letra);
		verifySongsLikes(0);
		verifyLink(linkText);
		verifyButton("delete this song");
		
	}

	
}
