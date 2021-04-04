package ro.fasttrackit.homework5.reader;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.fasttrackit.homework5.config.CountryConfig;
import ro.fasttrackit.homework5.domain.Country;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public interface CountryReader {

    List<Country> readCountries() throws FileNotFoundException;


    @Component
    @Profile("file")
    class FileCountryReader implements CountryReader {
        private final CountryConfig countryConfig;

        public FileCountryReader(CountryConfig countryConfig) {
            this.countryConfig = countryConfig;
        }

        @Override
        public List<Country> readCountries() throws FileNotFoundException {
            List<Country> countryList = new ArrayList<>();
            Scanner fileReader = new Scanner(new FileReader(countryConfig.getFileLocation()));

            while (fileReader.hasNextLine()) {
                String[] line = fileReader.nextLine().split("\\|");
                List<String> neighbours = new ArrayList<>();

                if (line.length == 6) {
                    neighbours = Arrays.stream(line[5].split("~")).toList();
                }
                Country country = new Country(line[0], line[1], Long.parseLong(line[2]), Long.parseLong(line[3]), line[4], neighbours);
                countryList.add(country);
            }
            return countryList;
        }
    }


    @Component
    @Profile("memory")
    class InMemoryCountryReader implements CountryReader {
        public InMemoryCountryReader() {
        }

        @Override
        public List<Country> readCountries() {
            return List.of(
                    new Country("Afghanistan", "Kabul", 27657145, 652230, "Asia", List.of("IRN", "PAK", "TKM", "UZB", "TJK", "CHN")),
                    new Country("Åland Islands", "Mariehamn", 28875, 1580, "Europe", List.of()),
                    new Country("Albania", "Tirana", 2886026, 28748, "Europe", List.of("MNE", "GRC", "MKD", "KOS")),
                    new Country("Algeria", "Algiers", 40400000, 2381741, "Africa", List.of("TUN", "LBY", "NER", "ESH", "MRT", "MLI", "MAR")),
                    new Country("American Samoa", "Pago Pago", 57100, 199, "Oceania", List.of()),
                    new Country("Andorra", "Andorra la Vella", 78014, 468, "Europe", List.of("FRA", "ESP")),
                    new Country("Angola", "Luanda", 25868000, 1246700, "Africa", List.of("COG", "COD", "ZMB", "NAM")),
                    new Country("Anguilla", "The Valley", 13452, 91, "Americas", List.of()),
                    new Country("Antigua and Barbuda", "Saint John's", 86295, 442, "Americas", List.of()),
                    new Country("Armenia", "Yerevan", 2994400, 29743, "Asia", List.of("AZE", "GEO", "IRN", "TUR")),
                    new Country("Aruba", "Oranjestad", 107394, 180, "Americas", List.of()),
                    new Country("Australia", "Canberra", 24117360, 7692024, "Oceania", List.of()),
                    new Country("Austria", "Vienna", 8725931, 83871, "Europe", List.of("CZE", "DEU", "HUN", "ITA", "LIE", "SVK", "SVN", "CHE")),
                    new Country("Azerbaijan", "Baku", 9730500, 86600, "Asia", List.of("ARM", "GEO", "IRN", "RUS", "TUR")),
                    new Country("Bahamas", "Nassau", 378040, 13943, "Americas", List.of())
            );
        }
    }

}
