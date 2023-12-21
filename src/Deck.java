import java.util.Random;

public class Deck {
    private final String[] colors = {"\033[34mBlue\033[0m\n", "\033[33mYellow\033[0m\n", "\033[31mRed\033[0m\n", "\033[32mGreen\033[0m\n"};
    private final String[] value = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
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

    public void addSpecialCards() { //This method creates special cards in the deck.

        Card[] cardsForPlayer = new Card[5];
        Card[] cardsForComputer = new Card[5];
        for (int i = 0; i < 5; i++) {
            cardsForPlayer[i] = basicCardMaker(i);
            cardsForComputer[i] = basicCardMaker(i);
        }
        for (int i = 0; i < 5; i++) {
            playerDeck[i + 5] = cardsForPlayer[i];
            computerDeck[i + 5] = cardsForComputer[i];
        }

    }

    private Card basicCardMaker(int i) { //This method gives random signs and colors to special cards in the deck.
        Card card;

        int randomNumber = rd.nextInt(6) + 1;

        int randomSign = rd.nextInt(2);

        int chanceToGetSpecialCard = rd.nextInt(100) + 1;

        String color;

        if (i <= 3) {

            int randomColor = rd.nextInt(4);
            if (randomColor == 0) {
                color = "\033[34mBlue\033[0m\n";
            } else if (randomColor == 1) {
                color = "\033[33mYellow\033[0m\n";
            } else if (randomColor == 2) {
                color = "\033[31mRed\033[0m\n";
            } else {
                color = "\033[32mGreen\033[0m\n";
            }

            card = new Card(randomSign == 0 ? randomNumber : -1 * randomNumber, color);
        } else {
            if (chanceToGetSpecialCard >= 80 && chanceToGetSpecialCard <= 90) {
                card = new Card(100, "\033[30mBlack\033[0m\n"); // 100 means this card is flip-flop card
            } else if (chanceToGetSpecialCard > 90) {
                card = new Card(200, "\033[30mBlack\033[0m\n"); // 200 means this card is x2 card
            } else {
                card = basicCardMaker(1); // recursive
            }
        }
        return card;

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

    public void dealing() { //This method deals cards from the main deck to the player deck and the computer deck.

        for (int i = 0; i < 5; i++) {
            computerDeck[i] = cards[i];
            playerDeck[i] = cards[cards.length - i - 1];
        }
        addSpecialCards();

        int[] arrayForPlayerNumber = new int[4];
        for (int i = 0; i < 4; i++) {
            int number = rd.nextInt(10);
            boolean flag = false;
            for (int k : arrayForPlayerNumber) {
                if (k == number) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                i = i - 1;
            } else {
                arrayForPlayerNumber[i] = number;
                playerHand[i] = playerDeck[number];
            }


        }
        int[] arrayForComputerNumber = new int[4];
        for (int i = 0; i < 4; i++) {
            int number = rd.nextInt(10);
            boolean flag = false;
            for (int k : arrayForComputerNumber) {
                if (k == number) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                i = i - 1;
            } else {
                arrayForComputerNumber[i] = number;
                computerHand[i] = computerDeck[number];
            }
        }
    }
}
