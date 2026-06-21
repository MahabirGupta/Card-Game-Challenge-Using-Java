package games.poker;

import main.Card;

import java.util.*;
import java.util.function.Consumer;

public class PokerGame {
    private final List<Card> deck = Card.getStandardDeck();
    private int playerCount;
    private int cardsInHand;
    private List<PokerHand> pokerHands;
    private List<Card> remainingCards;

    public PokerGame(int playerCount, int cardsInHand) {
        this.playerCount = playerCount;
        this.cardsInHand = cardsInHand;
        pokerHands=new ArrayList<>(cardsInHand);
    }
    public void startPlay(){
        Collections.shuffle(deck);
        Card.printDeck(deck);
        int randomMiddle = new Random().nextInt(15,35);
//        Collections.rotate(deck,26);// the first 2 row will swap with the last 2 row
        Collections.rotate(deck,randomMiddle);
        Card.printDeck(deck);

        deal();
        System.out.println("-----------------------------");
        Consumer<PokerHand> checkHand=PokerHand::evalHand;
        pokerHands.forEach(checkHand.andThen(System.out::println));

        int cardsDealt=playerCount*cardsInHand;// find the total number of cards dealt
        int cardsRemaining = deck.size()-cardsDealt;

        remainingCards = new ArrayList<>(Collections.nCopies(cardsRemaining,null));
        remainingCards.replaceAll(card -> deck.get(cardsDealt+remainingCards.indexOf(card)));
        Card.printDeck(remainingCards,"Remaining cards",2);

    }
    private void deal(){
        Card[][] hands = new Card[playerCount][cardsInHand];
        for (int deckIndex = 0,i=0; i <cardsInHand ; i++) {
            for (int j = 0; j < playerCount; j++) {
                hands[j][i]=deck.get(deckIndex++);
            }
        }
        int playerNo=1;
        for (Card[]hand:hands) {
            pokerHands.add(new PokerHand(playerNo++, Arrays.asList(hand)));
        }
    }
}
