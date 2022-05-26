package project3csc4101;

import java.util.Stack;

/**
 *
 * @author Joshg
 */
class Convertor {

    static String convertToPostfix(String infixExpr1) {
        
        String postfixExpr = "";
        Stack<Character> stk = new Stack<>();
        stk.push('#');

        for (int i = 0; i < infixExpr1.length(); i++) {
            char object = infixExpr1.charAt(i);
            if(object == ' ')
                continue;
            if(Character.isDigit(infixExpr1.charAt(i)))
            {
                if(i != 0 && postfixExpr.length() != 0)
                    if(!Character.isDigit(infixExpr1.charAt(i-1)))
                        postfixExpr += ' ';
                postfixExpr += infixExpr1.charAt(i);
            }
            else if(object == '(')
                stk.push('(');
            else if(object == ')') {            
                while(stk.peek() != '#' && stk.peek() != '(') {
                    if(postfixExpr.length() != 0)
                        postfixExpr += ' ';
                    postfixExpr += stk.peek(); 
                    stk.pop();
                }
                stk.pop(); 
            }
            else {

                if(pemdas(object) > pemdas(stk.peek()))
                    stk.push(object); 
                else {
                    while(stk.peek() != '#' && pemdas(object) <= pemdas(stk.peek())) {
                    if(postfixExpr.length() != 0)    
                        postfixExpr += ' ';
                    postfixExpr += stk.peek();   
                    stk.pop();
					}
					stk.push(object);
					}
				}
      }
        while(stk.peek() != '#') {
            postfixExpr += ' ';
            postfixExpr += stk.peek();    
            stk.pop();
        }
    return postfixExpr;
    }

    static double evaluatePostfix(String postfixExpr1) {
        Stack<Double> stk = new Stack<>();
        String currString = "";
        double A, B, C;
        postfixExpr1 += '#';
        
       for (int i = 0; i < postfixExpr1.length(); i++) {
            C = 0;
            char object = postfixExpr1.charAt(i);
            
            if(object == '#')
                return stk.pop();
            if(object == ' '){
                if(currString != "")
                    stk.add(Double.parseDouble(currString));
                currString = "";
                continue;
            }   
            if(Character.isDigit(object)){
                
                currString += object;
                continue;
            }
            A = stk.pop();
            B = stk.pop();
           
            if(object == '*')
               C = A * B; 
            else if(object == '/')
               C = B / A; 
            else if(object == '+')
               C = A + B; 
            else if(object == '-')
               C = B - A; 
            
            stk.add(C);
    }
       return stk.pop();
    }

    private static int pemdas(char ch) { 
        if(ch == '+' || ch == '-') {
            return 1;              
        }else if(ch == '*' || ch == '/') {
            return 2;            
        }else {
            return 0;
        }
    }
    
}
