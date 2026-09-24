public class Weapon {
    private String name;
    private int damage;
    private int durability;

    public Weapon(String name, int damage, int durability) {
        this.name = name;
        this.damage = damage;
        this.durability = durability;
    }

    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }

    public void increaseDamage(int amount) {
        this.damage += amount;
    }

    public int getDurability() {
        return this.durability;
    }

    public void decreaseDurability(int amount) {
        this.durability -= amount;
    }

}
