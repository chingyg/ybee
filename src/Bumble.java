import java.io.IOException;
import java.util.Scanner;

/*
 *  Author: Ching Hsiang Chen
 *  Last Edit: 10/31/2015
 */
public class Bumble
{

	public static void add(Database currentDatabase, String[] word)
	{

		if (word.length == 3)
		{
			currentDatabase.add(word[1], word[2]);

			System.out.println("--->	Added");
		}
		else
		{
			parseError();
		}
	}

	public static void delete(Database currentDatabase, String[] word)
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
					System.out.println("--->	Deleted "
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

	public static void exit()
	{

		System.out.println("--->	Session Ended");
		System.exit(0);
	}

	/**
	 * Displays the help message, which will list all commands
	 */
	public static void help()
	{
		System.out
				.println("====== Command List ======\nadd email_address Password	-Adds an acount to database\ndelete -index			-Delete an account. Use \"ls\" to find the account index\nls				-list all account information\nstart -n			-begin bing search,where n is the number of searches\nexit				-exits the program");
	}

	public static void main(String[] args) throws IOException
	{

		/*
		 * this section will take care of standard IOjava
		 */

		System.out.println("------ chingyg Bing Rewards Ex v1.3------");

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
				System.out.println("====== List of accounts ======");
				for (int i = 0; i < myDatabase.getNumOfAccounts() - 1; i++)
				{
					System.out.println("[" + i + "]" + " "
							+ myDatabase.getIndex(i).getUsername() + " "
							+ myDatabase.getIndex(i).getPassword());
				}
				break;
			case "start":
				start(myDatabase, word);

				break;

			default:
				System.out
						.println("Command not found. Try \"help\" for a list of commands");
				break;
			}

		}
		scanner.close();

	}

	public static void start(Database newDB, String[] word)
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

	public static void parseError()
	{
		System.out
				.println("Command not found. Try \"help\" for a list of commands");
	}

}
