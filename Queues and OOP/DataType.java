// CS 0445 Spring 2024
import java.io.Serializable;

public class DataType implements Serializable
{
	private String name;
	private int age;
	private double savings;
	
	public DataType(String n, int a, double s)
	{
		name = new String(n);
		age = a;
		savings = s;
	}
	
	public String toString()
	{
		return new String("Name: " + name + " Age: " + age + " Money: " + savings);
	}
}