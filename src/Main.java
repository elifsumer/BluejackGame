import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Deck d1 = new Deck();
        d1.shuffle();
        d1.dealing();
        Card[] d2 = d1.playerHand;
        Card[] d3 = d1.computerHand;
        System.out.println(Arrays.toString(d2));
        System.out.println("------------------------------------------------------------");
        System.out.println(Arrays.toString(d3));
    }
}