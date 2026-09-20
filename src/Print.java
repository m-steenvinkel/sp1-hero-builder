public class Print {
    void welcome() {
        System.out.println("Welcome to Hero Builder");
        System.out.println();
    }

    void menu() {
        System.out.println("1. Start game");
        System.out.println("2. Exit");
    }

    void enemyEncounter(Character enemy) {
        System.out.println("You have encountered a " + enemy.name + "!");
        System.out.println();
    }

    void invalidCommand() {
        System.out.println("Invalid command");
    }
}
