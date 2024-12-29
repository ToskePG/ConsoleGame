import java.util.*;

public class Character {

	 private String name;
	    private int level;
	    private int lifePoints;
	    private boolean isLocked;
	    private Weapon weapon;
	    private int experience;

	    public Character(String name, int level, Weapon weapon) {
	        this.name = name;
	        this.level = level;
	        this.lifePoints = 1000 + (level * 200);
	        this.isLocked = level > 5;
	        this.weapon = weapon;
	        this.experience = 0;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getLevel() {
	        return level;
	    }

	    public int getLifePoints() {
	        return lifePoints;
	    }

	    public boolean isLocked() {
	        return isLocked;
	    }

	    public void unlock() {
	        this.isLocked = false;
	    }

	    public void takeDamage(int damage) {
	        this.lifePoints -= damage;
	        if (this.lifePoints < 0) {
	            this.lifePoints = 0;
	        }
	    }

	    public boolean isAlive() {
	        return this.lifePoints > 0;
	    }

	    public void heal(int points) {
	        this.lifePoints += points;
	        if (this.lifePoints > 1000 + (level * 200)) {
	            this.lifePoints = 1000 + (level * 200);
	        }
	    }

	    public void levelUp() {
	        experience = 0;
	        level++;
	        lifePoints = 1000 + (level * 200);
	    }

	    public void gainExperience(int xp) {
	        experience += xp;
	        if (experience >= 500) {
	            System.out.println(name + " leveled up!");
	            levelUp();
	        }
	    }

	    public Weapon getWeapon() {
	        return weapon;
	    }

	    public void equipWeapon(Weapon newWeapon) {
	        this.weapon = newWeapon;
	    }

	    @Override
	    public String toString() {
	        return name + " (Level: " + level + ", Life: " + lifePoints + ", Weapon: " + weapon + ")";
	    }
	    
}
