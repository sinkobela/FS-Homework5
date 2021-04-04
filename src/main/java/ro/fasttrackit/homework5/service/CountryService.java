package ro.fasttrackit.homework5.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.reader.CountryReader;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.springframework.data.util.Pair.toMap;

@Service
public class CountryService {

    private final CountryReader countryReader;

    public CountryService(CountryReader countryReader) {
        this.countryReader = countryReader;
    }

    public List<Country> getAllCountries() throws FileNotFoundException {
        return countryReader.readCountries();
    }

    public List<String> getAllContinents() throws FileNotFoundException {
        return countryReader.readCountries().stream()
                .map(Country::getContinent)
                .collect(Collectors.toUnmodifiableList());
    }

    public Country getCountryById(Integer id) throws FileNotFoundException, NotFoundException {
        return countryReader.readCountries().stream()
                .filter(country -> country.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("There is no country with this id: " + id));
    }

    public List<String> getAllCountryNames() throws FileNotFoundException {
        return countryReader.readCountries().stream()
                .map(Country::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public String getCapitalByCountryId(Integer id) throws FileNotFoundException, NotFoundException {
        return getCountryById(id).getCapital();
    }

    public long getPopulationByCountryId(Integer id) throws FileNotFoundException, NotFoundException {
        return getCountryById(id).getPopulation();
    }

    public List<Country> getCountriesInContinent(String continent) throws FileNotFoundException {
        return countryReader.readCountries().stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<String> getCountryNeighbours(Integer id) throws FileNotFoundException, NotFoundException {
        return getCountryById(id).getNeighbours();
    }

    public List<Country> getCountriesInContinentWithPopulationLargerThan(String continent, long population) throws FileNotFoundException {
        return getCountriesInContinent(continent).stream()
                .filter(country -> country.getPopulation() > population)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Country> getCountriesWithNeighbourXWithoutY(String neighbourX, String neighbourY) throws FileNotFoundException {
        return countryReader.readCountries().stream()
                .filter(country -> country.getNeighbours().contains(neighbourX))
                .filter(country -> !country.getNeighbours().contains(neighbourY))
                .collect(Collectors.toUnmodifiableList());
    }

//    public Map<String, Long> getMapFromCountryNameToPopulation() throws FileNotFoundException {
//        return countryReader.readCountries().stream()
//                .collect(toMap(
//                        Country::getName,
//                        Country::getPopulation,
//
//                ));
//    }

//    public Map<String, List<Country>> getMapFromContinentToCountryList() throws FileNotFoundException {
//        return countryReader.readCountries().stream()
//                .collect(groupingBy(
//                        Country::getContinent,
//
//                        ))
//    }






}
