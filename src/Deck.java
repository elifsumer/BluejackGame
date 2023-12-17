import java.util.Random;

public class Deck {
    private final String[] colors = {"\033[34mBlue\033[0m\n", "\033[33mYellow\033[0m\n", "\033[31mRed\033[0m\n", "\033[32mGreen\033[0m\n"};
    private final String[] name = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private Card[] playerDeck = new Card[10];
    private Card[] computerDeck = new Card[10];
    public Card[] playerHand = new Card[4];
    public Card[] computerHand = new Card[4];
    Random rd = new Random();

    private int numberOfCards;
    private Card[] cards;

    public Card[] getPlayerDeck() {
        return playerDeck;
    }

    public Card[] getComputerDeck() {
        return computerDeck;
    }

    public Deck() {
        cards = new Card[40];
        numberOfCards = 0;
        for (int c = 0; c < colors.length; c++) {
            for (int n = 0; n < name.length; n++) {
                int value = n + 1;
                cards[numberOfCards] = new Card(value, colors[c]);
                numberOfCards++;

            }
        }
    }


    public Card giveACard() {
        numberOfCards--;
        System.out.println("Remaining card = " + numberOfCards);
        return cards[numberOfCards];
    }

    public Card showACard(int num) {
        return cards[num];
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }


}


