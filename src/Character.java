
public class Character {
    String name;
    int hp;
    int maxHp;
    int level;
    int xp;
    double gold;
    boolean isAlive;
    char type;
    Weapon weapon;

    Character(String name, int hp, int maxHp, int level, int xp, double gold, boolean isAlive, char type, Weapon weapon) {
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.level = level;
        this.xp = xp;
        this.gold = gold;
        this.isAlive = isAlive;
        this.type = type;
        this.weapon = weapon;
    }

    void printCharacterSheet() {
        System.out.println("=== CHARACTER SHEET ===");

        System.out.println("Name: " + this.name);
        System.out.println("Class: " + this.type);
        System.out.println("Level: " + this.level);
        System.out.println("Health: " + this.hp + "/" + this.maxHp);
        System.out.println("XP: " + this.xp);
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
        System.out.println("Gold: " + this.gold);
        System.out.println();

    }

    void takeDamage(int amount) {

        int tempValue = this.hp;
        this.hp -= amount;

        if (isAlive()) {
            isAlive = true;
        } else {
            isAlive = false;
        }

        /*
        if (isAlive) {
            System.out.println(name + " is still alive.");
        } else {
            System.out.println(name + " is dead.");
        }

         */

        System.out.println();

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
    }

    boolean removeGold(double amount) {
        if (amount < this.gold) {
            this.gold -= amount;
            return true;
        } else {
            return false;
        }
    }

    void addXP(int amount) {
        this.xp += amount;

        if (this.xp >= (1000 * this.level)) {
            System.out.println("Ready to level up!");
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
        int damage = this.weapon.damage;
        System.out.println(this.name + " attacks " + c.name + " for " + damage + " damage!");
        c.takeDamage(damage);
    }


}
