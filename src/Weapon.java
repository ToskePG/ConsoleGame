import java.util.*;

public class Weapon {

	private String name;
	private int damage;
	private double weight;
	
	
	// Default Constructor
	public Weapon()
	{
		this.name = "Deafult player";
		this.damage = 1;
		this.weight = 10.0;
	}
	
	// Create a Weapon
	public Weapon(String name, int damage, double weight)
	{
		this.name = name;
		this.damage = damage;
		this.weight = weight;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getDamage()
	{
		return damage;
	}

	public void setDamage(int damage)
	{
		this.damage = damage;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight) 
	{
		this.weight = weight;
	}

	@Override
	public String toString()
	{
		return "Weapon [name=" + name + ", damage=" + damage + ", weight=" + weight + "]";
	}
	
	
}