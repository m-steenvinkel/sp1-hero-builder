public class Item {
    String name;
    int amount;
    int healAmount = 50;

    Item(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    void increaseHealAmount() {
        this.healAmount += 50;
    }
}
