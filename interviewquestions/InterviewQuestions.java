//Student Name: Joshua Guillot
//LSU ID: 893430281
//TA: Zachary Zaulkner
package interviewquestions;

import java.util.Stack;

public class InterviewQuestions {


    public static void main(String[] args) {  

       System.out.println(isPalendrome("RaceCar"));
       System.out.println(isPalendrome("HelloWorld"));
       System.out.println(isPalendrome("W"));
       System.out.println("---------------");
       System.out.println(IsBalancedExpression("{()}(){()}"));
       System.out.println(IsBalancedExpression("{(})"));
       System.out.println(IsBalancedExpression("{(5 + 6) - (3 * 4)}"));
       System.out.println("---------------");
       System.out.println(EvaluateExpression("45+34*-"));
       System.out.println(EvaluateExpression("138*+"));
       System.out.println(EvaluateExpression("6+38*"));
       System.out.println(EvaluateExpression("65+38*7"));
    }

    private static boolean isPalendrome(String string) {
        String input = string.toLowerCase();
        Stack<Character> stack = new Stack<Character>();
        if(input.length() == 1)
            return true;
        for (int j = 0; j < string.length(); j++) {
           if(j < input.length()/2)
           {
                stack.push(input.charAt(j));
           }
           else if(j  == input.length()/2 && input.length()%2 != 0)
                {   
                    stack.push(input.charAt(j));
                    stack.pop();
                }
           else if(stack.isEmpty() == false){

            if(input.charAt(j) == stack.peek())
                {   
                    stack.pop();  
                }   
            }  
        } 
        return(stack.isEmpty());
    }

    private static boolean IsBalancedExpression(String string) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < string.length(); i++) {
            if(i == 0)
            {       
                stack.push(string.charAt(i));
            }
            else if(string.charAt(i) == ')' && stack.peek() == '('){
                stack.pop();
            }
            else if(string.charAt(i) == '}' && stack.peek() == '{'){
                stack.pop();
            }
            else if (string.charAt(i) == '{' || string.charAt(i) == '('){ 
                stack.push(string.charAt(i));
            }         
        }
    return(stack.isEmpty());
    }

    private static int EvaluateExpression(String string) {
             Stack<Integer> stack = new Stack<Integer>();
             
        for (int i = 0; i < string.length(); i++) {
            if(Character.isDigit(string.charAt(i)))
            {       
                stack.push(Character.getNumericValue(string.charAt(i)));
                
            }
            if(string.charAt(i) == '+' || string.charAt(i) == '-' || string.charAt(i) == '*' || string.charAt(i) == '/') {
                int sum1;
                int sum2;
            if(stack.size() < 2){
                System.out.println("invalid expression");
                return 0;
            }
            else if(string.charAt(i) == '+'){
                sum1 = (stack.pop());
                sum2 = (stack.pop());               
                stack.push(sum1 + sum2);   
            } 
            else if(string.charAt(i) == '-'){
                sum1 = (stack.peek());
                stack.pop();
                sum2 = (stack.peek());                
                stack.pop();
                stack.push(sum2 - sum1);                 
            }  
            else if(string.charAt(i) == '*'){
                sum1 = (stack.peek());
                stack.pop();
                sum2 = (stack.peek());
                stack.pop();
                stack.push(sum1 * sum2);       
            } 
            else if(string.charAt(i) == '/'){
                sum1 = (stack.peek());
                stack.pop();
                sum2 = (stack.peek());
                stack.pop();
                stack.push(sum2 / sum1);         
            }
        }
        }
        if(stack.size() > 1){
                System.out.println("invalid expression");
                return(0);
            } 
        return(stack.pop());  
    }
}
