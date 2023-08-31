package app;

import java.util.HashSet;
import commerce.company.*;

public class Register {
    public static void main(String[] args) {
        HashSet<Company> companies = new HashSet<>();
        for (String str : args) {
            companies.add(PublicTransportCompany.createFromString(str));
        }

        System.out.println("Number of companies: " + companies.size());
        
        for(Company company : companies) {
            System.out.println(company);
        }
    }
}