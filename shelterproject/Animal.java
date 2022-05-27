//Student Name: Joshua Guillot
//LSU ID: 893430281
//Lab Teaching Assistant name: Zachary Faulkner
package shelterproject;


public class Animal implements Comparable<Animal>{
    private String name;
    private String kind;
    private int age;
    

    public Animal() {
        //default constructor
    }
    public Animal(String name, String kind, int age){
        //constructor
        this.age = age;
        this.kind = kind;
        this.name = name;  
    }
    public String getInfo(){
        //method to return info
        double newage;
        if(age >= 12){
            //just a way to switch int to double (I was too lazy to look up how to do it properly)
            newage = age;
            newage = newage/12;
            return String.format("%-12s\t %-12s\t %.1f years",name, kind, newage);
        }
        else
            return String.format("%-12s\t %-12s\t %d months",name, kind, age);
    }

    @Override
    public int compareTo(Animal o) {
        
        if(kind.compareTo(o.kind) != 0)
            return kind.compareTo(o.kind);
        else if(Integer.compare(age, o.age) != 0)
            return Integer.compare(age, o.age);
        else
            return name.compareTo(o.name);
    }
            
    
}
