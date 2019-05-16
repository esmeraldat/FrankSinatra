package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

	static WebDriver driver;
	public static final String songAddedConfirmation = "Song successfully added";

	public static void verifyButton(String buttonText) {
		WebElement deleteSongButton = driver.findElement(By.xpath("//input [@value='delete this song']"));

		if (deleteSongButton.isDisplayed()) {
			System.out.println("PASS:  Verificación se muestra el botón Delete this Song");
		} else {
			System.out.println("FAIL:  NO se realizó la verificación del botón Delete this Song :(");
			System.exit(-1);
		}
	}

	public static void verifyLink(String linkText) {
		
		// editSongLink: //a[text() ='edit this song']
		WebElement editSongLink = driver.findElement(By.xpath("//a[text() ='edit this song']"));
		
		
		if (editSongLink.isDisplayed()) {
			System.out.println("PASS:  Verificación del link para editar la canción");
		} else {
			System.out.println("FAIL:  NO se realizó la verificación del link para editar la canción :(");
			System.exit(-1);
		}

	}

	public static void verifySongsLikes(int numLikes) {
		WebElement divLike = driver.findElement(By.id("like"));
		String xpath = "p/p";
		String message = "";
		switch (numLikes) {
		case 0:
			xpath = "p";
			message = "Nobody has liked this song so far!";
			break;
		case 1:
			message = "This song has been liked once";
			break;
		default:
			message = String.format("This song has been liked %d times", numLikes);
			break;
		}

		WebElement paragraphLike = divLike.findElement(By.xpath(xpath));
		String paragraphText = paragraphLike.getText();
		if (message.equals(paragraphText)) {
			System.out.println("PASS:  Se encontró el elemento contador de likes");
		} else {
			System.out.println("FAIL:  NO se encontró el elemento contador de likes :(");
			System.exit(-1);
		}
	}

	public static void verifyDatosCancion(String titulo, String duracion, String fecha, String letra) {
		// Elementos de la pagina de song detail
		// songTitle: //h1[text()='Vasos vacios']
		// releaseDateLabel://p[text()='Release Date: 13 October 1985']
		// lengthLabel: //p[text()='Length: 5 minutes 0 seconds']
		// lyricsLabel: //pre[text()='Vasos vacios']
		// likeImage: //a[@href ='/songs/346/edit']
		
		// deleteSongButton: //input [@value='delete this song']
		// logoutLink: href="/logout", //label[text() = ‘log out’]

		int duracionInt = Integer.parseInt(duracion);
		int minutes = duracionInt / 60;
		int seconds = duracionInt % 60;

		WebElement songTitle = driver.findElement(By.xpath(String.format("//h1[text()='%s']", titulo)));
		WebElement lengthLabel = driver
				.findElement(By.xpath(String.format("//p[text()='Length: %d minutes %d seconds']", minutes, seconds)));
		WebElement lyricsLabel = driver.findElement(By.xpath(String.format("//pre[text()='%s']", letra)));

		WebElement divLike = driver.findElement(By.id("like"));
		WebElement buttonLike = divLike.findElement(By.xpath("//form/input"));
		String imageUrl = buttonLike.getCssValue("background-image");

	
		WebElement deleteSongButton = driver.findElement(By.xpath("//input [@value='delete this song']"));
		WebElement logoutLink = driver.findElement(By.xpath("//a[@href ='/logout']	"));

		if (songTitle.isDisplayed() && lengthLabel.isDisplayed() && lyricsLabel.isDisplayed()
				 && deleteSongButton.isDisplayed() && logoutLink.isDisplayed()) {
			System.out.println("PASS:  Verificación de la creación de la canción");
		} else {
			System.out.println("FAIL:  NO se realizó la verificación de la creación de la canción :(");
			System.exit(-1);
		}
	}

	public static void verifyConfirmationMessage() {

		// Elementos de la pagina de song detail
		// confirmationLabel: id = "flash", //label[text() = ‘Song successfully added’]

		WebElement confirmationLabel = driver.findElement(By.id("flash"));

		if (confirmationLabel.isDisplayed()) {
			System.out.println(
					"PASS:  Se encontró el elemento de Mensaje de confirmación de creación exitosa de la canción");
		} else {
			System.out.println(
					"FAIL: NO se encontró el elemento de Mensaje de confirmación de creación exitosa de la canción :(");
			System.exit(-1);

		}

	}

	public static void crearCancion(String title, String length, String date, String lyrics) {
		// ' Elementos pagina inicio:
		// songsLink: linkText = "Songs"
		WebElement songsLink = driver.findElement(By.linkText("Songs"));
		songsLink.click();
		//
		// elementos de pagina de Songs:
		// createSongLink: linkText = "Create a new song"
		//
		WebElement createSongLink = driver.findElement(By.linkText("Create a new song"));
		createSongLink.click();
		// elementos pagina 'Add Song':
		// titleField: id="title"
		// lengthField: id="length"
		// dateField: id="released_on"
		// monthLabel: linkText = "Mes", xpath = "//span[@class =
		// 'ui-datepicker-month']"
		// yearSelect: xpath = "//select[@data-handler='selectYear']"
		// dayLink: xpath = "//a[text() = '26']"
		// lyricsText: id="lyrics"
		// saveSongButton: xpath = "//input [@value='Save Song']"
		WebElement titleField = driver.findElement(By.id("title"));
		WebElement lengthField = driver.findElement(By.id("length"));
		WebElement dateField = driver.findElement(By.id("released_on"));
		WebElement lyricsText = driver.findElement(By.id("lyrics"));
		WebElement saveSongButton = driver.findElement(By.xpath("//input [@value='Save Song']"));
		titleField.sendKeys(title);
		lengthField.sendKeys(length);
		dateField.sendKeys(date); 
		lyricsText.sendKeys(lyrics);
		saveSongButton.click();
	}

	public static void verifyWelcome(String user) {
		// logoutLink: linkText-> "log out ", xpath ->
		// "//a[contains(@href,'logout')]"
		// welcomeLabel: xpath--> "//div[text()="You are now logged in as
		WebElement logoutLink = driver.findElement(By.xpath("//a[contains(@href,'logout')]"));
		WebElement welcomeLabel = driver.findElement(By.xpath("//div[text()='You are now logged in as " + user + "']"));
		if (welcomeLabel.isDisplayed() && logoutLink.isDisplayed()) {
			System.out.println("PASS:  Verificación de los elementos de la pagina de inicio se encontraron");
		} else {
			System.out.println("FAIL:  No se verificaron los elementos de la pagina de inicio NO se encontraron :(");
			System.exit(-1);
		}
	}

	public static void logInBySinatra(String user, String password) {
		// 'Elementos pagina inicio:
		// loginLink: linkText-> "log in ", xpath ->
		// "//a[contains(@href,'login')]"
		// Elementos pagina de login:
		// userField: id="username"
		// passField: id="password"
		// loginButton: xpath = "//input[@value = 'Log In' ]"
		WebElement loginLink = driver.findElement(By.xpath("//a[contains(@href,'login')]"));
		loginLink.click();
		WebElement userField = driver.findElement(By.id("username"));
		WebElement passField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.xpath("//input[@value = 'Log In' ]"));
		userField.sendKeys(user);
		passField.sendKeys(password);
		loginButton.click();
	}

	public static void verifyHomePageElements() {
		// verificar que Carga la página principal de Songs By Sinatra
		// pageTitle: linkText -> "Songs By Sinatra"
		// linksMenu: xpath = //ul
		// sinatraImg: xpath = //img
		// loginLink: linkText-> "log in ", xpath ->
		// "//a[contains(@href,'login')]"
		WebElement pageTitle = driver.findElement(By.xpath("//h1"));
		WebElement linksMenu = driver.findElement(By.xpath("//ul"));
		WebElement sinatraImg = driver.findElement(By.xpath("//img"));
		WebElement loginLink = driver.findElement(By.xpath("//a[contains(@href,'login')]"));
		if (pageTitle.isDisplayed() && linksMenu.isDisplayed() && sinatraImg.isDisplayed() && loginLink.isDisplayed()) {
			System.out.println("PASS:  Elementos de la pagina de inicio se encontraron");
		} else {
			System.out.println("FAIL:  Elementos de la pagina de inicio NO se encontraron :(");
			System.exit(-1);
		}
	}

	public static void navigateTo(String url) {
		// Acceder a la Url
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(url);

	}

}
