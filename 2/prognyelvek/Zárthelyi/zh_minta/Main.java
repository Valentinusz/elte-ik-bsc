import cards.*;

public class Main {
    public static void main(String[] args) {
        Deck deck = Deck.makeFrenchDeck();

        try {
            while(deck.canDraw()) {
                System.out.println(deck.draw());
            }
        } catch (EmptyDeckException e) {
            System.out.println("The deck is empty.");
        }
    }
}
