
package ipod;

enum MUSICGENRE{ROCK, POP, FOLK, INDIE, JAZZ}

public class MusicFile extends MediaFile implements Streamable{
    
    private MUSICGENRE musicGenre; 
    private String artist;
    
    public MusicFile(String Title, EXTENTION Extention, MUSICGENRE Genre, String Artist_Name)
    {
        super(Title, Extention);
        musicGenre = Genre;
        artist = Artist_Name; 
    }
    
    @Override 
    public String getInfo()
    {
        return  title + "." + extention + ", " + artist + ", " + musicGenre; 
    }
    
    
    @Override
    public void stream()
            {
                System.out.println("Streaming " + title + " by " + artist);
            }
    
}
