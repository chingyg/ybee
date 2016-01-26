public class PrintColor extends Print2
{

	private final String BORDER_EQUAL = ANSI_COLOR.BLUE
			+ "============================================================"
			+ ANSI_COLOR.RESET;

	private final String BORDER_EQUAL_MIDDLE = ANSI_COLOR.BLUE + "\n= "
			+ ANSI_COLOR.RESET;

	private final String BORDER_HYPHEN = ANSI_COLOR.BLUE
			+ "------------------------------------------------------------"
			+ ANSI_COLOR.RESET;

	private final String STATUS_DEBUG = "[DEBUG]		";
	private final String STATUS_INFO = ANSI_COLOR.BOLD_YELLOW + "["
			+ ANSI_COLOR.RESET + ANSI_COLOR.BOLD_GREEN + "INFO"
			+ ANSI_COLOR.RESET + ANSI_COLOR.BOLD_YELLOW + "]		"
			+ ANSI_COLOR.RESET;
	private final String STATUS_ERROR = ANSI_COLOR.BOLD_YELLOW + "["
			+ ANSI_COLOR.RESET + ANSI_COLOR.BOLD_RED + "ERROR"
			+ ANSI_COLOR.RESET + ANSI_COLOR.BOLD_YELLOW + "]		"
			+ ANSI_COLOR.RESET;

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

		String banner = BORDER_EQUAL + BORDER_EQUAL_MIDDLE
				+ ANSI_COLOR.BOLD_YELLOW + " Command List\n" + ANSI_COLOR.RESET
				+ BORDER_EQUAL;

		String commands = ANSI_COLOR.GREEN + "\nadd" + ANSI_COLOR.RESET
				+ ANSI_COLOR.PURPLE + " <EMAIL_ADDRESS> <PASSWORD>	"
				+ ANSI_COLOR.RESET + "-Adds an acount to database"
				+ ANSI_COLOR.GREEN + "\ndelete" + ANSI_COLOR.RESET
				+ ANSI_COLOR.PURPLE + " -<INDEX>			" + ANSI_COLOR.RESET
				+ "-Delete an account. Use \"lsist\" to find the account index"
				+ ANSI_COLOR.GREEN + "\nls				" + ANSI_COLOR.RESET
				+ "-List all account information" + ANSI_COLOR.GREEN
				+ "\nstart " + ANSI_COLOR.RESET
				+ "				-Begin search,where N is the number of searches"
				+ ANSI_COLOR.GREEN + "\nexit				" + ANSI_COLOR.RESET
				+ "-Exits the program";

		System.out.println(banner + commands);

	}

	@Override
	public void printWelcome()
	{
		System.out.println(BORDER_EQUAL + BORDER_EQUAL_MIDDLE
				+ ANSI_COLOR.BOLD_YELLOW + super.SCRIPT_NAME + ANSI_COLOR.RESET
				+ " " + ANSI_COLOR.BOLD_RED + super.VERSION + ANSI_COLOR.RESET
				+ BORDER_EQUAL_MIDDLE + ANSI_COLOR.BOLD_YELLOW + "["
				+ ANSI_COLOR.RESET + ANSI_COLOR.GREEN + "T" + ANSI_COLOR.RESET
				+ ANSI_COLOR.CYAN + "Squard" + ANSI_COLOR.RESET
				+ ANSI_COLOR.BOLD_YELLOW + "]" + ANSI_COLOR.RESET + "\n"
				+ BORDER_EQUAL);

		String bee = "\n     %           %"
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
				+ "\n                            Z";

		printColoredBee(bee);

	}

	@Override
	public void ls(int index, String username)
	{
		String indexString = ANSI_COLOR.BOLD_YELLOW + "[" + ANSI_COLOR.RESET
				+ ANSI_COLOR.BOLD_BLUE + index + ANSI_COLOR.RESET
				+ ANSI_COLOR.BOLD_YELLOW + "] " + ANSI_COLOR.RESET;

		String usernameString = "";

		if (index % 2 == 0)
		{
			usernameString = ANSI_COLOR.CYAN + username + ANSI_COLOR.RESET;
		}
		else
		{
			usernameString = ANSI_COLOR.PURPLE + username + ANSI_COLOR.RESET;
		}

		String messageOut = indexString + usernameString;
		System.out.println(messageOut);

	}

	@Override
	public void printExit()
	{
		String messageOut = ANSI_COLOR.BOLD_CYAN + "Session ended"
				+ ANSI_COLOR.RESET;

		System.out.println(messageOut);

	}

	@Override
	public void printOffersStartBanner()
	{
		System.out.println(BORDER_EQUAL + BORDER_EQUAL_MIDDLE
				+ ANSI_COLOR.BOLD_YELLOW + "Offers" + ANSI_COLOR.RESET + "\n"
				+ BORDER_EQUAL);

	}

	@Override
	public void printlsBanner()
	{
		String messageOut = BORDER_EQUAL + BORDER_EQUAL_MIDDLE
				+ ANSI_COLOR.BOLD_YELLOW + " List of Accounts\n"
				+ ANSI_COLOR.RESET + BORDER_EQUAL;
		System.out.println(messageOut);

	}

	private void printColoredBee(String bee)
	{
		String colorBee = "";
		String temp = "";

		for (int i = 0; i < bee.length(); i++)
		{
			switch (bee.charAt(i))
			{
			case '%':
				temp = ANSI_COLOR.BOLD_BLACK + "%" + ANSI_COLOR.RESET;
				colorBee += temp;
				break;
			case 'Z':
				temp = ANSI_COLOR.BOLD_YELLOW + "Z" + ANSI_COLOR.RESET;
				colorBee += temp;
				break;
			case '*':
				temp = ANSI_COLOR.BOLD_BLACK + "*" + ANSI_COLOR.RESET;
				colorBee += temp;
				break;

			default:
				temp = ANSI_COLOR.BOLD_WHITE + bee.charAt(i) + ANSI_COLOR.RESET;
				colorBee += temp;
				break;
			}
		}

		System.out.println(colorBee);
	}

	@Override
	public void printStartBanner()
	{
		System.out.println(BORDER_EQUAL + BORDER_EQUAL_MIDDLE
				+ ANSI_COLOR.BOLD_YELLOW + "Starting Bing Automation"
				+ ANSI_COLOR.RESET + "\n" + BORDER_EQUAL);

	}

	@Override
	public void printOffersEndBanner()
	{
		System.out.println(BORDER_EQUAL + BORDER_EQUAL_MIDDLE
				+ ANSI_COLOR.BOLD_YELLOW + "End of Offers" + ANSI_COLOR.RESET + "\n"
				+ BORDER_EQUAL);
		
	}

	@Override
	public void printEndbanner()
	{
		System.out.println(BORDER_EQUAL + BORDER_EQUAL_MIDDLE
				+ ANSI_COLOR.BOLD_YELLOW + "End of Bing Automation"
				+ ANSI_COLOR.RESET + "\n" + BORDER_EQUAL);
		
	}

}
