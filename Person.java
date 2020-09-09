import java.util.Random;

public class Person {

    int infectionProbability = Main.infectionProbability;
    int minDygn = Main.minDygn;
    int maxDygn = Main.maxDygn;
    int dyingRisk = Main.dyingRisk;
    int sickDays = 0;
    int sickStart = 0;
    int status;
    boolean infectionDay;

    public Person() {
        Random rand = new Random();
        status = 0;
        sickDays = rand.nextInt(maxDygn - minDygn + 1) + minDygn;
        sickStart = 0;
        infectionDay = false;
    }

    public void checkStatus(Population pop) {
        if(this.status == 0) {
            checkInfectionRisk(pop);
        } else if (this.status == 1) {
            if (!checkImmunity(pop)) checkDeathRisk(pop);
        }
    }

    public void checkInfectionRisk(Population pop) {
        Random rand = new Random();
        int randomNr = rand.nextInt(100);
        if ( randomNr <= infectionProbability) {
            getInfected(pop);
        }
    }

    public void getInfected(Population pop) {
        this.sickStart = pop.day;
        this.status = 1;
        this.infectionDay = true;
        pop.dailyInfected++;
        pop.sickPeople++;
        pop.healthyPeople--;
    }

    public boolean checkImmunity(Population pop) {
        if (pop.day - this.sickStart == this.sickDays) {
            this.status = 2;
            pop.immunePeople++;
            pop.dailyImmune++;
            pop.sickPeople--;
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDeathRisk(Population pop) {
        Random rand = new Random();
        int randomNr = rand.nextInt(100);
        if(randomNr <= dyingRisk) {
            this.status = 3;
            pop.deadPeople++;
            pop.dailyDeaths++;
            pop.sickPeople--;
            return true;
        } else {
            return false;
        }

    }

}
