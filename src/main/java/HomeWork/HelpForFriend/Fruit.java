package HomeWork.HelpForFriend;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Fruit {

    private VariousFruit variousFruit;
    private int price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date creationDate;
    private int expirationDate;

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public VariousFruit getVariousFruit() {
        return variousFruit;
    }

    public void setVariousFruit(VariousFruit variousFruit) {
        this.variousFruit = variousFruit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Fruit() {
    }


    public void show() {
        System.out.println(
                "|-----------ONFO-----------" +
                        "\n|Type of fruit : " + variousFruit.name() + ";" +
                        "\n|Expiration Date : " + expirationDate + ";" +
                        "\n|Date of made : " + creationDate + ";" +
                        "\n|Price : " + price + " uan." +
                        "\n|--------------------------");
    }

    @Override

    public String toString() {
        return "Fruit{" +
                "variousFruit=" + variousFruit +
                ", price=" + price +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                '}';
    }

    public enum VariousFruit {
        APPLE,
        APRICOT,
        BANANA,
        PANAEPPLE,
        MELON
    }
}
