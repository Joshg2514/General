//Student Name: Joshua Guillot
//LSU ID: 893430281
//Lab Teaching Assistant name: Zachary Faulkner
package shelterproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class Shelter {
    private String name;
    private ArrayList<Animal> animals = new ArrayList<>();
    public Shelter() {
        //default constructor
    }

    public Shelter(String Shelter_name) {
        //constructor
        this.name = Shelter_name;
    }

    void loadAnimals() throws FileNotFoundException {
    File inFile = new File("animals.txt");
    Scanner in = new Scanner(inFile);
    
    String line;
    
    while(in.hasNextLine())
    {
        //looks into animals.txt to gather data
        line = in.nextLine();
        String[] fields = line.split(";");
        
        animals.add(new Animal(fields[0].trim(), fields[1].trim(), Integer.parseInt(fields[2].trim()) ));  
    }
    
}
    

    public void listAnimals() {
        //prints out and sorts list
        System.out.println("Welcome to " + name + " Shelter");
        System.out.println("==========================================");
        Collections.sort(animals);
        for(Animal a: animals)
            System.out.println(a.getInfo());
        }
        
    }
    
    

