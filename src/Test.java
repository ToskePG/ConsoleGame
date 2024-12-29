import java.util.*;

public class Test {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize weapons
        Weapon sword = new Weapon("Sword", 50, 3.0);
        Weapon bow = new Weapon("Bow", 30, 1.5);
        Weapon axe = new Weapon("Axe", 70, 4.0);

        // Initialize characters
        Character warrior = new Character("Warrior", 6, sword);
        Character archer = new Character("Archer", 4, bow);
        Character barbarian = new Character("Barbarian", 7, axe);

        // Initialize game
        VideoGame game = new VideoGame("Epic Battle");
        game.addCharacter(warrior);
        game.addCharacter(archer);
        game.addCharacter(barbarian);
        game.addWeapon(sword);
        game.addWeapon(bow);
        game.addWeapon(axe);

        // Initialize player
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        Player player = new Player(username);

        // Game loop
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. View Characters");
            System.out.println("2. Fight Random Enemy");
            System.out.println("3. View Inventory");
            System.out.println("4. Equip Weapon");
            System.out.println("5. Exit");

            int choice = getValidInt(scanner, "Choose an option: ", 1, 5);

            switch (choice) {
                case 1:
                    System.out.println("Your Characters:");
                    for (Character c : player.getCharacters()) {
                        System.out.println(c);
                    }
                    break;
                case 2:
                    System.out.println("A wild enemy appeared!");
                    Character enemy = game.getRandomEnemy();
                    System.out.println("Enemy: " + enemy);

                    if (player.getCharacters().isEmpty()) {
                        System.out.println("You have no characters to fight with. Game over!");
                        break;
                    }

                    Character playerChar = chooseCharacter(scanner, player);

                    while (playerChar.isAlive() && enemy.isAlive()) {
                        System.out.println("Attacking...");
                        enemy.takeDamage(playerChar.getWeapon().getDamage());
                        if (enemy.isAlive()) {
                            playerChar.takeDamage(enemy.getWeapon().getDamage());
                        }
                    }

                    if (playerChar.isAlive()) {
                        System.out.println("You defeated the enemy!");
                        player.addScore(100);
                        player.addChillPoints(200);
                        player.addCoins(50);
                    } else {
                        System.out.println(playerChar.getName() + " has fallen!");
                        player.getCharacters().remove(playerChar);
                    }
                    break;
                case 3:
                    System.out.println("Your Inventory:");
                    for (Weapon w : player.getInventory()) {
                        System.out.println(w);
                    }
                    break;
                case 4:
                    System.out.println("Equip Weapon:");
                    System.out.println("Available Weapons:");
                    for (Weapon w : player.getInventory()) {
                        System.out.println(w);
                    }
                    System.out.print("Enter weapon name: ");
                    scanner.nextLine();
                    String weaponName = scanner.nextLine();
                    Optional<Weapon> weaponToEquip = player.getInventory().stream().filter(w -> w.toString().contains(weaponName)).findFirst();
                    if (weaponToEquip.isPresent()) {
                        player.equipWeaponToCharacter(player.getCharacters().get(0), weaponToEquip.get());
                    } else {
                        System.out.println("Weapon not found in inventory!");
                    }
                    break;
                case 5:
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
