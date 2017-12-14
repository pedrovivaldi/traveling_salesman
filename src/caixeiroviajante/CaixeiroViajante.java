package caixeiroviajante;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PedroHenrique
 */
public class CaixeiroViajante {

    public static void main(String[] args) {
        // Create and add our cities
        City city = new City(60, 200);
        City city2 = new City(180, 200);
        City city3 = new City(80, 180);
        City city4 = new City(140, 180);
        City city5 = new City(20, 160);
        City city6 = new City(100, 160);
        City city7 = new City(200, 160);
        City city8 = new City(140, 140);
        City city9 = new City(40, 120);
        City city10 = new City(100, 120);
        City city11 = new City(180, 100);
        City city12 = new City(60, 80);
        City city13 = new City(120, 80);
        City city14 = new City(180, 60);
        City city15 = new City(20, 40);
        City city16 = new City(100, 40);
        City city17 = new City(200, 40);
        City city18 = new City(20, 20);
        City city19 = new City(60, 20);
        City city20 = new City(160, 20);

        List<City> cities = new ArrayList<>();
        cities.add(city);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        cities.add(city5);
        cities.add(city6);
        cities.add(city7);
        cities.add(city8);
        cities.add(city9);
        cities.add(city10);
        cities.add(city11);
        cities.add(city12);
        cities.add(city13);
        cities.add(city14);
        cities.add(city15);
        cities.add(city16);
        cities.add(city17);
        cities.add(city18);
        cities.add(city19);
        cities.add(city20);
        
        Tour.setCities(cities);

        // Initialize population
        Population pop = new TourPopulation(50);
        Tour fittest = (Tour) pop.getFittest();
        System.out.println("Initial distance: " + fittest.getTotalDistance());

        // Evolve population for 100 generations
        for (int i = 0; i < 100000; i++) {
            pop.evolvePopulation(true);
        }

        // Print final results
        System.out.println("Finished");
        fittest = (Tour) pop.getFittest();
        System.out.println("Final distance: " + fittest.getTotalDistance());
        System.out.println("Solution:");
        System.out.println(fittest);
    }

}
