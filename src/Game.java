import java.util.Scanner;

public class Game {
    private Scanner scanner;
    private Character hero;
    private Character enemy;
    private Print print;

    public Game(Scanner scanner, Character hero, Character enemy, Print print) {
        this.scanner = scanner;
        this.hero = hero;
        this.enemy = enemy;
        this.print = print;
    }

    public void mainMenu() {
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

    public void runGame() {
        boolean running = true;

        while (running) {
            enemy.revive();

            outOfCombatMenu();

            if (!hero.getIsAlive()) {
                running = false;
                System.out.println(hero.getName() + " has been killed. Game over.");
            }
        }
        mainMenu();
    }

    public void combat() {
        print.enemyEncounter(enemy);

        while (hero.getIsAlive() && enemy.getIsAlive()) {
            hero.printSmallCharacterSheet();
            enemy.printSmallCharacterSheet();
            System.out.println("Choose action:");
            System.out.println("1. Attack");
            System.out.println("2. Heal " + hero.healthPotion.getHealAmount() + " (Potions: " + hero.healthPotion.getAmount() + ")");
            System.out.println();
            if (hero.isHealthCritical()) {
                System.out.println(hero.getName() + "s health is critically low, it is recommended to heal");
                System.out.println();
            }

            int input = getUserAction();

            if (input == 1) {
                hero.attack(enemy);
                hero.getEquippedWeapon().decreaseDurability(1);
            } else if (input == 2 && hero.healthPotion.getAmount() > 0 ) {
                hero.heal(hero.healthPotion.getHealAmount());
                hero.healthPotion.decreaseAmount(1);
            }

            if (enemy.getIsAlive()) {
                enemy.attack(hero);
                System.out.println();
            } else {
                System.out.println(enemy.getName() + " has been killed.");
                System.out.println();
                hero.victoryReward();
                hero.createWeapon();

                enemy.levelUp();
                enemy.getEquippedWeapon().increaseDamage(5);
            }
        }
    }

    public int getUserAction() {
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public void outOfCombatMenu() {
        hero.printCharacterSheet();
        print.outOfCombatMenu();

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
        } else if (userInput == 4) {
            mainMenu();
        }

    }

    public void chooseWeapon() {
        hero.printWeapons();
        System.out.println("Type a number to choose your weapon");
        hero.equip(getUserAction());
        System.out.println();
    }

    public void shop() {
        double potionPrice = 300;
        double potionUpgradePrice = 1000;
        int potionStock = 5;
        boolean inShop = true;


        while (inShop) {
            System.out.println("=== SHOP ===");
            System.out.println("1. Health potion | Price: " + potionPrice + " Gold | Stock: " + potionStock);
            System.out.println("2. Upgrade health potion " + hero.healthPotion.getHealAmount() + " -> " + (hero.healthPotion.getHealAmount() + 50) + " | Price: " + potionUpgradePrice + " Gold");
            System.out.println("3. Exit shop");
            System.out.println();
            System.out.println("Gold : " + hero.getGold());
            System.out.println();
            int userInput = getUserAction();


            switch (userInput) {
                case 1:
                    if (hero.removeGold(potionPrice)) {
                        hero.healthPotion.increaseAmount(1);
                        System.out.println("You have bought a health potion");
                        System.out.println();
                    } else {
                        System.out.println("You have insufficient funds");
                        System.out.println();
                    }
                    break;
                case 2:
                    if (hero.removeGold(potionUpgradePrice)) {
                        hero.healthPotion.increaseHealAmount();
                        potionUpgradePrice += 1000;
                        System.out.println("You have upgraded your health potion");
                        System.out.println();
                    } else {
                        System.out.println("You have insufficient funds");
                        System.out.println();
                    }
                    break;
                case 3:
                    inShop = false;
                    System.out.println("Exiting shop");
                    System.out.println();
                    break;
                default:
                    print.invalidCommand();
            }
        }
    }
}
