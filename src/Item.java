public class Item {
    private String name;
    private int amount;
    private int healAmount = 50;

    public Item(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public void increaseHealAmount() {
        this.healAmount += 50;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getHealAmount() {
        return this.healAmount;
    }

    public void decreaseAmount(int amount) {
        this.amount -= amount;
    }

    public void increaseAmount(int amount) {
        this.amount += amount;
    }
}
