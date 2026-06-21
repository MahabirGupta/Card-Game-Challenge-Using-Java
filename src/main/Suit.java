package main;

public enum Suit {
    CLUB,DIAMOND,HEART,SPADE;
    public char getImage(){
        char[] ch = new char[]{9827,9830,9829,9824};
        return ch[this.ordinal()];
    }
}
