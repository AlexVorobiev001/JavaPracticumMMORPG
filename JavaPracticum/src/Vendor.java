public class Vendor extends NPC implements Sellable{





    public Vendor(String name, int gold) {
        super(name, gold);


    }

    @Override
    public String sell(Goods goods) {
        String result = "";
        if (goods == Goods.POTION) {
            result = "Зелье лечения";
        }
        return result;
    }
    public enum Goods {
        POTION
    }
}

