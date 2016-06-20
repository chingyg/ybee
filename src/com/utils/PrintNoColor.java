package com.utils;

public class PrintNoColor extends Print2
{

	private final String BORDER_HYPHEN_MIDDLE = "\n- ";

	private final String BORDER_EQUAL = "============================================================";

	private final String BORDER_EQUAL_MIDDLE = "\n= ";

	private final String BORDER_HYPHEN = "------------------------------------------------------------";

	private final String STATUS_ARROW = "--->	";

	private final String STATUS_DEBUG = "[DEBUG]		";
	private final String STATUS_INFO = "[INFO]		";
	private final String STATUS_ERROR = "[ERROR]		";

	@Override
	public void printDebug(String message)
	{
		String messageOut = STATUS_DEBUG + message;

		System.out.println(messageOut);

	}

	@Override
	public void printInfo(String message)
	{
		String messageOut = STATUS_INFO + message;
		System.out.println(messageOut);

	}

	@Override
	public void printError(String message)
	{
		String messageOut = STATUS_ERROR + message;
		System.out.println(messageOut);

	}

	@Override
	public void printInvalidCommand()
	{
		System.out.println(STATUS_ERROR
				+ "Command not found. Try \"help\" for a list of commands");

	}

	@Override
	public void printStatus(String statusMessage)
	{
		String message = statusMessage;
		System.out.println(message);

	}

	@Override
	public void printHelp()
	{
		System.out
				.println(BORDER_EQUAL
						+ "\n= Command List"
						+ "\n"
						+ BORDER_EQUAL
						+ "\nadd <EMAIL_ADDRESS> <PASSWORD>	-Adds an acount to database"
						+ "\ndelete -<INDEX>			-Delete an account. Use \"ls\" to find the account index"
						+ "\nls				-List all account information"
						+ "\nstart 				-Begin search,where N is the number of searches"
						+ "\nexit				-Exits the program");

	}

	@Override
	public void printWelcome()
	{
		System.out.println(BORDER_EQUAL + BORDER_EQUAL_MIDDLE + SCRIPT_NAME
				+ " " + VERSION + BORDER_EQUAL_MIDDLE + "[TSquard]" + "\n"
				+ BORDER_EQUAL);

		System.out
				.println("\n     %           %"
						+ "\n         %           %"
						+ "\n            %           %"
						+ "\n               %          %"
						+ "\n                 %          %"
						+ "\n                   %          %                   :::"
						+ "\n                    %          %                ::::::"
						+ "\n                 %%%%%%  %%%%%%%%%            ::::::::"
						+ "\n              %%%%%ZZZZ%%%%%%   %%%ZZZZ     ::::::::::         ::::::"
						+ "\n             %%%ZZZZZ%%%%%%%%%%%%%%ZZZZZZ  :::::::::::    :::::::::::::::::"
						+ "\n             ZZZ%ZZZ%%%%%%%%%%%%%%%ZZZZZZZ::::::::::***:::::::::::::::::::::"
						+ "\n          ZZZ%ZZZZZZ%%%%%%%%%%%%%%ZZZZZZZZZ::::::***:::::::::::::::::::::::"
						+ "\n        ZZZ%ZZZZZZZZZZ%%%%%%%%%%ZZZZZZ%ZZZZ:::***:::::::::::::::::::::::"
						+ "\n       ZZ%ZZZZZZZZZZZZZZZZZZZZZZZ%%%%% %ZZZ:**::::::::::::::::::::::"
						+ "\n      ZZ%ZZZZZZZZZZZZZZZZZZZ%%%%% | | %ZZZ *:::::::::::::::::::"
						+ "\n      Z%ZZZZZZZZZZZZZZZ%%%%%%%%%%%%%%%ZZZ::::::::::::::::::::::::::"
						+ "\n       ZZZZZZZZZZZ%%%%%ZZZZZZZZZZZZZZZZZ%%%%:::ZZZZ:::::::::::::::::"
						+ "\n         ZZZZ%%%%%ZZZZZZZZZZZZZZZZZZ%%%%%ZZZ%%ZZZ%ZZ%%*:::::::::::"
						+ "\n            ZZZZZZZZZZZZZZZZZZ%%%%%%%%%ZZZZZZZZZZ%ZZ%:::*:::::::"
						+ "\n            *:::%%%%%%%%%%%%%%%%%%%%%%%ZZZZZZZZZZ%%%*::::*::::"
						+ "\n          *:::::::%%%%%%%%%%%%%%%%%%%%%%%ZZZZZ%%      *:::Z"
						+ "\n         **:ZZZZ:::%%%%%%%%%%%%%%%%%%%%%%%%%%%ZZ      ZZZZZ"
						+ "\n        *:ZZZZZZZ       %%%%%%%%%%%%%%%%%%%%%ZZZZ    ZZZZZZZ"
						+ "\n       *::::ZZZZZZ         %%%%%%%%%%%%%%%ZZZZZZZ      ZZZ"
						+ "\n        *::ZZZZZZ           Z%%%%%%%%%%%ZZZZZZZ%%"
						+ "\n          ZZZZ              ZZZZZZZZZZZZZZZZ%%%%%"
						+ "\n                           %%%ZZZZZZZZZZZ%%%%%%%%"
						+ "\n                          Z%%%%%%%%%%%%%%%%%%%%%"
						+ "\n                          ZZ%%%%%%%%%%%%%%%%%%%"
						+ "\n                          %ZZZZZZZZZZZZZZZZZZZ"
						+ "\n                          %%ZZZZZZZZZZZZZZZZZ"
						+ "\n                           %%%%%%%%%%%%%%%%"
						+ "\n                            %%%%%%%%%%%%%"
						+ "\n                             %%%%%%%%%"
						+ "\n                              ZZZZ"
						+ "\n                              ZZZ"
						+ "\n                             ZZ"
						+ "\n                            Z");

	}

	@Override
	public void ls(int index, String username)
	{
		String messageOut = "[" + index + "] " + username;
		System.out.println(messageOut);

	}

	@Override
	public void printExit()
	{
		String messageOut = "Session ended";
		System.out.println(messageOut);

	}

	@Override
	public void printOffersStartBanner()
	{
		System.out.println(BORDER_EQUAL + BORDER_EQUAL_MIDDLE + "Offers" + "\n"
				+ BORDER_EQUAL);

	}

	@Override
	public void printlsBanner()
	{
		String messageOut = BORDER_EQUAL + BORDER_EQUAL_MIDDLE
				+ " List of Accounts\n" + BORDER_EQUAL;
		System.out.println(messageOut);

	}

	@Override
	public void printStartBanner()
	{
		System.out.println(BORDER_EQUAL + BORDER_EQUAL_MIDDLE
				+ "Starting Bing Automation" + "\n" + BORDER_EQUAL);

	}

	@Override
	public void printOffersEndBanner()
	{
		System.out.println(BORDER_EQUAL + BORDER_EQUAL_MIDDLE + "End of Offers" + "\n"
				+ BORDER_EQUAL);
		
	}

	@Override
	public void printEndbanner()
	{
		System.out.println(BORDER_EQUAL + BORDER_EQUAL_MIDDLE
				+ "End of Bing Automation" + "\n" + BORDER_EQUAL);
		
	}

}
