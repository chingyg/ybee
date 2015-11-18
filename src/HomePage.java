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
 * 
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
	 * Goes through all of the offers and attempts to complete legitimate daily
	 * offers. This is the iterative and ugly way to do this. Recursive method
	 * would be more elegant.
	 */
	public void offers()
	{

		boolean end = false;
		boolean restart = true;
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		// TODO This is a work in progress

		// Navigate to home page
		driver.get(HOMEPAGE_URL);

		try
		{
			// WebElement offer =
			// driver.findElement(By.className("tile rel blk tile-height"));
			// WebElement offer =
			// driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/ul/li/a"));
			// System.out.println("\n" + offer.getText());

			System.out.println("==============================" + "\n= Offers"
					+ "\n==============================");

			while (!end)
			{
				List<WebElement> offerList = null;

				if (restart)
				{
					offerList = getMostRecentOffers();
					restart = false;
				}

				for (int i = 0; i < offerList.size(); i++)
				{
					System.out.println("------------------------------\n"
							+ offerList.get(i).getText());
					if (isDailyOffer(offerList.get(i)))
					{
						System.out.println("\nLegit daily offer");

						/*
						 * When you click on an offer, the list of offers on the
						 * webpage gets updated in real time. Need to redeclare
						 * the list of offers again
						 */

						// Opens the offer in a new tab
						offerList.get(i).sendKeys("", selectLinkOpeninNewTab);
						sleep(1500);
						restart = true;
						break;

					}
					else
					{
						/*
						 * If the first element is not an offer, then it either
						 * means you've completed all offers, or there is no
						 * offer.
						 */

						if (i == 0)
						{
							end = true;
							break;
						}
						System.out.println("\nNOT daily offer");
					}

					// We've reached the end of the offerList
					if (i == (offerList.size() - 1))
					{
						end = true;
					}
					sleep(1000);

				}

			}

			System.out.println("=============================="
					+ "\n= End of Offers" + "\n==============================");

		}
		catch (Exception e)
		{
			// TODO: handle exception
			System.out.println("--->	" + e.toString());
		}

	}

	/**
	 * Goes through all of the offers and attempts to complete legitimate daily
	 * offers.
	 */
	public void offers2()
	{
		/**
		 * So the premise of this algorithm is that completeable offers are
		 * always pushed to the top of the list. After you've clicked on one,
		 * the list just gets updated in real time, and the next offer (if it
		 * exist) after the one you just pressed will be pushed to the top. You
		 * should be able to just check the top of the list to verify whether
		 * you've completed all offers or not
		 */
		List<WebElement> offerList = null;
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

		System.out.println(Print.BORDER_EQUAL + Print.BORDER_EQUAL_MIDDLE
				+ "Offers" + "\n" + Print.BORDER_EQUAL);

		// Navigate to home page
		driver.get(HOMEPAGE_URL);

		sleep(1000);

		while (true)
		{

			offerList = getMostRecentOffers();

			// Print out the first offer
			System.out.println(Print.BORDER_HYPHEN + "\n"
					+ offerList.get(0).getText());
			// Check if the first item is a legit offer.
			if (isDailyOffer(offerList.get(0)))
			{
				System.out.println("\nLegit daily offer");

				// Opens the offer in a new tab
				offerList.get(0).sendKeys("", selectLinkOpeninNewTab);
				sleep(1500);

			}
			/**
			 * While trivia challenges are considered a completeable offer in
			 * human perspective, this program does not see it as a offer
			 * because it cannot be automated.
			 */
			else if (offerList.get(0).getText().contains("Trivia challenge")
					|| offerList.get(0).getText()
							.contains("Daily iPhone bonus"))
			{

				// Case when there is an offer after a trivia challenge
				if (isDailyOffer(offerList.get(1)))
				{
					System.out.println("\nLegit daily offer");

					// Opens the offer in a new tab
					offerList.get(1).sendKeys("", selectLinkOpeninNewTab);
					sleep(1500);
				}
				else
				{
					// No more offers to complete. We're done.
					break;
				}
			}
			else
			{
				break;
			}
		}

		System.out.println(Print.BORDER_EQUAL + Print.BORDER_EQUAL_MIDDLE
				+ "End of Offers" + "\n" + Print.BORDER_EQUAL);

	}

	public List<WebElement> getMostRecentOffers()
	{
		List<WebElement> offerList = driver.findElements(By
				.xpath("//*[@class='tile rel blk tile-height']"));
		return offerList;
	}

	/**
	 * Determines whether an offer will give you points when clicked.
	 * 
	 * @param element
	 *            the offer element in html
	 * @return true if its a daily offer in which you can earn points, false
	 *         otherwise.
	 */
	public boolean isDailyOffer(WebElement element)
	{
		String offer = element.getText();

		// check for the correct offers first(there are very few conditions)
		if (offer.contains("0 of 1 credit") || offer.contains("A special gift"))
		{
			return true;
		}
		else if (offer.contains("Trivia challenge")
				|| offer.contains("Invite friends")
				|| offer.contains("Maintain Gold")
				|| offer.contains("PC search")
				|| offer.contains("Mobile search"))

		{
			// System.out.println("Caught second condition");
			return false;
		}
		else
		{
			return false;
		}

		// one way to do it
		// return !((offer.contains("Trivia challenge") ||
		// offer.contains("Maintain Gold") ||
		// offer.contains("Invite friends")||
		// offer.contains("Invite friends")||
		// offer.contains("Sweepstakes Entry")||
		// offer.contains("You can redeem")));

		// another way to do it
		// return (element.getText().contains("Maintain Gold"));

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
		System.out.println(Print.STATUS_ARROW + "Starting");

		// FireFox Driver
		if (driverType.equalsIgnoreCase("firefox"))
		{
			for (int i = 0; i < myDB.getNumOfAccounts() - 1; i++)
			{
				this.driver = new FirefoxDriver();

				// Go to main login page
				this.driver.get(LOGIN_URL);

				login(myDB.getIndex(i), "firefox");
				sleep(2000);
				pcSearch("firefox");
				offers2();
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
			htmlDriver.get(LOGIN_URL);

			// Login
			login(myDB.getIndex(0), "html");
			sleep(2000);
			pcSearch("html");

		}
	}
}
