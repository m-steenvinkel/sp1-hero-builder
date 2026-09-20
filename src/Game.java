import java.util.Scanner;

public class Game {
    Scanner scanner;
    Character hero;
    Character enemy;
    Print print;

    public Game(Scanner scanner, Character hero, Character enemy, Print print) {
        this.scanner = scanner;
        this.hero = hero;
        this.enemy = enemy;
        this.print = print;
    }

    void mainMenu() {
        print.welcome();
        print.menu();
        int userInput = getUserAction();

        if (userInput == 1) {
            System.out.println("Starting game");
            System.out.println();
            runGame();
        } else if (userInput == 2) {
            System.out.println("Exiting");
        } else {
            print.invalidCommand();
        }
    }

    void runGame() {
        boolean running = true;

        while (running) {
            enemy.hp = enemy.maxHp;
            enemy.isAlive = true;

            outOfCombatMenu();

            if (!hero.isAlive) {
                running = false;
                System.out.println(hero.name + " has been killed. Game over.");
            }
        }
        mainMenu();
    }

    void combat() {
        print.enemyEncounter(enemy);

        while (hero.isAlive && enemy.isAlive) {
            hero.printSmallCharacterSheet();
            enemy.printSmallCharacterSheet();
            System.out.println("Choose action:");
            System.out.println("1. Attack");
            System.out.println("2. Heal (Potions: " + hero.healthPotion.amount + ")");
            System.out.println();
            if (hero.isHealthCritical()) {
                System.out.println(hero.name + "s health is critically low, it is recommended to heal");
                System.out.println();
            }

            int input = getUserAction();

            if (input == 1) {
                hero.attack(enemy);
            } else if (input == 2 && hero.healthPotion.amount > 0 ) {
                hero.heal(50);
            }

            if (enemy.isAlive) {
                enemy.attack(hero);
                System.out.println();
            } else {
                System.out.println(enemy.name + " has been killed.");
                System.out.println();
                hero.victoryReward();

                enemy.levelUp();
                enemy.weapons[enemy.equippedWeaponIndex].damage += 5;
            }
        }
    }

    int getUserAction() {
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    void outOfCombatMenu() {
        hero.printCharacterSheet();

        System.out.println("Choose action: ");
        System.out.println("1. Enter dungeon");
        System.out.println("2. Enter shop");
        System.out.println("3. Change weapon");

        int userInput = getUserAction();

        if (userInput == 1) {
            System.out.println("Entering the dungeon");
            System.out.println();
            combat();
        } else if (userInput == 2) {
            System.out.println("Entering the shop");
            System.out.println();
            shop();
        } else if (userInput == 3) {
            chooseWeapon();
        }

    }

    void chooseWeapon() {
        hero.printWeapons();
        System.out.println("Type a number to choose your weapon");
        hero.equippedWeaponIndex = getUserAction() - 1;
        System.out.println();
    }

    void shop() {
        double potionPrice = 300;
        int potionStock = 5;
        System.out.println("=== SHOP ===");
        System.out.println("1. Health potion | " + potionPrice + " | Stock: " + potionStock);
        System.out.println("2. Exit shop");
        System.out.println();
        System.out.println("Gold : " + hero.gold);
        System.out.println();
        int userInput = getUserAction();

        if (userInput == 1) {
            if (hero.removeGold(potionPrice)) {
                hero.healthPotion.amount++;
                System.out.println("You have bought a health potion");
                System.out.println();
            } else {
                System.out.println("You have insufficient funds");
                System.out.println();
            }
        } else if (userInput == 2) {
            System.out.println("Exiting shop");
            System.out.println();
        }
    }
}
