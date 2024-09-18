// CS 0445 Spring 2024
// Demonstration of using object input and output streams in Java.  Use this to
// help you with Assignment 1.
// See also DataType.java (used with this handout)

import java.io.*;  // for I/O classes
import java.util.*;  // for ArrayList

public class OStreamDemo
{
	public static void main(String [] args)
	{
		// First create some objects and put into ArrayList
		ArrayList<DataType> A = new ArrayList<DataType>();
		
		A.add(new DataType("Herb",20,100.0));
		A.add(new DataType("Marge",25,400.0));
		A.add(new DataType("Mort",18,75.0));
		A.add(new DataType("Agnes",30,500.0));
		
		System.out.println("Original Data in ArrayList:");
		for (DataType D: A)
			System.out.println(D);
		
		System.out.println();
		System.out.println("Now saving objects to a file...\n");	
		try
		{	// Make ObjectOutputStream -- note syntax
			ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("data.out"));
			
			// Use writeObject method to write the data to the file
			for (int i = 0; i < A.size(); i++)
			{
				oos.writeObject(A.get(i));
			}
			oos.close();  // close the file
		}
		catch (IOException e) 	// We must catch IOException here since it is
		{						// a checked Exception.  In this case I don't
					// try to recover -- just note the error.
			System.out.println("IO Error");
		}
		
		System.out.println("Now reading objects back from file...\n");
		ArrayList<DataType> Acopy = new ArrayList<DataType>();
		
		try
		{
			ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("data.out"));
				
			// Note "infinite" loop here.  The readObject() method will
			// throw EOFException at the end of the file -- so we use that
			// exception as a sentinel to indicate that the reading is
			// finished.
			while (true)
			{
				// Note the casting here.  The readObject() method returns
				// the objects using Java Object references, so if we want
				// to access them fully we need to cast to the correct type.
				// If we cast incorrectly that will clearly throw an exception.
				DataType newData = (DataType) ois.readObject();
				System.out.println("Read in " + newData.toString());
				Acopy.add(newData);
			}
		}
		catch (EOFException e1)
		{
			System.out.println("All data has been read in");
		}
		catch (ClassNotFoundException e2)
		{
			// If the .class file for the object in the file cannot be found we will
			// get a ClassNotFoundException.  This is a checked exception so we must
			// catch it here, even if we are confident that it will not occur.
			System.out.println("Cannot create object from serialized form");
		}
		catch (IOException e3)
		{
			// IOException is also checked so we must also catch this (ex: in case
			// the file is not found)
			System.out.println("Some other I/O error");
		}
		System.out.println();
		
		System.out.println("Uploaded data in new ArrayList:");
		for (DataType D: Acopy)
			System.out.println(D);
	}
}
