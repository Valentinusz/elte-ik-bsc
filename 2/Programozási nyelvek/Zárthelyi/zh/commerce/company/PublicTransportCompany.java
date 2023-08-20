package commerce.company;

import java.util.ArrayList;

public class PublicTransportCompany extends Company {
    private final ArrayList<String> places;

    public ArrayList<String> getPlaces() {
        return new ArrayList<String>(this.places);
    }

    public PublicTransportCompany(int establishmentYear, String name, ArrayList<String> arr) {
        super(establishmentYear,name);
        this.places = new ArrayList<String>(arr);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(":");
        sb.append(this.establishmentYear);
        sb.append(":");

        for (String place : this.places) {
            sb.append(place);
            sb.append(",");
        }

        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public static PublicTransportCompany createFromString(String str) {
        String[] splitString = str.split(":");
        String[] splitPlaces = splitString[2].split(",");

        ArrayList<String> places = new ArrayList<>();
        for (String placeStr : splitPlaces) {
            places.add(placeStr);
        }

        return new PublicTransportCompany(Integer.parseInt(splitString[1]),splitString[0],places);
    }
}