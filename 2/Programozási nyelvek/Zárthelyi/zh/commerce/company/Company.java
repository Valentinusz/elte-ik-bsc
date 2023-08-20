package commerce.company;

public abstract class Company {
    final int establishmentYear;
    final String name;

    public Company(int establishmentYear, String name) {
        this.establishmentYear = establishmentYear;
        this.name = name;
    }

    public int getEstablishmentYear() {
        return this.establishmentYear;
    }

    public String getName() {
        return this.name;
    }
}