package cards;

public abstract class Card {
    protected Suit suit;

    public Card() {}

    public Card(Suit suit) {
        if (suit != null) {
            this.suit = suit;
        } else {
            throw new IllegalArgumentException("Suit must not be null.");
        }
    }

    public Suit getColor() {
        return this.suit;
    }

    @Override
    public String toString() {
        return suit.toString();
    }
}
