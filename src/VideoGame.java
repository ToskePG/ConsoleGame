import java.util.*;

public class VideoGame {
	private String name;
    private List<Character> availableCharacters;
    private List<Weapon> availableWeapons;

    public VideoGame(String name) {
        this.name = name;
        this.availableCharacters = new ArrayList<>();
        this.availableWeapons = new ArrayList<>();
    }

    public void addCharacter(Character character) {
        availableCharacters.add(character);
    }

    public void addWeapon(Weapon weapon) {
        availableWeapons.add(weapon);
    }

    public List<Character> getAvailableCharacters() {
        return availableCharacters;
    }

    public Character getRandomEnemy() {
        Random random = new Random();
        return new Character(
                "Enemy" + random.nextInt(100),
                random.nextInt(5) + 1,
                new Weapon("Enemy Weapon", random.nextInt(30) + 20, 3.0)
        );
    }
}
