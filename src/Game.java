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
        String userAction = getUserAction();
        if (userAction.equals("Start")) {
            runGame();
        } else if (userAction.equals("Exit")) {
            System.out.println("Exiting");
        } else {
            System.out.println("Invalid command");
        }
    }

    void runGame() {
        boolean running = true;

        while (running) {
            enemy.hp = enemy.maxHp;
            enemy.isAlive = true;
            combat();
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
            System.out.println("2. Heal");
            System.out.println();

            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 1) {
                hero.attack(enemy);
            } /*else if (input == 2) {
                if (hasHealthPotion()) {
                    heal(25);
                }
                */
            if (enemy.isAlive) {
                enemy.attack(hero);
            } else {
                System.out.println(enemy.name + " has been killed.");
                System.out.println();
            }


        }
    }

    String getUserAction() {
        int input = scanner.nextInt();
        scanner.nextLine();
        String action = "";
        if (input == 1) {
            action = "Start";
        } else if (input == 2) {
            action = "Exit";
        }
        return action;
    }
}
