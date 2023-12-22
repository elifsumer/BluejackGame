import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private Card[] playerBoard = new Card[9];
    private Card[] playerHand;
    private Card[] computerHand;
    private Card[] computerBoard = new Card[9];
    private Deck deck;

    private int playersWin = 0;
    private int computersWin = 0;
    private boolean isPlayerStaying = false;
    private boolean isComputerStaying = false;


    public void startGame() {


        for (int round = 1; round <= 3; round++) {
            System.out.println("------------- ROUND " + round + " -----------------");
            deck = new Deck();
            deck.shuffle();
            deck.dealing();
            playerHand = deck.playerHand;
            computerHand = deck.computerHand;


            while (!(calculateHand(playerBoard) > 20) || !(calculateHand(computerBoard) > 20)) {
                if (isPlayerStaying && !isComputerStaying) {
                    play(false);
                } else if (isPlayerStaying && isComputerStaying) {
                    //sonuçgöster();
                } else if (!isPlayerStaying && isComputerStaying) {
                    play(true);
                } else if (!isPlayerStaying && !isComputerStaying) {
                    play(true);
                    play(false);
                }

            }

        }
    }

    public void play(boolean isPlayerTurn) {
        if (isPlayerTurn) {
            System.out.println("Your board is = " + Arrays.toString(playerBoard));
            System.out.println("press 1 to get a card from deck,press 2 to stay, press 3 to play your cards");
            Scanner sc = new Scanner(System.in);
            int chosenNumber = sc.nextInt();
            while (chosenNumber <= 0 || chosenNumber >= 4) {
                System.out.println("Enter a value between 1 and 3");
                System.out.println("press 1 to get a card from deck, press 2 to stay, press 3 to play your cards");
                chosenNumber = sc.nextInt();
            }
            switch (chosenNumber) {
                case 1: {
                    addCardToBoard(playerBoard);
                    System.out.println("Your board is = " + Arrays.toString(playerBoard));
                    System.out.println("Your playable cards : " + Arrays.toString(playerHand));
                    System.out.println("press 1 to play your hand cards, press 2 for staying, press 3 to pass your turn");
                    int enteredNumber = sc.nextInt();
                    while (enteredNumber <= 0 || enteredNumber >= 4) {
                        System.out.println("Enter a value between 1 and 3");
                        System.out.println("press 1 to play your hand cards, press 2 for staying, press 3 to pass your turn");
                        enteredNumber = sc.nextInt();
                    }
                    switch (enteredNumber) {
                        case 1: {
                            System.out.println("Your playable cards : " + Arrays.toString(playerHand));
                            if (cardCount(playerHand) != 0) {
                                int clickedNumber;
                                System.out.println("Enter a value between 1 and  " + cardCount(playerHand));
                                clickedNumber = sc.nextInt();
                                while (cardCount(playerHand) < clickedNumber || 1 > clickedNumber) {
                                    System.out.println("Enter a value between 1 and  " + cardCount(playerHand));
                                    clickedNumber = sc.nextInt();
                                }
                                gameStatus(removeACard(clickedNumber, playerHand, true), playerBoard);
                                System.out.println("Your board is = " + Arrays.toString(playerBoard));
                                break;
                            } else {
                                System.out.println("You don't have a playable card");
                            }
                        }
                        case 2:
                    }
                }
            }
        }
    }

    public void gameStatus(Card card, Card[] board) {

        if (cardCount(board) >= 9) {
            System.out.println("You cant add more than 9 card in a board");
        } else {
            int freeIndex = cardCount(board);
            board[freeIndex] = card;
        }
    }

    public void addCardToBoard(Card[] board) {
        int freeIndex = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                break;
            } else
                freeIndex++;
        }
        board[freeIndex] = deck.giveACard();
    }

    private int calculateHand(Card[] cards) {
        int sum = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
            } else {
                sum += cards[i].getValue();
            }
        }
        return sum;
    }

    public int cardCount(Card[] hand) {
        int count = 0;
        for (Card c : hand) {
            if (c != null) {
                count++;
            }
        }
        return count;

    }

    public Card removeACard(int index, Card[] hand, boolean isPlayer) {
        Card removedCard = hand[--index];
        Card[] newHand = new Card[hand.length - 1];

        System.arraycopy(hand, 0, newHand, 0, index);
        System.arraycopy(hand, index + 1, newHand, index, hand.length - index - 1);

        if (isPlayer) {
            playerHand = newHand;
        } else {
            computerHand = newHand;
        }
        return removedCard;
    }
}




