import java.util.*;
import java.io.*;

/**
 *
 * Joshua Guillot
 * jguil87@lsu.edu
 * PA-2 (RAID)
 * Feng Chen
 * cs4103-au21
 * cs410339
 * 
 */
public class RAID5 {
//driver
    public static void main(String[] args) {
        
        //Initializes variables with runtime arguments from User
        int n_drives = Integer.parseInt(args[0]);
        int bytes_per_block = Integer.parseInt(args[1]);
        String command = args[2];
        String file_name = args[3];
        
        
        //reads input.txt fileand creates disc1.txt through discn.txt
        File[] disc_s = new File[n_drives];
        for(int i = 1; i <= n_drives; i++){
            disc_s[i-1] = new File("disc"+Integer.toString(i)+".txt");
            try{
                disc_s[i-1].createNewFile();
            }
            catch(IOException e){
                System.out.println("Drive " + Integer.toString(i) + " not created.");
                e.printStackTrace();
            }
        }
        //creates commands for raid5
        if(command.equals("write")){
            RAID5.write_to_drives(n_drives, bytes_per_block, file_name);
        }
        
        if(command.equals("read")){
            RAID5.read_from_drives(n_drives, bytes_per_block, file_name);
        }
        
        if(command.equals("rebuild")){
            String char_from_input = Character.toString(file_name.charAt(4));
            int drive_number = Integer.parseInt(char_from_input);
            RAID5.rebuild_drive(n_drives, drive_number, bytes_per_block, file_name);
        }
    } 

