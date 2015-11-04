import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
/**
 * Contains any functionality you could possibly perform while on the home page
 * @author Ching Hsiang Chen
 *
 */
public class HomePage
{

	private WebDriver driver;
	private HtmlUnitDriver htmlDriver;
	private Database myDB;
	private int numOfSearches;
	private RandomUtils myRandom;
	
	private final String LOGIN_URL = "https://www.bing.com/fd/auth/signin?action=interactive&"
								+ "provider=windows_live_id&src=rewardssi&perms=&sig=6F8B109"
								+ "B72194E3C9B489B52B5490FE7&return_url=https%3a%2f%2fwww.bin"
								+ "g.com%3a443%2frewards%2fdashboard&Token=1";
	private final String HOMEPAGE_URL = "https://www.bing.com/rewards/dashboard";

	public HomePage(Database newDB, int n)
	{

		this.driver = null;

		this.myDB = newDB;
		this.numOfSearches = n;
		myRandom = new RandomUtils();

	}

	public void login(Account account, String driverType)
	{
		System.out.println("--->	Logging in");

		if (driverType.equalsIgnoreCase("firefox"))
		{
			// Entering user name
			WebElement usernameElement = driver.findElement(By.id("i0116"));
			usernameElement.sendKeys(account.getUsername());

			// Entering password and simulating "ENTER" key
			WebElement passwordElement = driver.findElement(By.id("i0118"));
			passwordElement.sendKeys(account.getPassword(), Keys.ENTER);

			// error checking
			WebElement error = null;

			// try {
			// error = driver.findElement(By.id("idTd_PWD_ErrorMsg_Username"));
			// } catch (NoSuchElementException e) {
			// System.out
			// .println("--->	There was a login error. Please recheck username and password");
			// driver.close();
			//
			// }

			try
			{
				error = driver.findElement(By.id("idTd_Tile_ErrorMsg_Login"));
			}
			catch (NoSuchElementException e)
			{

			}

			// Login error
			if (error != null)
			{
				System.out
						.println("--->	There was a login error. Please recheck username and password");
				driver.close();
			}
			else
			{
				// No login error
				System.out.println("--->	Login sucessful");
			}
		}

		// if (driverType.equalsIgnoreCase("html"))
		// {
		// // Entering user name
		// WebElement usernameElement = htmlDriver.findElement(By.id("i0116"));
		// usernameElement.sendKeys(account.getUsername());
		//
		// // Entering password and simulating "ENTER" key
		// WebElement passwordElement = htmlDriver.findElement(By.id("i0118"));
		// passwordElement.sendKeys(account.getPassword(), Keys.ENTER);
		//
		// // error checking
		// WebElement error = null;
		//
		// // try {
		// // error = driver.findElement(By.id("idTd_PWD_ErrorMsg_Username"));
		// // } catch (NoSuchElementException e) {
		// // System.out
		// //
		// .println("--->	There was a login error. Please recheck username and password");
		// // driver.close();
		// //
		// // }
		//
		// try
		// {
		// error = htmlDriver.findElement(By
		// .id("idTd_Tile_ErrorMsg_Login"));
		// }
		// catch (NoSuchElementException e)
		// {
		//
		// }
		//
		// // Login error
		// if (error != null)
		// {
		// System.out
		// .println("--->	There was a login error. Please recheck username and password");
		// driver.close();
		// }
		// else
		// {
		// // No login error
		// System.out.println("--->	Login sucessful");
		// }
		// }

	}

