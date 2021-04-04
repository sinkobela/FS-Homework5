package ro.fasttrackit.homework5.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String capital;
    private long population;
    private long area;
    private String continent;
    @ElementCollection
    private List<String> neighbours;

    public Country() {
    }

    public Country(Integer id, String name, String capital, long population, long area, String continent, List<String> neighbours) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = Optional.ofNullable(neighbours)
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }

    public Country(String name, String capital, long population, long area, String continent, List<String> neighbours) {
        this(null, name, capital, population, area, continent, neighbours);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public long getPopulation() {
        return population;
    }

    public long getArea() {
        return area;
    }

    public String getContinent() {
        return continent;
    }

    public List<String> getNeighbours() {
        return Collections.unmodifiableList(neighbours);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setNeighbours(List<String> neighbours) {
        this.neighbours = neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return population == country.population && area == country.area && Objects.equals(id, country.id) && Objects.equals(name, country.name) && Objects.equals(capital, country.capital) && Objects.equals(continent, country.continent) && Objects.equals(neighbours, country.neighbours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capital, population, area, continent, neighbours);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", continent='" + continent + '\'' +
                ", neighbours=" + neighbours +
                '}';
    }
}
