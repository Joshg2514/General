
package ipod;

import java.util.*; 

public class Album {

    private ArrayList<MediaFile> album; 
    private String title; 
    
    public Album(String Title)
    {
        title = Title;
        album = new ArrayList<>();
    }
    
    public void addFile(MediaFile md)
    {
        album.add(md); 
    }
    
    public void listTitles()
    {
        Collections.sort(album);
        
        for(MediaFile md: album)
            System.out.println(md.getInfo());
    }
    
            
    
}
