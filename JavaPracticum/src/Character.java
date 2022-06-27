public abstract class Character implements Attackble {
    public String name;
    public int hp;
    public int strength;
    public int agility;
    public int exp;
    public int gold;

    public Character(String name, int hp, int strength, int agility, int exp, int gold) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.agility = agility;
        this.exp = exp;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    @Override
    public int attack() {
        if (agility * 3 > getRandomValue()) {return strength;}
        else if ((agility * 3 > getRandomValue() && (getRandomValue()%2 ==0))) {
            System.out.println("КРИИИИИИИИТ!!!");return strength*2;}
        else return 0;
    }

    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, hp);
    }


}

