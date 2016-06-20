package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer
{
private final String databaseName = "db.chingyg";
	public Serializer()
	{
		// Create the database file if it does not exist
		File file = new File(databaseName);
		if (!file.exists())
		{
			// file.mkdirs();
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out
					.println("--->	No database found.\n--->	New database2 created.");
		}
	}
/**
 * Attempts to read from the database file
 * @return the readed object from the database file
 * @throws IOException
 * @throws ClassNotFoundException
 */
	public Object deserialize() throws IOException, ClassNotFoundException
	{
		FileInputStream fin = new FileInputStream(databaseName);

		try
		{
			ObjectInputStream ois = new ObjectInputStream(fin);

			Object data = ois.readObject();
			ois.close();
			return data;
		}
		catch (Exception e)
		{
			// TODO: handle exception
			return null;
		}

	}

	public void serialize(Object data)
	{
		try
		{
			FileOutputStream fout = new FileOutputStream(databaseName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);

			oos.writeObject(data);
			oos.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
