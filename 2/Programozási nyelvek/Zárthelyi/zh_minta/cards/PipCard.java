package cards;

public class PipCard extends Card {
    private int pip;

    public PipCard(Suit suit, int pip) {
        super(suit);
        if (1 <= pip && pip <= 10) {
            this.pip = pip;
        } else {
            throw new IllegalArgumentException("Invalid pip size: " + pip);
        }
    }

    public int getPip() {
        return this.pip;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.pip == 1) {
            sb.append("ACE of ");
            sb.append(super.toString());
        } else {
            sb.append(super.toString());
            sb.append("(");
            sb.append(this.pip);
            sb.append(")");
        }
        return sb.toString();
    }
}
