public class Main {
    public static void main(String[] args) {
        Deck d1 = new Deck();
        d1.shuffle();
        for (int k = 0; k < 40; k++) {
            System.out.println(d1.showACard(k));
        }
    }
}