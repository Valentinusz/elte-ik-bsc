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

    public boolean equals(Person that) {
        if (           that != null            &&
            this.firstName  == that.firstName  &&
            this.lastName   == that.lastName   &&
            this.occupation == that.occupation &&
            this.birthYear  == that.birthYear  &&
            this.gender     == that.gender       ){
                return true;
            } 
        return false;
    }

}
