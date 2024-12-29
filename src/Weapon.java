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
	
	
}