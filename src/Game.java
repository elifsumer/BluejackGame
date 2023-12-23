import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private static final int TARGET_SCORE = 20;

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
            resetGame();

            while (!isPlayerBusted() && !isComputerBusted()) {
                if (isPlayerStaying && !isComputerStaying) {
                    play(false);
                } else if (isPlayerStaying && isComputerStaying) {
                    determineSetWinner();
                    break;
                } else if (!isPlayerStaying && isComputerStaying) {
                    play(true);
                } else if (!isPlayerStaying && !isComputerStaying) {
                    play(true);
                    play(false);
                }
            }
        }
        determineGameWinner();
    }

    public void play(boolean isPlayerTurn) {
        if (isPlayerTurn) {
            System.out.println("Your board is = " + Arrays.toString(playerBoard));
            System.out.println("press 1 to get a card from deck, press 2 to stay, press 3 to play your cards");
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
                                int clickedNumber1;
                                System.out.println("Enter a value between 1 and  " + cardCount(playerHand));
                                clickedNumber1 = sc.nextInt();
                                while (cardCount(playerHand) < clickedNumber1 || 1 > clickedNumber1) {
                                    System.out.println("Enter a value between 1 and  " + cardCount(playerHand));
                                    clickedNumber1 = sc.nextInt();
                                }
                                gameStatus(removeACard(clickedNumber1, playerHand, true), playerBoard);
                                System.out.println("Your board is : " + Arrays.toString(playerBoard));
                                break;
                            } else {
                                System.out.println("You don't have a playable card");
                            }
                        }
                        case 2: {
                            System.out.println("You chose to stay. Your turn is over.");
                            isPlayerStaying = true;
                            break;
                        }
                        case 3: {
                            System.out.println("You chose to pass your turn. Your turn is over.");
                            break;
                        }
                        default: {
                            System.out.println("Invalid choice. Your turn is not over.");
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("You chose to stay. Your turn is over.");
                    isPlayerStaying = true;
                    break;
                }
                case 3: {
                    System.out.println("Your playable cards : " + Arrays.toString(playerHand));
                    if (cardCount(playerHand) != 0) {
                        int clickedNumber2;
                        System.out.println("Enter a value between 1 and  " + cardCount(playerHand));
                        clickedNumber2 = sc.nextInt();
                        while (cardCount(playerHand) < clickedNumber2 || 1 > clickedNumber2) {
                            System.out.println("Enter a value between 1 and  " + cardCount(playerHand));
                            clickedNumber2 = sc.nextInt();
                        }
                        gameStatus(removeACard(clickedNumber2, playerHand, true), playerBoard);
                        System.out.println("Your board is : " + Arrays.toString(playerBoard));
                        break;
                    } else {
                        System.out.println("You don't have a playable card");
                    }
                }
                default: {
                    System.out.println("Invalid choice. Your turn is not over.");
                    break;
                }
            }
        } else {
            System.out.println("Computer's turn:");
            System.out.println("Computer's board is = " + Arrays.toString(computerBoard));

            int computerDecision = makeComputerDecision();

            switch (computerDecision) {
                case 1: {
                    addCardToBoard(computerBoard);
                    System.out.println("Computer draws a card.");
                    System.out.println("Computer's board is = " + Arrays.toString(computerBoard));
                    break;
                }
                case 2: {
                    System.out.println("Computer chooses to stay.");
                    isComputerStaying = true;
                    break;
                }
                default: {
                    System.out.println("Computer made an invalid decision.");
                    break;
                }
            }
        }
    }

    private int makeComputerDecision() {
        if (calculateHand(computerBoard) < 19) {
            return 1;
        }
        return (Math.random() < 0.5) ? 1 : 2;
    }

    public void gameStatus(Card card, Card[] board) {
        if (cardCount(board) >= 9) {
            System.out.println("You cannot add more than 9 cards on a board");
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
            if (cards[i] != null) {
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

    private boolean isPlayerBusted() {
        return calculateHand(playerBoard) > TARGET_SCORE;
    }

    private boolean isComputerBusted() {
        return calculateHand(computerBoard) > TARGET_SCORE;
    }

    private void determineSetWinner() {
        int playerScore = calculateHand(playerBoard);
        int computerScore = calculateHand(computerBoard);

        if (isPlayerBusted()) {
            System.out.println("Player busts! Computer wins the set.");
            computersWin++;
        } else if (isComputerBusted()) {
            System.out.println("Computer busts! Player wins the set.");
            playersWin++;
        } else {
            if (playerScore == computerScore) {
                System.out.println("Set is tied! No one wins.");
            } else {
                System.out.println("Set winner: " + (playerScore > computerScore ? "Player" : "Computer"));
                if (playerScore > computerScore) {
                    playersWin++;
                } else {
                    computersWin++;
                }
            }
        }
        resetPlayerStatus();
    }

    private void determineGameWinner() {
        // Check if a player used all blue cards to get a score of 20
        if (isBluejack(playerBoard)) {
            System.out.println("Player wins the game with Bluejack!");
        } else if (isBluejack(computerBoard)) {
            System.out.println("Computer wins the game with Bluejack!");
        } else {
            // Existing logic for determining the game winner
            if (playersWin > computersWin) {
                System.out.println("Player wins the game!");
            } else if (playersWin < computersWin) {
                System.out.println("Computer wins the game!");
            } else {
                System.out.println("The game is tied!");
            }
        }
    }

    private boolean isBluejack(Card[] board) {
        int blueCount = 0;
        for (Card card : board) {
            if (card != null && card.getColor().equals("blue")) {
                blueCount++;
            }
        }
        return blueCount == 9 && calculateHand(board) == TARGET_SCORE;
    }

    private void resetPlayerStatus() {
        isPlayerStaying = false;
        isComputerStaying = false;
    }

    private void resetGame() {
        deck = new Deck();
        deck.shuffle();
        deck.dealing();
        playerHand = deck.playerHand;
        computerHand = deck.computerHand;
        resetPlayerStatus();
        playerBoard = new Card[9];
        computerBoard = new Card[9];
    }
}