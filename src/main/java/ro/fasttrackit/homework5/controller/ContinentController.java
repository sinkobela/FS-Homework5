package ro.fasttrackit.homework5.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.service.CountryService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("continents")
public class ContinentController {
    private final CountryService countryService;

    public ContinentController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<String> getAllContinents() throws FileNotFoundException {
        return countryService.getAllContinents();
    }

    @GetMapping("{continentName}/countries")
    public List<Country> getCountriesInContinent(@RequestParam String continentName) throws FileNotFoundException {
        return countryService.getCountriesInContinent(continentName);
    }

    @GetMapping("{continentName}/countries/{minPopulation}")
    public List<Country> getCountriesInContinentWithPopulationLargerThan(@RequestParam String continentName, @PathVariable Long minPopulation) throws FileNotFoundException {
        return countryService.getCountriesInContinentWithPopulationLargerThan(continentName, minPopulation);
    }



}
