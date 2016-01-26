public abstract class Print2
{

	public final String SCRIPT_NAME = "Bumble";

	public final String VERSION = "1.5.0.1";

	public abstract void printDebug(String message);

	public abstract void printInfo(String message);

	public abstract void printError(String message);

	public abstract void printInvalidCommand();

	public abstract void printStatus(String statusMessage);

	public abstract void printHelp();

	public abstract void printWelcome();

	public abstract void printlsBanner();

	public abstract void ls(int index, String username);

	public abstract void printExit();

	public abstract void printOffersStartBanner();
	public abstract void printOffersEndBanner();

	public abstract void printStartBanner();
	
	public abstract void printEndbanner();
}
