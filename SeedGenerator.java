import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class SeedGenerator {

    public ArrayList<Long> generateSeeds() {
        ArrayList<Long> seedArray = new ArrayList<>();
        try {
            File seedFile = new File("src/seeds.txt");
            Scanner sc = new Scanner(seedFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                Long i = Long.parseLong(data);
                seedArray.add(i);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured: " + e);
        }

        return seedArray;
    }


}
