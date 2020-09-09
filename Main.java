import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int population;
    static int infectionProbability;
    static int minDygn;
    static int maxDygn;
    static int dyingRisk;
    static int infectedX;
    static int infectedY;
    static double simulationInfected = 0;
    static int simulations = 1;



    public static void main(String args[]){
        input();
        for (int i = 0; i < simulations; i++) {
            Population pop = new Population();
            pop.setPopulation(population, infectedX, infectedY);
            while ((pop.immunePeople + pop.deadPeople + pop.healthyPeople) < (population * population)) {
                pop.iterate();
                pop.day++;
                pop.accumulatedInfected += pop.dailyInfected;
                printDailyStats(pop);
                pop.dailyInfected = 0;
                pop.dailyDeaths = 0;
                pop.dailyImmune = 0;
            }
            simulationInfected += pop.accumulatedInfected;
        }

        simulationInfected = simulationInfected / simulations;
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("--------- For " + simulations + " simulations ---------");
        System.out.println("InfectionProbability: " + infectionProbability + "%");
        System.out.println("Infection Rate: " +  (simulationInfected/(population*population)));
    }

    public static void printDailyStats(Population pop) {
        System.out.println("------- Day " + pop.day + "--------");
        System.out.println("Healthy People: " + pop.healthyPeople);
        System.out.println("Daily Infected: " + pop.dailyInfected );
        System.out.println("Daily Deaths: " + pop.dailyDeaths);
        System.out.println("Daily Immune: " + pop.dailyImmune);
        System.out.println("Sick people: " + pop.sickPeople);
        System.out.println("Accumulated Infected: " + pop.accumulatedInfected);
        System.out.println("Accumulated Deaths: " + pop.deadPeople);
    }

    public static void input() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Population size (N): ");
        population = scanner.nextInt();

        System.out.println("Probability of getting infected (S): ");
        infectionProbability = scanner.nextInt();

        System.out.println("Minimum days of infected (MinDygn): ");
        minDygn = scanner.nextInt();

        System.out.println("Maximum days of infected (MaxDygn): ");
        maxDygn = scanner.nextInt();

        System.out.println("Risk of dying (L): ");
        dyingRisk = scanner.nextInt();

        System.out.println("Infected X: ");
        infectedX = scanner.nextInt();

        System.out.println("Infected Y: ");
        infectedY = scanner.nextInt();

    }
}
