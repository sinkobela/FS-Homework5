package ro.fasttrackit.homework5.controller;

import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.service.CountryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries(@RequestParam (required = false) String includeNeighbour,
                                         @RequestParam (required = false) String excludeNeighbour) {
        return countryService.getCountriesNeighbourFilter(includeNeighbour, excludeNeighbour);
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable Integer id) throws NotFoundException {
        return countryService.getCountryById(id);
    }

    @GetMapping("/names")
    public List<String> getAllCountryNames() {
        return countryService.getAllCountryNames();
    }

    @GetMapping("{id}/capital")
    public String getCapitalByCountryId(@PathVariable Integer id) throws NotFoundException {
        return countryService.getCapitalByCountryId(id);
    }

    @GetMapping("{id}/population")
    public Long getPopulationByCountryId(@PathVariable Integer id) throws NotFoundException {
        return countryService.getPopulationByCountryId(id);
    }

    @GetMapping("{id}/neighbours")
    public List<String> getCountryNeighbours(@PathVariable Integer id) throws NotFoundException {
        return countryService.getCountryNeighbours(id);
    }

    @GetMapping("/population")
    public Map<String, Long> getMapFromCountryToPopulation() {
        return countryService.getMapFromCountryNameToPopulation();
    }

}
