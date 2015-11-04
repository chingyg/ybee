import java.io.Serializable;

public class Account implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4837362758806483006L;
	private String username;
	private String password;

	public Account(String someUsername, String somePassword)
	{
		this.username = someUsername;
		this.password = somePassword;
	}

	public String getPassword()
	{
		return this.password;
	}

	public String getUsername()
	{
		return this.username;
	}

	public void setPassword(String newPassword)
	{
		this.password = newPassword;
	}

	public void setUsername(String newUsername)
	{
		this.username = newUsername;
	}

}
