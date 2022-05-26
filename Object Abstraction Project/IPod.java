
package ipod;


public class IPod {


    public static void main(String[] args) {
        
        MediaFile myFile = new MediaFile("Good Fellas", EXTENTION.MOV); 
        MusicFile musicFile = new MusicFile("Lonely", EXTENTION.MP3, MUSICGENRE.POP, "Justin Bieber"); 
        MovieFile myMovie = new MovieFile("The Social Network", EXTENTION.MOV, MOVIEGENRE.DRAMA, "David Fincher", "Jesse Eisenberg");  
        
        myMovie.stream();
        musicFile.stream();
        
        
        Album myAlbum = new Album("Listen_When_Sad"); 
        myAlbum.addFile(myFile);
        myAlbum.addFile(musicFile); 
        myAlbum.addFile(myMovie);
        
        myAlbum.listTitles();
        

    }
    
}