	/**
	 * Does PC search, which equates to a maximum of 15 points daily.
	 * 
	 * @param driverType
	 *            the driver type
	 */
	public void pcSearch(String driverType)
	{
		if (driverType.equalsIgnoreCase("firefox"))
		{
			// Navigate to the search page
			driver.get("https://www.bing.com/news?q=US+news&FORM=ML11Z9&CREA=ML11Z9");

			// Clears the search bar
			WebElement inputField = driver.findElement(By.id("sb_form_q"));
			inputField.clear();

			System.out.println("--->	Automating 30 searches");

			// Do search for 30 times
			for (int i = 0; i < this.numOfSearches; i++)
			{
				inputField = driver.findElement(By.id("sb_form_q"));

				// For the first character, we don't want a white space
				String character = Character.toString(myRandom.generateChar());

				// while ((character) == " ")
				// {
				// character = Character.toString(myRandom.generateChar());
				// }
				inputField.sendKeys(character, Keys.ENTER);

				System.out.println("--->	Search #" + (i + 1) + " completed.");
				sleep(2100);

			}
		}
		
		

		// if (driverType.equalsIgnoreCase("html"))
		// {
		// // Navigate to the search page
		// htmlDriver
		// .get("https://www.bing.com/news?q=US+news&FORM=ML11Z9&CREA=ML11Z9");
		//
		// // Clears the search bar
		// WebElement inputField = htmlDriver.findElement(By.id("sb_form_q"));
		// inputField.clear();
		//
		// System.out.println("--->	Automating 30 searches");
		//
		// // Do search for 30 times
		// for (int i = 0; i < 30; i++)
		// {
		// inputField = htmlDriver.findElement(By.id("sb_form_q"));
		//
		// // For the first character, we don't want a white space
		// if (i == 0)
		// {
		// String character = Character.toString(myRandom.generateChar());
		//
		// while ((character) == " ")
		// {
		// character = Character.toString(myRandom.generateChar());
		// }
		//
		// // inputField.sendKeys(character, Keys.ENTER);
		// System.out.println(inputField.getText());
		// }
		// else
		// {
		// // inputField.sendKeys(generateRandomString(), Keys.ENTER);
		// String character = Character.toString(myRandom.generateChar());
		// System.out.println(inputField.getText());
		//
		// }
		// System.out.println("--->	Search #" + (i + 1) + " completed.");
		// sleep(2000);
		//
		// }
		// }

	}
	
	/**
	 * Goes through all of the offers and clicks on them.
	 */
	public void offers()
	{
		//TODO This is a work in progress
		
		// Navigate to home page
		driver.get(HOMEPAGE_URL);
		
		try
		{
			System.out.println("--->	Trying to find offers");
			
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
			
			//WebElement offer = driver.findElement(By.className("tile rel blk tile-height"));
			
			//WebElement offer = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/ul/li/a"));
		
			
			//System.out.println("\n" + offer.getText());
			
			// Opens the offer in a new tab
			//offer.sendKeys("",selectLinkOpeninNewTab);
			
			
//			List<WebElement> offerList = driver.findElements(By.xpath("//*[@class='tile rel blk tile-height']"));
//			System.out.println();
			
			
			
			
			
		}
		catch (Exception e)
		{
			// TODO: handle exception
			System.out.println("--->	No Offers Found");
		}

	}

	/**
	 * Sleep or Delays the program for specified milliseconds.
	 * 
	 * @param milliseconds
	 *            Amount of time to delay in milliseconds. 1000 milliseconds = 1
	 *            second
	 */
	public void sleep(int milliseconds)
	{
		try
		{
			// Delay to avoid suspicion
			Thread.sleep(milliseconds);
		}
		catch (java.lang.InterruptedException ie)
		{
			System.out.println(ie);
		}
	}

	public void start(String driverType)
	{
		System.out.println("--->	Starting");

		// FireFox Driver
		if (driverType.equalsIgnoreCase("firefox"))
		{
			for (int i = 0; i < myDB.getNumOfAccounts() - 1; i++)
			{
				this.driver = new FirefoxDriver();

				// Go to main login page
				this.driver
						.get(LOGIN_URL);

				login(myDB.getIndex(i), "firefox");
				sleep(2000);
				pcSearch("firefox");
				//offers();
				sleep(2000);
				driver.close();

			}
		}
		// HTML Unit Driver
		if (driverType.equalsIgnoreCase("html"))
		{

			// Chrome web client
			// webClient = new WebClient(BrowserVersion.CHROME);

			htmlDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
			htmlDriver.setJavascriptEnabled(true);

			/* turn off annoying htmlunit warnings */
			java.util.logging.Logger.getLogger("com.gargoylesoftware")
					.setLevel(java.util.logging.Level.OFF);

			// Go to bing rewards login page
			htmlDriver
					.get(LOGIN_URL);

			// Login
			login(myDB.getIndex(0), "html");
			sleep(2000);
			pcSearch("html");

		}
	}
}
