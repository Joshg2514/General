//Student Name: Joshua Guillot
//LSU ID: 893430281
//Lab Teaching Assistant name: Zachary Faulkner
package shelterproject;

import java.io.FileNotFoundException;

public class ShelterProject {

    
    public static void main(String[] args) throws FileNotFoundException {
    // main class
    Shelter BRShelter = new Shelter("Baton Rouge General");
    
    BRShelter.loadAnimals();
    BRShelter.listAnimals();
    }
    
}
