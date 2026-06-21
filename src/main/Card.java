package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Card {
    private Suit suit;
    private String face;
    private int rank;

    public Card(Suit suit, String face, int rank) {
        this.suit = suit;
        this.face = face;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    public static Card getNumericCard(Suit suit,int cardNumber){
        if (cardNumber>1&&cardNumber<11){
            return new Card(suit,String.valueOf(cardNumber),cardNumber-2);// the lowest rank is zero
        }
        System.out.println("Invalid Numeric card selected");
        return null;
    }
    public static Card getFaceCard(Suit suit,char abbrev){
        int charIndex = "JQKA".indexOf(abbrev);//largest index is 3
        if (charIndex>-1){
            return new Card(suit,""+abbrev,charIndex+9);// the highest rank is 12(3+9)
        }
        System.out.println("Invalid Face card selected");
        return null;
    }
    public static List<Card> getStandardDeck(){
        List<Card> deck = new ArrayList<>(52);
        for (Suit suit:Suit.values()) {
            for (int i = 2; i <=10 ; i++) {// must include 10 because 10-2+1 = 9
                deck.add(getNumericCard(suit,i));
            }
            for (char c:new char[]{'J','Q','K','A'}) {
                deck.add(getFaceCard(suit,c));
            }
        }
        return deck;
    }
    public static void printDeck(List<Card> deck){
        printDeck(deck,"Current Deck",4);

    }
    public static void printDeck(List<Card> deck,String description,int rows){
        System.out.println("------------------------------");
        if (description!=null){
            System.out.println(description);
        }
        int cardsInEachRow=deck.size()/rows;//52/4=13 cards in each row
        for (int i = 0; i < rows; i++) {
            int startIndex = i*cardsInEachRow;
            int endIndex=startIndex+cardsInEachRow;
            deck.subList(startIndex,endIndex).forEach(c-> System.out.print(c + " "));
            System.out.println();//after each row of cards leave an empty line
        }
    }

    public static Comparator<Card> sortRankReverseSuit(){
        return Comparator.comparing(Card::getRank).reversed().thenComparing(Card::getSuit);
    }

    @Override
    public String toString() {
        int index = face.equals("10")?2:1;
        String faceString = face.substring(0,index);
        return "%s%c(%d)".formatted(faceString,suit.getImage(),rank);

    }
}
