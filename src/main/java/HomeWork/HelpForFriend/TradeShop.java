package HomeWork.HelpForFriend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TradeShop {

    final static long DAY_IN_MLS = 86_400_000;

    private List<Fruit> dataBase = new ArrayList<>();

    public void addFruits(List<Fruit> fruits, String pathToJsonFile) throws JsonProcessingException {
        File file = new File(pathToJsonFile);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        dataBase.addAll(fruits);

        String content = mapper.writeValueAsString(fruits);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save(String pathToJsonFile) throws JsonProcessingException {
        File file = new File(pathToJsonFile);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String content = mapper.writeValueAsString(dataBase);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void load(String pathToJsonFile) {
        File file = new File(pathToJsonFile);
        ObjectMapper mapper = new ObjectMapper();
        try {
            dataBase = Arrays.asList(mapper.readValue(file, Fruit[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Fruit> getDataBase() {
        return dataBase;
    }

    public List<Fruit> getSpoiledFruits(Date date) {
        return dataBase.stream().filter((Fruit fruit) -> {
            Date fruitDate = fruit.getCreationDate();
            int fruiLife = fruit.getExpirationDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fruitDate);
            calendar.add(Calendar.DATE, fruiLife);
            return calendar.getTime().getTime() < date.getTime();
        }).collect(Collectors.toList());
    }

    public List<Fruit> getSpoiledFruits(Date date, Fruit.VariousFruit variousFruitType) {
        return getSpoiledFruits(date).stream().filter(fruit -> variousFruitType == fruit.getVariousFruit()).collect(Collectors.toList());
    }

    public List<Fruit> getAvailableFruits(Date date) {
        List<Fruit> value = getSpoiledFruits(date);
        return dataBase.stream().filter(fruit -> !value.contains(fruit)).collect(Collectors.toList());
    }

    public List<Fruit> getAvailableFruits(Date date, Fruit.VariousFruit variousFruitType) {
        return getAvailableFruits(date).stream().filter(fruit -> variousFruitType == fruit.getVariousFruit()).collect(Collectors.toList());
    }

    List<Fruit> getAddedFruits(Date date){
        return dataBase.stream()
                .filter(fruit -> fruit.getCreationDate().getTime() / DAY_IN_MLS  == date.getTime() / DAY_IN_MLS)
                .collect(Collectors.toList());
    }

    public List<Fruit> getAddedFruits(Date date, Fruit.VariousFruit variousFruitType) {
        return dataBase.stream()
                .filter(fruit -> variousFruitType == fruit.getVariousFruit())
                .filter(fruit -> fruit.getCreationDate().getTime() / DAY_IN_MLS  == date.getTime() / DAY_IN_MLS)
                .collect(Collectors.toList());
    }
}
