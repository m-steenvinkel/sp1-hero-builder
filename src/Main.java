import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Weapon[] weapons = new Weapon[10];
        Weapon[] enemyWeapons = new Weapon[10];

        weapons[0] = new Weapon("Sword", 20, 100);
        enemyWeapons[0] = new Weapon("Dagger", 15, 100);

        Item healthPotion = new Item("healthPotion", 3);

        Character hero = new Character("Ragnar", 100, 100, 1, 0, 500.0, true, 'W', weapons, healthPotion);
        Character enemy = new Character ("Goblin", 50, 50, 1, 0, 0, true, 'R', enemyWeapons, healthPotion);



        Scanner scanner = new Scanner(System.in);

        Print print = new Print();

        Game game = new Game(scanner, hero, enemy, print);


        game.mainMenu();
    }

}
