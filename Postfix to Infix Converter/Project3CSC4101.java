
package project3csc4101;

/**
 *
 * @author Joshg
 */
public class Project3CSC4101 {


    public static void main(String[] args) {
        
       
        String infixExpr1 = "( 1 + 2 ) / 4 - 5 * 6";
        String infixExpr2 = "( 4 / 2 + 2 ) + ( 5 - 4 ) * 6";
        String infixExpr3 = "14 / 7 + ( 3 - 2 )";
        
        String postfixExpr1 = Convertor.convertToPostfix(infixExpr1);
        String postfixExpr2 = Convertor.convertToPostfix(infixExpr2);
        String postfixExpr3 = Convertor.convertToPostfix(infixExpr3);
            
        System.out.println(infixExpr1 + " => " + postfixExpr1 + " = " + Convertor.evaluatePostfix(postfixExpr1));
        System.out.println(infixExpr2 + " => " + postfixExpr2 + " = " + Convertor.evaluatePostfix(postfixExpr2));
        System.out.println(infixExpr3 + " => " + postfixExpr3 + " = " + Convertor.evaluatePostfix(postfixExpr3));
        
        
    }
    
}
