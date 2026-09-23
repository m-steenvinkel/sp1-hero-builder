public class Print {
    void welcome() {
        System.out.println("======= Welcome to Hero Builder ======");
        System.out.println();
    }

    void menu() {
        System.out.println("1. Start game");
        System.out.println("2. Exit");
    }

    void enemyEncounter(Character enemy) {
        System.out.println("You have encountered a " + enemy.getName() + "!");
        System.out.println();
    }

    void invalidCommand() {
        System.out.println("Invalid command");
    }

    void outOfCombatMenu() {
        System.out.println("Choose action: ");
        System.out.println("1. Enter dungeon");
        System.out.println("2. Enter shop");
        System.out.println("3. Change weapon");
        System.out.println("4. Exit to main menu");
    }
}
