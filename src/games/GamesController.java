package games;

import games.poker.PokerGame;

public class GamesController {

    public static void main(String[] args) {
        PokerGame fiveCardDraw=new PokerGame(8,5);
        fiveCardDraw.startPlay();
    }
}
