public class Bartender {
    public boolean order(Beverage beverage, Guest guest) {
        return !(beverage.getLegalAge() == 18 && !guest.isAdult());
    }   
}
