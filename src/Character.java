
public class Character {
    private String name;
    private int hp;
    private int maxHp;
    private int level;
    private int xp;
    private double gold;
    private boolean isAlive;
    char type;
    Weapon[] weapons;
    int equippedWeaponIndex;
    Item healthPotion;

    Character(String name, int hp, int maxHp, int level, int xp, double gold, boolean isAlive, char type, Weapon[] weapons, Item healthPotion) {
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.level = level;
        this.xp = xp;
        this.gold = gold;
        this.isAlive = isAlive;
        this.type = type;
        this.weapons = weapons;
        this.healthPotion = healthPotion;
    }

    public String getName() {
        return this.name;
    }

    public double getGold() {
        return this.gold;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    void printCharacterSheet() {
        System.out.println("=== CHARACTER SHEET ===");

        System.out.println("Name: " + this.name);
        System.out.println("Class: " + this.type);
        System.out.println("Level: " + this.level);
        System.out.println("Health: " + this.hp + "/" + this.maxHp);
        System.out.println("XP: " + this.xp + "/" + 1000 * this.level);
        System.out.println("Gold: " + this.gold);
        System.out.println("Alive: " + this.isAlive);
        System.out.println();

        // Status
        /*
        System.out.println("=== STATUS ===");

        if (this.xp >= (1000 * this.level)) {
            System.out.println("Ready to level up!");
        }

        System.out.println();
         */
    }

    void printSmallCharacterSheet() {
        System.out.println("=== " + this.name + " (" + this.type + ")" + " ===");
        System.out.print("Level: " + this.level + " | ");
        System.out.print("Health: " + this.hp + "/" + this.maxHp + " | ");
        System.out.println("Damage: " + this.weapons[equippedWeaponIndex].damage);
        System.out.println();

    }


    void printWeapons() {
        System.out.println("=== WEAPONS ===");
        for (int i = 0; i < weapons.length; i++) {
            if (weapons[i] != null) {
                System.out.print(i + 1 + ". Name: " + weapons[i].name);
                System.out.print(" | Damage: " + weapons[i].damage);
                System.out.println(" | Durability: " + weapons[i].durability);
            }
        }
        System.out.println();
    }

    void takeDamage(int amount) {

        this.hp -= amount;

        if (isAlive()) {
            isAlive = true;
        } else {
            isAlive = false;
        }
    }

    void heal(int amount) {
        int tempValue = this.hp;
        this.hp += amount;

        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }

        System.out.println(this.name + " heals " + amount + "HP! | " + "Health : " + tempValue + " -> " + this.hp);
    }

    boolean isAlive() {
        if (this.hp > 0) {
            return true;
        } else {
            return false;
        }
    }

    void addGold(double amount) {
        this.gold += amount;
        System.out.println(this.name + " gained " + amount + " gold");
    }

    boolean removeGold(double amount) {
        if (amount <= this.gold) {
            this.gold -= amount;
            return true;
        } else {
            return false;
        }
    }

    void addXP(int amount) {
        this.xp += amount;

        if (this.xp >= (1000 * this.level)) {
            levelUp();
            System.out.println(this.name + " has leveled up!");
        }
    }

    void levelUp() {
        this.level++;
        this.xp = 0;
        this.maxHp += 10;
    }

    boolean isHealthCritical() {
        if (this.hp < (this.maxHp / 4)) {
            return true;
        } else {
            return false;
        }
    }

    double getHealthPercent() {
        double healthPercent = (1.0 * this.hp / this.maxHp) * 100;
        return healthPercent;
    }

    void attack(Character c) {
        int damage = this.weapons[equippedWeaponIndex].damage;
        System.out.println(this.name + " attacks " + c.name + " for " + damage + " damage!");
        c.takeDamage(damage);
    }


    void victoryReward() {
        this.addXP(1500);
        this.heal(20);
        this.addGold(250);
        System.out.println();
    }

    void createWeapon() {
        int min = 20 + this.level;
        int max = min + 3 * this.level;
        int randomDamage = min + (int)(Math.random() * ((max - min) + 1));
        int i = 0;
        for (i = 0; i < weapons.length; i++) {
            if (weapons[i] == null) {
                weapons[i] = new Weapon("Sword " + i, randomDamage, 100);
                break;
            }
        }
        System.out.println(this.name + " has recieved a new weapon: " + weapons[i].name);
        System.out.println();
    }

    public void equip(int index) {
        this.equippedWeaponIndex = index - 1;
    }

    public void revive() {
        this.hp = this.maxHp;
        this.isAlive = true;
    }


}
