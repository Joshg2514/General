package ipod;

enum EXTENTION{MPG, MP3, MP4, MOV}

public class MediaFile implements Comparable<MediaFile>{

    protected String title; 
    protected EXTENTION extention; 
    
    public MediaFile(String Title, EXTENTION Extention)
    {
        title = Title; 
        extention = Extention; 
    }
    
    public String getInfo()
    {
        return title + ", " + extention; 
    }
    
    public void play()
    {
        System.out.println("Playing " + title + "...");
    }
    
    public void puase()
    {
        System.out.println("Pausing " + title + "...");
    }
    
    public void Stop()
    {
        System.out.println("Stopping " + title + "...");
    }
    
    @Override 
    public int compareTo(MediaFile other)
    {
        return title.compareTo(other.title); 
    }
    
    
}
