package ro.fasttrackit.homework5.domain;


import java.util.*;

public class Country {
    private static int idCounter = 1;

    private final int id;
    private final String name;
    private final String capital;
    private final long population;
    private final long area;
    private final String continent;
    private final List<String> neighbours;

    public Country(String name, String capital, long population, long area, String continent, List<String> neighbours) {
        this.id = idCounter++;
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = Optional.ofNullable(neighbours)
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }

    public int getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id && population == country.population && area == country.area && Objects.equals(name, country.name) && Objects.equals(capital, country.capital) && Objects.equals(continent, country.continent) && Objects.equals(neighbours, country.neighbours);
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
