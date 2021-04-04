package ro.fasttrackit.homework5.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.reader.CountryReader;
import ro.fasttrackit.homework5.repository.CountryRepository;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CountryService {

    private final CountryReader countryReader;
    private final CountryRepository countryRepository;

    public CountryService(CountryReader countryReader, CountryRepository countryRepository) throws FileNotFoundException {
        this.countryReader = countryReader;
        this.countryRepository = countryRepository;
        saveDataInRepo();
    }

    private void saveDataInRepo() throws FileNotFoundException {
        for (Country country : countryReader.readCountries()) {
            countryRepository.save(country);
        }
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public List<String> getAllContinents() {
        return countryRepository.findAll().stream()
                .map(Country::getContinent)
                .collect(Collectors.toUnmodifiableList());
    }

    public Country getCountryById(Integer id) throws NotFoundException {
        return countryRepository.findAll().stream()
                .filter(country -> country.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("There is no country with this id: " + id));
    }

    public List<String> getAllCountryNames() {
        return countryRepository.findAll().stream()
                .map(Country::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public String getCapitalByCountryId(Integer id) throws NotFoundException {
        return getCountryById(id).getCapital();
    }

    public long getPopulationByCountryId(Integer id) throws NotFoundException {
        return getCountryById(id).getPopulation();
    }

    public List<Country> getCountriesInContinent(String continent, Long minPopulation) {
        if (minPopulation == null) {
        return countryRepository.findAll().stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                .collect(Collectors.toUnmodifiableList());
        } else {
            return getCountriesInContinentWithPopulationLargerThan(continent, minPopulation);
        }
    }

    public List<String> getCountryNeighbours(Integer id) throws NotFoundException {
        return getCountryById(id).getNeighbours();
    }

    public List<Country> getCountriesInContinentWithPopulationLargerThan(String continentName, Long minPopulation) {
        return getCountriesInContinent(continentName, null).stream()
                .filter(country -> country.getPopulation() > minPopulation)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Country> getCountriesWithNeighbourXWithoutY(String neighbourX, String neighbourY) {
        return countryRepository.findAll().stream()
                .filter(country -> country.getNeighbours().contains(neighbourX))
                .filter(country -> !country.getNeighbours().contains(neighbourY))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Country> getCountriesNeighbourFilter(String neighbourX, String neighbourY ){
        if (neighbourX == null && neighbourY == null) {
            return getAllCountries();
        } else {
            return getCountriesWithNeighbourXWithoutY(neighbourX, neighbourY);
        }
    }

    public Map<String, Long> getMapFromCountryNameToPopulation() {
        Map<String, Long> result = new HashMap<>();
        for (Country country : countryRepository.findAll()) {
            result.put(country.getName(), country.getPopulation());
        }
        return result;
    }

    public Map<String, List<Country>> getMapFromContinentToCountryList() {
        return countryRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Country::getContinent,
                        HashMap::new,
                        Collectors.toUnmodifiableList()
                ));
    }


}
