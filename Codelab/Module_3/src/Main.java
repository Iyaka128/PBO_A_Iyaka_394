class GameCharacter {
    private String name; // Simpan nama karakter
    private int health; // Simpan health karakter

    // Konstruktor (Menganalisis nama dan health)
    public GameCharacter(String name, int health) {
        this.name = name;
        this.health = health;
    }

    // Getter untuk nama
    public String getName() {
        return name;
    }

    // Getter untuk health
    public int getHealth() {
        return health;
    }

    // Setter untuk health
    public void setHealth(int health) {
        this.health = health;
    }

    // Metode serangan yang akan dioverride subclass
    public void attack(GameCharacter target) {
        System.out.println(name + " menyerang " + target.getName() + "!");
    }
}

// Kelas Brimstone
class Hero extends GameCharacter {
    public Hero(String name, int health) {
        super(name, health);
    }

    // Override metode serang untuk Brimstone
    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " menyerang " + target.getName() + " menggunakan Orbital Strike!");
        target.setHealth(target.getHealth() - 20); // Dikurangi health 20
        System.out.println(target.getName() + " sekarang memiliki " + target.getHealth() + " health.");
    }
}

// Kelas Viper
class Enemy extends GameCharacter {
    public Enemy(String name, int health) {
        super(name, health);
    }

    // Override metode serang untuk Viper
    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " menyerang " + target.getName() + " menggunakan Snake Bite!");
        target.setHealth(target.getHealth() - 15); // Dikurangi health 15
        System.out.println(target.getName() + " sekarang memiliki " + target.getHealth() + " health.");
    }
}


public class Main {
    public static void main(String[] args) {
        GameCharacter general = new GameCharacter("General Character", 100); // Objek karakter
        Hero hero = new Hero("Brimstone", 150); // Objek Brimstone dengan health 150
        Enemy enemy = new Enemy("Viper", 200); // Objek Viper dengan helath 200

        System.out.println("Status awal:"); // Status awal
        System.out.println(hero.getName() + " memiliki health: " + hero.getHealth());
        System.out.println(enemy.getName() + " memiliki health: " + enemy.getHealth());
        System.out.println();

        // Simulasi serangan
        hero.attack(enemy); // Brimstone serang 1x
        hero.attack(enemy); // Brimstone serang 2x
        enemy.attack(hero); // Viper serang 1x
    }
}

// Iyaka Samanda Caysar
// 202410370110394
// 2A Informatics Engineering
