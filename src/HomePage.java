import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;

//import com.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * Contains any functionality you could possibly perform while on the home page
 * 
 * @author Ching Hsiang Chen
 *
 */
public class HomePage
{

	private WebDriver driver;
//	private HtmlUnitDriver htmlDriver;
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

	public HomePage(Database newDB)
	{
		this.driver = null;
		this.myDB = newDB;
		this.numOfSearches = -1;
		myRandom = new RandomUtils();
	}

	public void login(Account account, String driverType)
	{
		Bumble.print.printInfo("Logging in");
		// System.out.println("--->	Logging in");

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
				// System.out.println("--->	Login sucessful");
				Bumble.print.printInfo("Login sucessful");
			}
		}

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

			int maxSearches = this.numOfSearches;
			// Print.printInfo("Automating " + maxSearches + " searches");
			// System.out.println("--->	Automating " + maxSearches +
			// " searches");

			// Do search for X times
			for (int i = 0; i < maxSearches; i++)
			{
				inputField = driver.findElement(By.id("sb_form_q"));

				// For the first character, we don't want a white space
				String character = Character.toString(myRandom.generateChar());

				// while ((character) == " ")
				// {
				// character = Character.toString(myRandom.generateChar());
				// }
				inputField.sendKeys(character, Keys.ENTER);

				// System.out.println("--->	Search #" + (i + 1) +
				// " completed.");
				Bumble.print.printInfo("Search #" + (i + 1) + " completed.");
				this.numOfSearches--;
				sleep(2100);

			}
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

		Bumble.print.printOffersStartBanner();

		// Navigate to home page
		driver.get(HOMEPAGE_URL);

		sleep(3000);

		while (true)
		{

			offerList = getMostRecentOffers();

			// Print out the first offer
			// System.out.println(Print.C_BORDER_HYPHEN + "\n"
			// + offerList.get(0).getText());

			// Check if the first item is a legit offer.
			if (isDailyOffer(offerList.get(0)))
			{

				Bumble.print.printInfo("Legit daily offer");
				Bumble.print.printInfo(offerList.get(0).getText());

				// Opens the offer in a new tab
				offerList.get(0).sendKeys("", selectLinkOpeninNewTab);
				sleep(3000);

			}
			/**
			 * While trivia challenges are considered a completeable offer in
			 * human perspective, this program does not see it as a offer
			 * because it cannot be automated. Not sure if iPhone daily will be
			 * permanent daily offer.
			 */
			else if (offerList.get(0).getText().contains("Trivia")
					|| offerList.get(0).getText()
							.contains("Daily iPhone bonus")
					|| offerList.get(0).getText().contains("quiz")
					|| offerList.get(0).getText().contains("Earn more"))
			{

				// Case when there is an offer after a trivia challenge
				if (isDailyOffer(offerList.get(1)))
				{
					Bumble.print.printInfo("Legit daily offer");
					Bumble.print.printInfo(offerList.get(1).getText());

					// Opens the offer in a new tab
					offerList.get(1).sendKeys("", selectLinkOpeninNewTab);
					sleep(3000);
				}
				else
				{

					// Check the third one for daily offer. There is a case when
					// you have a quiz and a "earn more credits" offer

					if (isDailyOffer(offerList.get(2)))
					{
						Bumble.print.printInfo("Legit daily offer");
						Bumble.print.printInfo(offerList.get(2).getText());

						// Opens the offer in a new tab
						offerList.get(1).sendKeys("", selectLinkOpeninNewTab);
						sleep(3000);
					}
					else
					{
						// No more offers to complete. We're done.
						// sleep(1000);
						break;
					}

				}
			}
			else
			{
				// No more offers to complete. We're done.
				// sleep(1000);
				break;
			}
		}

		Bumble.print.printOffersEndBanner();

	}

	private List<WebElement> getMostRecentOffers()
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
		// System.out.println(Print.C_STATUS_ARROW + "Starting");

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
//		if (driverType.equalsIgnoreCase("html"))
//		{
//
//			// Chrome web client
//			// webClient = new WebClient(BrowserVersion.CHROME);
//
//			htmlDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
//			htmlDriver.setJavascriptEnabled(true);
//
//			/* turn off annoying htmlunit warnings */
//			java.util.logging.Logger.getLogger("com.gargoylesoftware")
//					.setLevel(java.util.logging.Level.OFF);
//
//			// Go to bing rewards login page
//			htmlDriver.get(LOGIN_URL);
//
//			// Login
//			login(myDB.getIndex(0), "html");
//			sleep(2000);
//			pcSearch("html");
//
//		}
	}

	public void start2(String driverType)
	{

		Bumble.print.printStartBanner();
		Bumble.print.printInfo("Starting");
		// System.out.println(Print.STATUS_ARROW + "Starting");

		// FireFox Driver
		if (driverType.equalsIgnoreCase("firefox"))
		{
			// Go through all accounts
			for (int i = 0; i < myDB.getNumOfAccounts() - 1; i++)
			{

				Bumble.print.printInfo("Starting Automation for Account: "
						+ myDB.getIndex(i).getUsername());
				this.driver = new FirefoxDriver();

				// Go to main login page
				this.driver.get(LOGIN_URL);

				login(myDB.getIndex(i), "firefox");
				sleep(2000);
				this.numOfSearches = getNumberOfSearches();
				pcSearch("firefox");
				offers2();
				sleep(2000);
				driver.close();

			}
		}
		// HTML Unit Driver
//		if (driverType.equalsIgnoreCase("html"))
//		{
//
//			// Chrome web client
//			// webClient = new WebClient(BrowserVersion.CHROME);
//
//			htmlDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
//			htmlDriver.setJavascriptEnabled(true);
//
//			/* turn off annoying htmlunit warnings */
//			java.util.logging.Logger.getLogger("com.gargoylesoftware")
//					.setLevel(java.util.logging.Level.OFF);
//
//			// Go to bing rewards login page
//			htmlDriver.get(LOGIN_URL);
//
//			// Login
//			login(myDB.getIndex(0), "html");
//			sleep(2000);
//			pcSearch("html");
//
//		}
		
		Bumble.print.printEndbanner();
	}

	/**
	 * ONLY GETS CALLS WHEN YOU'RE LOGGED IN. This will determine how many
	 * searches you need to reach the maximum.
	 * 
	 * @return number of remaining searches
	 */
	private int getNumberOfSearches()
	{
		// New way

		// Grab the "PC Search" element
		List<WebElement> myList = getMostRecentOffers();
		String PCSearch = "";
		boolean found = false;
		int i = 0;
		while (!found)
		{

			if (myList.get(i).getText().contains("PC search"))
			{
				PCSearch = myList.get(i).getText();
				found = true;
			}
			i++;
		}

		// Case when there's searches remaining
		if (PCSearch.contains("of 15 credits"))
		{

			String[] word = PCSearch.split("\\s+");

			int numOfSearches = (15 - (Integer.parseInt(word[word.length - 4]))) * 2;

			Bumble.print.printInfo("Number of searches to be completed: "
					+ numOfSearches);
			return numOfSearches;
		}
		// Case when there's no searches remaining
		else
		{
			Bumble.print.printInfo("Number of searches to be completed: 0");
			return 0;
		}

	}
}
