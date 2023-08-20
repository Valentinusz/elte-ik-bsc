package person;

public class Person {
    private String firstName;
    private String lastName;
    private String occupation;
    private int birthYear;
    private Gender gender;

    public Person(String firstName, String lastName, String occupation, int birthYear, Gender gender) {
        if (firstName != null && lastName != null && occupation != null && gender != null) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.occupation = occupation;
            this.birthYear = birthYear;
            this.gender = gender;
        }
    }

    // 12.

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        if (that != null && this.getClass().equals(that.getClass())) {
            Person p = (Person)that;
            return this.firstName.equals(p.firstName)   &&
                   this.lastName.equals(p.lastName)     &&
                   this.occupation.equals(p.occupation) &&
                   this.birthYear == p.birthYear        &&
                   this.gender == p.gender;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.firstName.hashCode() + this.lastName.hashCode() + this.occupation.hashCode() + this.birthYear + this.gender.hashCode();
        // this.birthYear ugyan az mint this.birthYear.hashCode()
    }


    //12 vége

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.firstName);
        sb.append(" ");
        sb.append(this.lastName);
        sb.append(" ");
        sb.append(this.occupation);
        sb.append(" ");
        sb.append(this.birthYear);
        sb.append(" ");
        sb.append(this.gender.toString());
        return sb.toString();
    }



}
