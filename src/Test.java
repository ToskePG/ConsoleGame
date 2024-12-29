import java.util.*;

public class Test {

	public static void main(String[] args)
	{
        Scanner scanner = new Scanner(System.in);

        // Initialize weapons
        Weapon sword = new Weapon("Sword", 50, 3.0);
        Weapon bow = new Weapon("Bow", 30, 1.5);
        Weapon axe = new Weapon("Axe", 70, 4.0);

        // Initialize characters
        Character warrior = new Character("Warrior", 3, sword);
        Character archer = new Character("Archer", 4, bow);
        Character barbarian = new Character("Barbarian", 5, axe);
        Character mage = new Character("Mage", 2, new Weapon("Staff", 40, 2.0));

        // Initialize game
        VideoGame game = new VideoGame("Epic Battle");
        game.addCharacter(warrior);
        game.addCharacter(archer);
        game.addCharacter(barbarian);
        game.addCharacter(mage);
        game.addWeapon(sword);
        game.addWeapon(bow);
        game.addWeapon(axe);
        game.addWeapon(new Weapon("Dagger", 20, 0.5));

        // Initialize player
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        Player player = new Player(username);

        // Choose initial characters
        System.out.println("Choose 2 characters for your initial roster:");
        List<Character> availableCharacters = game.getAvailableCharacters();
        for (int i = 0; i < availableCharacters.size(); i++)
        {
            System.out.println((i + 1) + ". " + availableCharacters.get(i));
        }
        for (int i = 0; i < 2; i++)
        {
            int choice = getValidInt(scanner, "Choose character " + (i + 1) + ": ", 1, availableCharacters.size());
            player.addCharacter(availableCharacters.get(choice - 1));
        }

        // Game loop
        while (true)
        {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. View Characters");
            System.out.println("2. Fight Random Enemy");
            System.out.println("3. View Inventory");
            System.out.println("4. Buy Character");
            System.out.println("5. Buy Weapon");
            System.out.println("6. Exit");

            int choice = getValidInt(scanner, "Choose an option: ", 1, 6);

            switch (choice) {
                case 1:
                    System.out.println("Your Characters:");
                    for (Character c : player.getCharacters())
                    {
                        System.out.println(c);
                    }
                    break;
                case 2:
                    System.out.println("A wild enemy appeared!");
                    Character enemy = game.getRandomEnemy();
                    System.out.println("Enemy: " + enemy);

                    if (player.getCharacters().isEmpty())
                    {
                        System.out.println("You have no characters to fight with. Game over!");
                        break;
                    }

                    Character playerChar = chooseCharacter(scanner, player);

                    while (playerChar.isAlive() && enemy.isAlive())
                    {
                        System.out.println("Attacking...");
                        enemy.takeDamage(playerChar.getWeapon().getDamage());
                        if (enemy.isAlive())
                        {
                            playerChar.takeDamage(enemy.getWeapon().getDamage());
                        }
                    }

                    if (playerChar.isAlive())
                    {
                        System.out.println("You defeated the enemy!");
                        player.addScore(100);
                        player.addChillPoints(200);
                        player.addCoins(50);
                    } else
                    {
                        System.out.println(playerChar.getName() + " has fallen!");
                        player.getCharacters().remove(playerChar);
                    }
                    break;
                case 3:
                    System.out.println("Your Inventory:");
                    for (Weapon w : player.getInventory())
                    {
                        System.out.println(w);
                    }
                    break;
                case 4:
                    System.out.println("Characters available for purchase:");
                    for (Character c : game.getAvailableCharacters())
                    {
                        if (!player.getCharacters().contains(c))
                        {
                            System.out.println(c + " - Cost: 100 coins");
                        }
                    }
                    System.out.print("Enter the name of the character you want to buy: ");
                    scanner.nextLine();
                    String charName = scanner.nextLine();
                    Optional<Character> charToBuy = game.getAvailableCharacters().stream()
                            .filter(c -> c.getName().equalsIgnoreCase(charName) && !player.getCharacters().contains(c))
                            .findFirst();
                    if (charToBuy.isPresent() && player.getCoins() >= 100)
                    {
                        player.spendCoins(100);
                        player.addCharacter(charToBuy.get());
                        System.out.println("You bought " + charToBuy.get().getName() + "!");
                    }
                    else
                    {
                        System.out.println("Character not found or insufficient coins!");
                    }
                    break;
                case 5:
                    System.out.println("Weapons available for purchase:");
                    for (Weapon w : game.getAvailableWeapons())
                    {
                        if (!player.getInventory().contains(w))
                        {
                            System.out.println(w + " - Cost: 50 coins");
                        }
                    }
                    System.out.print("Enter the name of the weapon you want to buy: ");
                    scanner.nextLine();
                    String weaponName = scanner.nextLine();
                    Optional<Weapon> weaponToBuy = game.getAvailableWeapons().stream()
                            .filter(w -> w.toString().contains(weaponName) && !player.getInventory().contains(w))
                            .findFirst();
                    if (weaponToBuy.isPresent() && player.getCoins() >= 50) {
                        player.spendCoins(50);
                        player.addWeaponToInventory(weaponToBuy.get());
                        System.out.println("You bought " + weaponToBuy.get().toString() + "!");
                    } else {
                        System.out.println("Weapon not found or insufficient coins!");
                    }
                    break;
                case 6:
                    System.out.println("Thanks for playing!");
                    return;
            }
        }
    }

    private static int getValidInt(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Input out of range. Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
    }

    private static Character chooseCharacter(Scanner scanner, Player player) {
        System.out.println("Choose a character to fight with:");
        List<Character> characters = player.getCharacters();
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ". " + characters.get(i));
        }
        int choice = getValidInt(scanner, "Enter your choice: ", 1, characters.size());
        return characters.get(choice - 1);
    }
}
