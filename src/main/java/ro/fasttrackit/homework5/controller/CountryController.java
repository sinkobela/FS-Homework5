package ro.fasttrackit.homework5.controller;

import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.service.CountryService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() throws FileNotFoundException {
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable Integer id) throws FileNotFoundException, NotFoundException {
        return countryService.getCountryById(id);
    }

    @GetMapping("/names")
    public List<String> getAllCountryNames() throws FileNotFoundException {
        return countryService.getAllCountryNames();
    }

    @GetMapping("{id}/capital")
    public String getCapitalByCountryId(@PathVariable Integer id) throws FileNotFoundException, NotFoundException {
        return countryService.getCapitalByCountryId(id);
    }

    @GetMapping("{id}/population")
    public Long getPopulationByCountryId(@PathVariable Integer id) throws FileNotFoundException, NotFoundException {
        return countryService.getPopulationByCountryId(id);
    }

    @GetMapping("{id}/neighbours")
    public List<String> getCountryNeighbours(@PathVariable Integer id) throws FileNotFoundException, NotFoundException {
        return countryService.getCountryNeighbours(id);
    }

    @GetMapping("{includeNeighbour}{excludeNeighbour}")
    public List<Country> getCountriesWithNeighbourXWithoutY(@RequestParam String includeNeighbour, @RequestParam String excludeNeighbour) throws FileNotFoundException {
        return countryService.getCountriesWithNeighbourXWithoutY(includeNeighbour, excludeNeighbour);
    }


}
