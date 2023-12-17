public class Main {
    public static void main(String[] args) {
        Deck d1 = new Deck();
        for (int k = 0; k < 40; k++) {
            System.out.println(d1.showACard(k));
        }
    }
}