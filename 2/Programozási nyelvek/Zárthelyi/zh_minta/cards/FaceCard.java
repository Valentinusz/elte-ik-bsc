package cards;

public class FaceCard extends Card {
    private Face face;

    public FaceCard(Suit suit, Face face) {
        super(suit);
        if (face != null) {
            this.face = face;
        } else {
            throw new NullPointerException();
        }
    }

    public Face getFace() {
        return this.face;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.face);
        sb.append(" of ");
        sb.append(super.toString());
        return sb.toString();
    }
}
