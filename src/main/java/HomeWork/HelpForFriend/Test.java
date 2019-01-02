package HomeWork.HelpForFriend;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<>();

        Fruit apple = new Fruit();
        LocalDate localDateOne = LocalDate.of(2019, 1, 1);
        Date dateOne = Date.from(localDateOne.atStartOfDay(ZoneId.systemDefault()).toInstant());
        apple.setVariousFruit(Fruit.VariousFruit.APPLE);
        apple.setExpirationDate(20);
        apple.setCreationDate(dateOne);
        apple.setPrice(100);
        apple.show();
        fruits.add(apple);

        System.out.println();

        Fruit banana = new Fruit();
        LocalDate localDateTwo = LocalDate.of(2018, 1, 1);
        Date dateTwo = Date.from(localDateTwo.atStartOfDay(ZoneId.systemDefault()).toInstant());
        banana.setVariousFruit(Fruit.VariousFruit.BANANA);
        banana.setExpirationDate(30);
        banana.setCreationDate(dateTwo);
        banana.setPrice(80);
        banana.show();
        fruits.add(banana);

        TradeShop tradeShop = new TradeShop();
        try {
            tradeShop.addFruits(fruits, "src/main/java/HomeWork/HelpForFriend/deliverOne.json");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Fruit panaepple = new Fruit();
        panaepple.setVariousFruit(Fruit.VariousFruit.PANAEPPLE);
        panaepple.setExpirationDate(15);
        panaepple.setCreationDate(new Date());
        panaepple.setPrice(40);
        panaepple.show();
        fruits.clear();
        fruits.add(panaepple);

        try {
            tradeShop.addFruits(fruits, "src/main/java/HomeWork/HelpForFriend/deliverTwo.json");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            tradeShop.save("src/main/java/HomeWork/HelpForFriend/dataBase.json");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String dataBase = "src/main/java/HomeWork/HelpForFriend/dataBase.json";

        tradeShop.load(dataBase);
        tradeShop.getDataBase().forEach(System.out::println);

        System.out.println();

        LocalDate localDate2 = LocalDate.of(2019, 1, 1);
        Date date2 = Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());
        tradeShop.getSpoiledFruits(date2).forEach(System.out::println);

        System.out.println();

        tradeShop.getAvailableFruits(date2).forEach(System.out::println);

        System.out.println();
    }
}
