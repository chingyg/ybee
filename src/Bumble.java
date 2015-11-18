import java.io.IOException;
import java.util.Scanner;

/*
 *  Author: Ching Hsiang Chen
 *  Last Edit: 10/31/2015
 *  
 *  This class handles user interaction, and is the heart of this program.
 */
public class Bumble
{

	private static void add(Database currentDatabase, String[] word)
	{

		if (word.length == 3)
		{
			currentDatabase.add(word[1], word[2]);

			System.out.println(Print.STATUS_ARROW + "Added");
		}
		else
		{
			parseError();
		}
	}

	private static void delete(Database currentDatabase, String[] word)
	{
		if (word.length == 2)
		{

			// Checks for the dash
			if (word[1].charAt(0) == '-')
			{

				int index = 0;
				try
				{

					index = Integer.parseInt(word[1].substring(1,
							word[1].length()));
					System.out.println(Print.STATUS_ARROW + "Deleted "
							+ currentDatabase.getIndex(index).getUsername());
					currentDatabase.delete(index);
				}
				catch (Exception e)
				{
					// TODO: handle exception
					parseError();
				}
			}
			else
			{
				parseError();
			}
		}
		else
		{
			parseError();
		}

		// Valid command

	}

	/**
	 * Exits this program
	 */
	private static void exit()
	{

		System.out.println(Print.STATUS_ARROW + "Session Ended");

		// System.out.println("[Session Ended]");
		System.exit(0);
	}

	/**
	 * Displays the help message, which will list all commands
	 */
	private static void help()
	{
		System.out
				.println(Print.BORDER_EQUAL
						+ "\n= Command List"
						+ "\n"
						+ Print.BORDER_EQUAL
						+ "\nadd <EMAIL_ADDRESS> <PASSWORD>	-Adds an acount to database"
						+ "\ndelete -<INDEX>			-Delete an account. Use \"ls\" to find the account index"
						+ "\nls				-List all account information"
						+ "\nstart -<N>			-Begin search,where N is the number of searches"
						+ "\nexit				-Exits the program");
	}

	/**
	 * Prints all account emails and corresponding passwords
	 * 
	 * @param currentDatabase
	 *            Database containing accounts
	 */
	private static void ls(Database currentDatabase)
	{

		System.out.println(Print.BORDER_EQUAL + "\n= " + "List of accounts"
				+ "\n" + Print.BORDER_EQUAL);

		for (int i = 0; i < currentDatabase.getNumOfAccounts() - 1; i++)
		{
			if (i % 2 == 0)
			{
				System.out.println(ANSI_COLOR.CYAN + "[" + i + "]" + " "
						+ currentDatabase.getIndex(i).getUsername() + " "
						+ ANSI_COLOR.RESET);
			}
			else
			{
				System.out.println(ANSI_COLOR.PURPLE + "[" + i + "]" + " "
						+ currentDatabase.getIndex(i).getUsername() + " "
						+ ANSI_COLOR.RESET);
			}

		}

	}

	public static void main(String[] args) throws IOException
	{

		/*
		 * this section will take care of standard IOjava
		 */

		printWelcomeMessage();

		// Initialize Database
		Database myDatabase = new Database();
		// BingSearch driver = new BingSearch();

		Scanner scanner = new Scanner(System.in);

		// Read in the commands
		while (scanner.hasNextLine())
		{
			String command = scanner.nextLine();

			String[] word = command.split("\\s+");

			// Process the commands
			switch (word[0])
			{

			case "add":
				add(myDatabase, word);
				break;
			case "delete":
				delete(myDatabase, word);
				break;
			case "exit":
				exit();
				break;
			case "help":
				help();
				break;
			case "ls":
				ls(myDatabase);
				break;
			case "start":
				start(myDatabase, word);

				break;

			default:
				parseError();
				break;
			}

		}
		scanner.close();

	}

	/**
	 * Begin the search
	 * 
	 * @param newDB
	 *            Database containing account information
	 * @param word
	 *            The switch that was used with the start command
	 */
	private static void start(Database newDB, String[] word)
	{

		// Check if command is valid
		if (word.length == 2)
		{

			if (word[1].charAt(0) == '-')
			{
				String tempString = word[1].substring(1, word[1].length());

				int numberOfSearches = 0;
				try
				{
					numberOfSearches = Integer.parseInt(tempString);

					// For now, this will use the firefox driver
					HomePage driver = new HomePage(newDB, numberOfSearches);
					driver.start("firefox");
					exit();
				}
				catch (Exception e)
				{
					// TODO: handle exception
					parseError();
				}
			}
			else
			{
				parseError();
			}
		}

		else
		{
			parseError();
		}

	}

	/**
	 * Prints out an error message when user enters an invalid command
	 */
	private static void parseError()
	{
		System.out.println(Print.STATUS_ARROW
				+ "Command not found. Try \"help\" for a list of commands");
		
		
	}

	/**
	 * Message that is printed when program initially starts
	 */
	private static void printWelcomeMessage()
	{

		System.out.println(Print.BORDER_EQUAL + Print.BORDER_EQUAL_MIDDLE
				+ Print.SCRIPT_NAME + " " + Print.VERSION
				+ Print.BORDER_EQUAL_MIDDLE + "[TSquard]" + "\n"
				+ Print.BORDER_EQUAL);
		
		
		//System.out.println((char)27 + "[34;43mBlue text with yellow background");
	}

}
