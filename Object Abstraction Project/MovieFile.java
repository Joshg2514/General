
package ipod;

enum MOVIEGENRE{ACTION, DRAMA, HORROR, COMEDY, ROMANCE}

public class MovieFile extends MediaFile implements Streamable{
    
    private MOVIEGENRE movieGenre; 
    private String director;
    private String star;
    
    public MovieFile(String Title, EXTENTION Extention, MOVIEGENRE Genre, String Director_Name, String Star_Name)
    {
        super(Title, Extention);
        movieGenre = Genre;
        director = Director_Name; 
        star = Star_Name; 
    }
    
    
    @Override 
    public String getInfo()
    {
        return super.getInfo() + ", " + director +", " + star + ", " + movieGenre; 
    }
    
    
    @Override
    public void stream()
    {
        System.out.println("Streaming " + title + "...");
    }
    
}
