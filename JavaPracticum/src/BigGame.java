import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BigGame {
    private static BufferedReader br;
    private static Character player = null;
    private static Rumble rumble = null;
    private static Vendor vendor = null;

    public static void main(String[] args) {
        System.out.println("\nДобро пожаловать в новый и неизведанный МИР!\n");
        br = new BufferedReader(new InputStreamReader(System.in));
        rumble = new Rumble();
        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(
                    string,
                    100,
                    20,
                    20,
                    0,
                    0
            );
            System.out.println(String.format("Создан персонаж по имени %s! У него %d опыта и %d золота", player.getName(), player.getExp(), player.getGold()));
            printNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Добро пожаловать в мой магазин!");
                printVendorsOffer();
                command(br.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }
    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }
    private static void printVendorsOffer() throws IOException{
        System.out.println("Хотите купить или продать?");
        System.out.println("1. Купить");
        System.out.println("2. Продать");
        System.out.println("3. Вернуться в главное меню");


        switch (br.readLine()) {
            case "1" : {
                Vendor vendor = new Vendor("Vendor", 300);
                vendor.sell(Vendor.Goods.POTION);
                player.hp = player.hp + 20;
                    if (player.gold > 0) {
                        player.gold = player.gold - 10;
                        System.out.println(String.format("%s принимает зелье лечения и теперь у него %d"+"й уровень, %d опыта и %d здоровья", player.getName(), Hero.lvl,player.getExp(), player.getHp()));
                    } else {
                        System.out.println("Не достаточно денег на покупку зелья!");
                    }

                printVendorsOffer();
            }
            break;
            case "2" : {
                System.out.println("Пока у продавца ничего нет");
                printVendorsOffer();
            }
            break;
            case "3" : {
                printNavigation();
                command(br.readLine());
            }
        }
    }
    private static void commitFight() {
        rumble.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у него %d"+"й уровень, %d опыта и %d золота, а также осталось %d едениц здоровья.", player.getName(), Hero.lvl, player.getExp(), player.getGold(), player.getHp()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }
    interface FightCallback {
        void fightWin();
        void fightLost();
    }
    private static Character createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin(
                "Гоблин",
                50,
                10,
                10,
                100,
                20
        );
        else return new Skeleton(
                "Скелет",
                25,
                20,
                20,
                100,
                10
        );
    }

}
