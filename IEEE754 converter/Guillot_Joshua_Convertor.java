package project2csc4101;

/**
 *
 * @author Joshg
 * Jguil87
 * Joshua Guillot
 * 
 */
class Convertor {
    static int numIntLength;
    static int numE;

    static String IEEE754(double d) {
        numIntLength = -1;
        numE = 0;
        int lead = 0;
        if( d < 0){
            lead = 1;
            d *= -1;
            }
           
        String Mantissa = findBinary(d, false);
        
        String Exponet = findBinary(numE, true);
        
        return (lead + " | " + Exponet + " | " + Mantissa);
    }

    //I used the same binary conversion method for both the mantissa and exponet to just used a boolean to distinguish between the two; not the most eloquent but it is simple
    private static String findBinary(double num, boolean numm) {
        numIntLength = -1;
        StringBuilder binaryBuilder = new StringBuilder();
        int numInt = (int) num; //integral
        
        int i = 0;
        int j = 0;
        double numFract = num - numInt;

        // binary conversion for the integral part (if found)
        while (numInt > 0) {           
            binaryBuilder.append(numInt % 2);
            numInt /= 2;
            if(numInt/2 == 0 && i < 1  && num != numE){ // find binary for number by dividing by two and adding the modulus of 2 of the remainder to the string
                i++;
            }
            j = binaryBuilder.length();
        }
      
         numIntLength +=  (binaryBuilder.length());
        
          while (binaryBuilder.length() < 8 && numm) { //insures exponet is length 8
                binaryBuilder.append(0);
            }
          
        binaryBuilder.reverse();

        // binary conversion for the fractional part
        if (numFract != 0) {
            
            if(i < 1)  
                i++;
                
            while (numFract != 0 && binaryBuilder.length() < 23+i) {
                numFract *= 2;
                binaryBuilder.append((int) numFract);
                if(((int) numFract) != 0){
                  j++;
                   
                }
                numFract = numFract - (int) numFract;
                
                if(j == 0){
                   numIntLength--;
                 i++;
                }
            }
        }
        
        numE = numIntLength + 127;
       if(!numm)
              binaryBuilder.delete(0, i); //trims the mantissa
       
        return binaryBuilder.toString();
    }   
}
