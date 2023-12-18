public class Deck {
    private final String[] colors = {"\033[34mBlue\033[0m\n", "\033[33mYellow\033[0m\n", "\033[31mRed\033[0m\n", "\033[32mGreen\033[0m\n"};
    private final String[] value = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};


    private int numberOfCards;
    private Card[] cards;


    public Deck() { //This method creates the cards in the deck.
        cards = new Card[40];
        numberOfCards = 0;
        for (int c = 0; c < colors.length; c++) {
            for (int n = 0; n < value.length; n++) {
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

    public Card showACard(int num) { //This method shows us all the cards in the deck except special cards.
        return cards[num];
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void shuffle() { //This method allows the cards in the deck to be shuffled.
        int i;
        for (int k = 0; k < cards.length; k++) {
            i = (int) (Math.random() * cards.length); // a random index between 0-40
            Card temp = cards[k];
            cards[k] = cards[i];
            cards[i] = temp;
        }
        numberOfCards = 40;
    }
}
