public class Card {
    private int value;
    private String color;


    public Card(int value, String color) {

        this.value = value;
        this.color = color;

    }


    public int getValue() {
        return this.value;
    }

    public String getColor() {
        return this.color;
    }

    public String toString() {
        return this.color  + "\nValue = " + this.value;
    }
}
