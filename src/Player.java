import java.util.*;

public class Player {
	
	private String username;
    private int score;
    private int chillPoints;
    private int coins;
    private List<Character> characters;
    private List<Weapon> inventory;

    public Player(String username)
    {
        this.username = username;
        this.score = 0;
        this.chillPoints = 0;
        this.coins = 100;
        this.characters = new ArrayList<>();
        this.inventory = new ArrayList<>();
    }

    public String getUsername()
    {
        return username;
    }

    public int getScore()
    {
        return score;
    }

    public void addScore(int points)
    {
        this.score += points;
    }

    public int getChillPoints()
    {
        return chillPoints;
    }

    public void addChillPoints(int points)
    {
        this.chillPoints += points;
    }

    public int getCoins()
    {
        return coins;
    }

    public void addCoins(int amount)
    {
        this.coins += amount;
    }

    public void spendCoins(int amount)
    {
        if (coins >= amount) {
            coins -= amount;
        } else {
            System.out.println("Not enough coins!");
        }
    }

    public void addCharacter(Character character)
    {
        this.characters.add(character);
    }

    public List<Character> getCharacters()
    {
        return characters;
    }

    public void addWeaponToInventory(Weapon weapon)
    {
        inventory.add(weapon);
    }

    public List<Weapon> getInventory()
    {
        return inventory;
    }

    public void equipWeaponToCharacter(Character character, Weapon weapon)
    {
        if (inventory.contains(weapon)) {
            character.equipWeapon(weapon);
            System.out.println(character.getName() + " equipped " + weapon);
        } else {
            System.out.println("Weapon not in inventory!");
        }
    }
}
