public class Character {
    private String name;
    private int hp;
    private int maxHp;
    private int level;
    private int xp;
    private double gold;
    private boolean isAlive;
    private char type;
    private Weapon[] weapons;
    private Weapon equippedWeapon;
    Item healthPotion;

    public Character(String name, int hp, int maxHp, int level, int xp, double gold, boolean isAlive, char type, Weapon[] weapons, Item healthPotion) {
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
        this.equippedWeapon = weapons[0];
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

    public Weapon getEquippedWeapon() {
        return this.equippedWeapon;
    }

    public void printCharacterSheet() {
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

    public void printSmallCharacterSheet() {
        System.out.println("=== " + this.name + " (" + this.type + ")" + " ===");
        System.out.print("Level: " + this.level + " | ");
        System.out.print("Health: " + this.hp + "/" + this.maxHp + " | ");
        System.out.println("Damage: " + this.equippedWeapon.getDamage());
        System.out.println();

    }


    public void printWeapons() {
        System.out.println("=== WEAPONS ===");
        for (int i = 0; i < weapons.length; i++) {
            if (weapons[i] != null) {
                System.out.print(i + 1 + ". Name: " + weapons[i].getName());
                System.out.print(" | Damage: " + weapons[i].getDamage());
                System.out.println(" | Durability: " + weapons[i].getDurability());
            }
        }
        System.out.println();
    }

    private void takeDamage(int amount) {

        this.hp -= amount;

        if (isAlive()) {
            isAlive = true;
        } else {
            isAlive = false;
        }
    }

    public void heal(int amount) {
        int tempValue = this.hp;
        this.hp += amount;

        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }

        System.out.println(this.name + " heals " + amount + "HP! | " + "Health : " + tempValue + " -> " + this.hp);
    }

    public boolean isAlive() {
        if (this.hp > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addGold(double amount) {
        this.gold += amount;
        System.out.println(this.name + " gained " + amount + " gold");
    }

    public boolean removeGold(double amount) {
        if (amount <= this.gold) {
            this.gold -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void addXP(int amount) {
        this.xp += amount;

        if (this.xp >= (1000 * this.level)) {
            levelUp();
            System.out.println(this.name + " has leveled up!");
        }
    }

    public void levelUp() {
        this.level++;
        this.xp = 0;
        this.maxHp += 10;
    }

    public boolean isHealthCritical() {
        if (this.hp < (this.maxHp / 4)) {
            return true;
        } else {
            return false;
        }
    }

    public double getHealthPercent() {
        double healthPercent = (1.0 * this.hp / this.maxHp) * 100;
        return healthPercent;
    }

    public void attack(Character c) {
        int damage = this.equippedWeapon.getDamage();
        System.out.println(this.name + " attacks " + c.name + " for " + damage + " damage!");
        c.takeDamage(damage);
    }


    public void victoryReward() {
        this.addXP(1500);
        this.heal(20);
        this.addGold(250);
        System.out.println();
    }

    public void createWeapon() {
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
        System.out.println(this.name + " has recieved a new weapon: " + weapons[i].getName());
        System.out.println();
    }

    public void equip(int index) {
        this.equippedWeapon = weapons[index -1];
    }

    public void revive() {
        this.hp = this.maxHp;
        this.isAlive = true;
    }


}