    //function for rebuiilding drives
    public static void rebuild_drive(int n_drives, int corrupted_drive_number, int bytes_per_block, String file_name){
        FileInputStream[] from_drives = new FileInputStream[n_drives];
        FileOutputStream output = null;
        
        try{
            output = new FileOutputStream(file_name);
            
            for(int i = 1; i <= n_drives; i++){
                from_drives[i - 1] = new FileInputStream("disc"+Integer.toString(i)+".txt");
            }
            
            ArrayList<Byte> temp = new ArrayList<Byte>();
            FileInputStream temp_reader = null;
            
            if(corrupted_drive_number == 1){
                temp_reader = new FileInputStream("disc2.txt");
            }
            else{
                temp_reader = new FileInputStream("disc1.txt");
            }
            
            while(true){
                Byte b = (byte)temp_reader.read();
                if(b != -1){
                    temp.add(b);
                }
                else{
                    break;
                }
            }
            
            temp_reader.close();
            
            int n_rows = (temp.size() - 1) / bytes_per_block + 1;
            
            byte[][][] bytes = new byte[n_rows][n_drives][bytes_per_block];
            
            for(int row = 0; row < n_rows; row++){
                for(int drive = 0; drive < n_drives; drive++){
                    for(int j = 0; j < bytes_per_block; j++){
                        if(drive != corrupted_drive_number - 1){
                            bytes[row][drive][j] = (byte)from_drives[drive].read();
                        }
                    }
                }
            }
            
            for(int row = 0; row < n_rows; row++){
                for(int j = 0; j < bytes_per_block; j++){
                    int xor;
                    if(corrupted_drive_number == 1){
                        xor = bytes[row][1][j];
                        for(int drive = 2; drive < n_drives; drive++){
                            xor = xor ^ ((int)bytes[row][drive][j]);
                        }
                    }
                    else{
                        xor = bytes[row][0][j];
                        for(int drive = 1; drive < n_drives; drive++){
                            if(drive != corrupted_drive_number - 1){
                                xor = xor ^ ((int)bytes[row][drive][j]);
                            }
                        }
                    }
                    bytes[row][corrupted_drive_number - 1][j] = (byte)(0xff & xor);
                }
            }
            
            for(int row = 0; row < n_rows; row++){
                for(int j = 0; j < bytes_per_block; j++){
                    output.write((byte)bytes[row][corrupted_drive_number - 1][j]);
                }
            }
            
            output.close();
            for(int i = 1; i <= n_drives; i++){
                from_drives[i-1].close();
            }
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
    }
       
    //function for writing to drives
    public static void write_to_drives(int n_drives, int bytes_per_block, String file_name){
        
        FileInputStream in = null;
        FileOutputStream[] to_drives = new FileOutputStream[n_drives]; 
        
        //write to data of file_name
        try{
            in = new FileInputStream(file_name);
            
            for(int i = 1; i <= n_drives; i++){
                to_drives[i-1] = new FileOutputStream("disc"+Integer.toString(i)+".txt");
            }
            
            ArrayList<Byte> temp = new ArrayList<Byte>();
            
            int index1 = 0;
            int index2 = 0;
            int current_row;
            int current_drive;
            int bytes_per_row = bytes_per_block * n_drives;
            boolean at_parity_block;
            
            while(true) {
                
                current_row = (index1 + index2) / bytes_per_row;
                current_drive = ((index1 + index2) % bytes_per_row) / bytes_per_block + 1;
                at_parity_block = ((current_row + current_drive) % n_drives == 0);
                
                if(at_parity_block == false){
                    Byte b = (byte)in.read();
                    if (b != -1) {
                        temp.add(b);
                        index1 += 1;
                    } 
                    else {
                        break;
                    }
                }
                else{
                    temp.add((byte)0);
                    index2 += 1;
                }
            }
            
            byte[] byte_array = new byte[temp.size()];
            
            for(int i = 0; i < temp.size(); i++){
                byte_array[i] = temp.get(i).byteValue();
            }
            
            int num_rows = (byte_array.length - 1)/bytes_per_row + 1;           
            
            byte[][][] bytes = new byte[num_rows][n_drives][bytes_per_block];    
            
            int index = 0;
            
            while(index < byte_array.length){
                current_row = index / bytes_per_row;
                current_drive = (index % bytes_per_row) / bytes_per_block + 1;
                bytes[current_row][current_drive - 1][index % bytes_per_block] = byte_array[index];
                index += 1;
            }
            
            //Uses xor to calculate P from the four data blocks
            for(int row = 0; row < num_rows; row++){
                for(int j = 0; j < bytes_per_block; j++){
                    int xor;
                    int parity_drive = n_drives - (row % n_drives);
                    if(parity_drive == 1){
                        xor = (int)bytes[row][1][j];
                        for(int drive = 3; drive <= n_drives; drive++){
                            xor = xor ^ ((int)bytes[row][drive - 1][j]);
                        }
                        bytes[row][parity_drive - 1][j] = (byte)(0xff & xor);
                    }  
                    else{
                        xor = bytes[row][0][j];
                        for(int drive = 2; drive <= n_drives; drive++){
                            if((drive == parity_drive) == false){
                                xor = xor ^ ((int)bytes[row][drive - 1][j]);
                            }
                        }
                        bytes[row][parity_drive - 1][j] = (byte)(0xff & xor);
                    }
                }
            }
            
            for(int drive = 1; drive <= n_drives; drive++){
                for(int row = 0; row < num_rows; row++){
                    for(int j = 0; j < bytes_per_block; j++){
                        try{
                            to_drives[drive - 1].write(bytes[row][drive - 1][j]);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        
            in.close();
            for(int i = 1; i <= n_drives; i++){
                to_drives[i-1].close();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
       
    //function for reading from drives
    public static void read_from_drives(int n_drives, int bytes_per_block, String file_name){
        //reads data from the RAID5 storage
        
        FileInputStream[] from_drives = new FileInputStream[n_drives];
        FileOutputStream output = null;
        
        //splits the byte stream over the disks using raid5 structure
        try{
            output = new FileOutputStream(file_name);
            
            for(int i = 1; i <= n_drives; i++){
                from_drives[i-1] = new FileInputStream("disc"+Integer.toString(i)+".txt");
            }
            
            byte[] buffer = new byte[bytes_per_block];
            
            ArrayList<Byte> temp = new ArrayList<Byte>();
            FileInputStream temp_reader = new FileInputStream("disc1.txt");
            
            while(true){
                Byte b = (byte)temp_reader.read();
                if(b != -1){
                    temp.add(b);
                }
                else{
                    break;
                }
            }
            
            temp_reader.close();
            
            int n_rows = (temp.size() - 1) / bytes_per_block + 1;
            boolean at_parity_block;
            byte burner_var;
            
            for(int row = 0; row < n_rows; row++){
                for(int drive = 1; drive <= n_drives; drive++){
                    
                    at_parity_block = ((row + drive) % n_drives == 0);     
                    
                    if(at_parity_block == false){
                        for(int j = 0; j < bytes_per_block; j++){
                            buffer[j] = (byte)from_drives[drive - 1].read();
                        }
                        for(int k = 0; k < bytes_per_block; k++){
                            output.write(buffer[k]);
                        }
                    }
                    else{
                        for(int j = 0; j < bytes_per_block; j++){
                            burner_var = (byte)from_drives[drive - 1].read();
                        }
                    }
                }
            }
            
            output.close();
            for(int i = 1; i <= n_drives; i++){
                from_drives[i-1].close();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}



