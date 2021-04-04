package ro.fasttrackit.homework5;

import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.reader.CountryReader;
import ro.fasttrackit.homework5.service.CountryService;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        CountryReader countryReader = new CountryReader(/* "C:\\Users\\SBela\\Desktop\\FullStack\\Homework5\\homework5\\src\\main\\resources\\Countries.txt" */);
        CountryReader countryReader = new CountryReader.FileCountryReader();
        CountryService countryService = new CountryService(countryReader);

//        for (Country country : countryReader.readCountries()) {
//            System.out.println(country);
//        }

        for (Country country : countryService.getCountriesWithNeighbourXWithoutY("HUN", "ROU")) {
            System.out.println(country);
        }
    }
}
