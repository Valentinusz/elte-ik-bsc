package city;

public enum City {
    // 1.1
    BUDAPEST_11(1111),
    //KILITI(SIOFOK), sorrend számít ez illegal forward reference lenne (1.5)
    SIOFOK(8600),
    BALATONSZABADI(8651),
    SIOMAROS(BALATONSZABADI);

    // 1.2
    int zipCode;

    // 1.3
    City(int zipCode) {
        this.zipCode = zipCode;
    }

    // 1.4
    City(City city) {
        this.zipCode = city.zipCode;
    }

    // 1.6
    @Override
    public String toString() {
        return this.zipCode + " " + this.name();
    }
}
