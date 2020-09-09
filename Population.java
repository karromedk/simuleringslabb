class Population extends Main{

    int immunePeople;
    int deadPeople;
    int dailyInfected;
    int dailyImmune;
    int dailyDeaths;
    int accumulatedInfected;
    int sickPeople;
    int healthyPeople;
    int day;

    public Population() {
        dailyInfected = 0;
        dailyImmune = 0;
        dailyDeaths = 0;
        accumulatedInfected = 0;
        immunePeople = 0;
        deadPeople = 0;
        sickPeople = 0;
        healthyPeople = population * population;
        day = 0;
    }
    Person[][] matrix= new Person[population][population];

    public void setPopulation(int population, int infectedX, int infectedY){
        for ( int i = 0; i < population; i++) {
            for( int j = 0; j < population; j++ ) {
                Person p = new Person();
                matrix[i][j] = p;
            }
        }
        Person firstInfected = matrix[infectedX][infectedY];
        firstInfected.getInfected(this);
    }

    public void iterate() {
        for( int i=0; i< population; i++) {
            for ( int j=0; j < population; j++) {
                Person p = matrix[i][j];
                if (p.status == 1 && p.infectionDay == false) {
                    if(!p.checkImmunity(this)) {
                        if(!p.checkDeathRisk(this)) {
                            infectNeighbours(i,j);
                        }
                    }
                }
            }
        }

        // After 1 day, set the infectionday flag to false for everyone infected
        for( int i=0; i< population; i++) {
            for ( int j=0; j < population; j++) {
                Person p = matrix[i][j];
                if (p.status == 1) {
                    p.infectionDay = false;
                }
            }
        }
    }

    public void infectNeighbours(int i, int j) {
        for (int n = i - 1; n <= i + 1; n++) {
            for (int m = j - 1; m <= j + 1; m++) {
                if(n == i && m == j)
                    continue;
                if( n < 0 || m < 0 || n >= population || m >= population)
                    continue;
                Person p = matrix[n][m];
                p.checkStatus(this);
            }
        }

    }
}
