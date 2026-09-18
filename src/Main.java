import java.util.Scanner;

public class Main {
    Weapon sword = new Weapon("Sword", 20, 100);
    Weapon dagger = new Weapon("Dagger", 15, 100);

    Character hero = new Character("Ragnar", 85, 100, 5, 2300, 156.5, true, 'W', sword);
    Character enemy = new Character ("Goblin", 50, 50, 3, 0, 0, true, 'R', dagger);


    String[] items = {"Sword", "Shield", "Potion"};

    void main() {
        Scanner scanner = new Scanner(System.in);

        Print print = new Print();

        Game game = new Game(scanner, hero, enemy, print);


        game.mainMenu();





        /*

        hero.printCharacterSheet();
        enemy.printCharacterSheet();
        hero.attack(enemy);
        enemy.printCharacterSheet();
        hero.attack(enemy);
        enemy.printCharacterSheet();



        if (hero.removeGold(100.0)) {
            System.out.println("Bought a potion!");
        } else {
            System.out.println("Not enough gold!");
        }

        System.out.println("Health: " + hero.getHealthPercent() + "%");

        if (hero.isHealthCritical()) {
            System.out.println("WARNING: Find a healer!");
        }

         */
    }











    void printInventory() {
        System.out.println("Inventory (" + items.length + " items):");

        for (int i = 0; i < items.length; i++) {
            System.out.println("- " + items[i]);
        }

        System.out.println();
    }
}
