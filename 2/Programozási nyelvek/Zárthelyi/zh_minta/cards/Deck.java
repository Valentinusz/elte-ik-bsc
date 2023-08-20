package cards;

import java.util.Stack;

public class Deck {
    private Stack<Card> deck;

    private Deck() {
        this.deck = new Stack<>();
    }

    public boolean isEmpty() {
        return deck.empty();
    }

    public boolean canDraw() {
        return !this.isEmpty();
    }

    public Card draw() throws EmptyDeckException {
        if (this.canDraw()) {
            return this.deck.pop();
        } else {
            throw new EmptyDeckException();
        }
    }

    public static Deck makeFrenchDeck() {
        Deck deck = new Deck();
        for (Suit suit : Suit.values()) {
            for (int pip = 1; pip <= 10; ++pip) {
                deck.deck.push(new PipCard(suit, pip));
            }
            for (Face face : Face.values()) {
                if (face != Face.CAVALIER) {
                    deck.deck.push(new FaceCard(suit, face));
                }
            }
        }
        return deck;
    }
}
