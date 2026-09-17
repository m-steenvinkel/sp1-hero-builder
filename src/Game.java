import java.util.Scanner;

public class Game {
    Scanner scanner;
    Character c1;
    Character c2;

    public Game(Scanner scanner, Character c1, Character c2) {
        this.scanner = scanner;
        this.c1 = c1;
        this.c2 = c2;
    }

    void runGame() {

        System.out.println("Welcome to my game");

        combat();

    }
    void combat() {
        System.out.println("You have encountered a " + c2.name);

        while (c1.isAlive && c2.isAlive) {
            c1.printSmallCharacterSheet();
            c2.printSmallCharacterSheet();
            System.out.println("Choose action:");
            System.out.println("1. Attack");
            System.out.println("2. Heal");
            System.out.println();

            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 1) {
                c1.attack(c2);
            } /*else if (input == 2) {
                if (hasHealthPotion()) {
                    heal(25);
                }
                */
            if (c2.isAlive) {
                c2.attack(c1);
            } else {
                System.out.println(c2.name + " has been killed.");
            }


        }
    }
}
