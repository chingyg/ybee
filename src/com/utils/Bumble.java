package com.utils;

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

	// Static object that gets accessed everywhere
	// This is the main way things get printed to the console
	static Print2 print;

	private static void add(Database currentDatabase, String[] word)
	{

		if (word.length == 3)
		{
			currentDatabase.add(word[1], word[2]);

			// System.out.println(Print.STATUS_ARROW + "Added");
			print.printStatus("Added " + word[1]);
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

					print.printStatus("Deleted "
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

	}

	/**
	 * Exits this program
	 */
	private static void exit()
	{

		print.printExit();
		System.exit(0);
	}

	/**
	 * Displays the help message, which will list all commands
	 */
	private static void help()
	{

		print.printHelp();
	}

	/**
	 * Prints all account emails and corresponding passwords
	 * 
	 * @param currentDatabase
	 *            Database containing accounts
	 */
	private static void ls(Database currentDatabase)
	{
		print.printlsBanner();

		for (int i = 0; i < currentDatabase.getNumOfAccounts() - 1; i++)
		{

			print.ls(i, currentDatabase.getIndex(i).getUsername());

		}

	}

	public static void main(String[] args) throws IOException
	{

		// Check for special switches
		if (args.length > 0)
		{
			for (int i = 0; i < args.length; i++)
			{
				if (args[i].equals("-c"))
				{
					// Set Color Syntax Flag
					print = new PrintColor();

				}

			}
		}
		else
		{
			// Black and White prints
			print = new PrintNoColor();
		}

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
				start(myDatabase);

				break;

			default:
				parseError();
				break;
			}

		}
		scanner.close();

	}

	private static void start(Database newDB)
	{
		try
		{
			// For now, this will use the firefox driver
			HomePage driver = new HomePage(newDB);
			driver.start2("firefox");
			exit();
		}
		catch (Exception e)
		{
			print.printError(e.toString());
			// System.out.println(e);
		}
	}

	/**
	 * Prints out an error message when user enters an invalid command
	 */
	private static void parseError()
	{

		print.printInvalidCommand();

	}

	/**
	 * Message that is printed when program initially starts
	 */
	private static void printWelcomeMessage()
	{

		print.printWelcome();

	}

}
