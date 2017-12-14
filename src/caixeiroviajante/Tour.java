    package caixeiroviajante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author PedroHenrique
 */
public class Tour extends Individual {

    private static List<City> cities;
    private final List<City> tour;

    public Tour(int size) {
        this.tour = new ArrayList<>(size);
        while (tour.size() < size) {
            tour.add(null);
        }

        fitness = 0;
    }

    public Tour() {
        this.tour = new ArrayList<>(cities);
        Collections.shuffle(tour);
        fitness = 0;
    }

    public City getCity(int tourPosition) {
        return tour.get(tourPosition);
    }

    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        fitness = 0; // Resetting fitness
    }

    @Override
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1 / this.getTotalDistance();
        }

        return fitness;
    }

    public double getTotalDistance() {
        double tourDistance = 0;

        for (int i = 0; i < tourSize(); i++) {
            City from = getCity(i);
            City destination;

            if (i < tourSize() - 1) {
                destination = this.getCity(i + 1);
            } else {
                destination = this.getCity(0);
            }

            tourDistance += from.distanceTo(destination);
        }

        return tourDistance;
    }

    public int tourSize() {
        return tour.size();
    }

    public boolean containsCity(City city) {
        return tour.contains(city);
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i) + "|";
        }
        return geneString;
    }

    @Override
    public Individual crossover(Individual parent) {
        Tour child = null;
        if (parent != null) {
            Tour parentTour = (Tour) parent;
            child = new Tour(this.tour.size());

            // Get start and end sub tour positions for parent1's tour
            int startPos = (int) (Math.random() * this.tourSize());
            int endPos = (int) (Math.random() * this.tourSize());

            // Loop and add the sub tour from parent1 to our child
            for (int i = 0; i < child.tourSize(); i++) {
                // If our start position is less than the end position
                if (startPos < endPos && i > startPos && i < endPos) {
                    child.setCity(i, this.getCity(i));
                } // If our start position is larger
                else if (startPos > endPos) {
                    if (!(i < startPos && i > endPos)) {
                        child.setCity(i, this.getCity(i));
                    }
                }
            }

            // Loop through parentTour's city tour
            for (int i = 0; i < parentTour.tourSize(); i++) {
                // If child doesn't have the city add it
                if (!child.containsCity(parentTour.getCity(i))) {
                    // Loop to find a spare position in the child's tour
                    for (int ii = 0; ii < child.tourSize(); ii++) {
                        // Spare position found, add city
                        if (child.getCity(ii) == null) {
                            child.setCity(ii, parentTour.getCity(i));
                            break;
                        }
                    }
                }
            }
        }
        return child;
    }

    @Override
    public void mutate() {
        // Loop through tour cities
        // Apply mutation rate
        // Get a second random position in the tour
        int tourPos1 = (int) (tourSize() * Math.random());
        int tourPos2 = (int) (tourSize() * Math.random());

        // Get the cities at target position in tour
        City city1 = getCity(tourPos1);
        City city2 = getCity(tourPos2);

        // Swap them around
        setCity(tourPos2, city1);
        setCity(tourPos1, city2);
    }

    public static void setCities(List<City> allCities) {
        cities = new ArrayList<>(allCities);
    }
}
