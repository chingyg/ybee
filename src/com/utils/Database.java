package com.utils;

import java.io.IOException;
import java.util.ArrayList;

public class Database
{

	private ArrayList<Account> accountList;
	private Serializer serializer;

	/**
	 * Constructor
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public Database() throws IOException
	{

		this.accountList = new ArrayList<Account>();
		this.serializer = new Serializer();

		try
		{
			Object data = serializer.deserialize();
			if (data != null)
			{
				accountList = (ArrayList<Account>) data;
			}
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Adds a new account to the database
	 * 
	 * @param newUsername
	 *            a new user name
	 * @param newPassword
	 *            a new password
	 */
	public void add(String newUsername, String newPassword)
	{

		Account newAccount = new Account(newUsername, newPassword);

		accountList.add(newAccount);

		saveDatabaseToFile();

	}

	/**
	 * Deletes the account at specified index
	 * 
	 * @param index
	 *            Which account to delete
	 */
	public void delete(int index)
	{
		accountList.remove(index);

		saveDatabaseToFile();
	}

	/**
	 * Returns the account at the specified index
	 * 
	 * @param i
	 *            The the index of the account you want
	 * @return Account corresponding to specified Index
	 */
	public Account getIndex(int i)
	{
		return accountList.get(i);
	}

	/**
	 * Get the number of accounts
	 * 
	 * @return Length of the accountList
	 */
	public int getNumOfAccounts()
	{
		return accountList.size() + 1;
	}

	/**
	 * Save the data base in memory to file
	 */
	public void saveDatabaseToFile()
	{

		// testing writing object to file
		serializer.serialize(accountList);
	}

}
