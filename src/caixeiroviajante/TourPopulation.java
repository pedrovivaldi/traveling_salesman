package caixeiroviajante;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Vivaldi <pedro.cotta.vivaldi@gmail.com>
 */
public class TourPopulation extends Population {

    private static final int TOURNAMENT_SIZE = 5;
    private static final double MUTATION_RATE = 0.015;

    public TourPopulation(int size) {
        population = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Tour newTour = new Tour();
            population.add(newTour);
        }
    }

    @Override
    public void evolvePopulation(boolean elitism) {
        int elitismOffset = 0;
        List<Tour> newPopulation = new ArrayList<>(population.size());

        if (elitism) {
            newPopulation.add((Tour) this.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from Current population
        for (int i = 1; i < population.size(); i++) {
            // Select parents
            Tour parent1 = tournamentSelection();
            Tour parent2 = tournamentSelection();
            // Crossover parents
            Tour child = (Tour) parent1.crossover(parent2);
            // Add child to new population
            newPopulation.add(child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            if (Math.random() < MUTATION_RATE) {
                newPopulation.get(i).mutate();
            }
        }

        population = new ArrayList<>(newPopulation);
    }

    // Selects candidate tour for crossover
    private Tour tournamentSelection() {

        // Create a tournament population
        Population tournament = new TourPopulation(TOURNAMENT_SIZE);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * this.populationSize());
            tournament.population.set(i, this.getTour(randomId));
        }
        // Get the fittest tour
        Tour fittest = (Tour) tournament.getFittest();
        return fittest;

    }

    // Gets a tour from population
    public Tour getTour(int index) {
        return (Tour) population.get(index);
    }
}
