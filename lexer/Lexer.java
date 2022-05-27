//Joshua Guillot
//No one yet :(
package lexical.analyzer;

import java.util.*;

public class Lexer {
    //declaration of things I will use
    public static ArrayList<Token> Tokens;
    public boolean Success = false;
    public Token lexeme = new Token(null, ""); //I defined all tokens with two attributes, type, and the data it contains
    private Token errortoken = new Token("error", "error"); // error token used for syntax errors
    private Map<String, String> lex = Map.ofEntries(Map.entry("if", "IF"),Map.entry("for", "FOR"),Map.entry("while", "WHILE"),Map.entry("function", "FUNCTION"),
                Map.entry("return", "RETURN"),Map.entry("int", "INT"),Map.entry("else", "ELSE"),Map.entry("do", "DO"),Map.entry("break", "BREAK"),Map.entry("=", "ASSIGN"),
                Map.entry("+", "ADD"),Map.entry("-", "SUB"),Map.entry("*", "MUL"),Map.entry("/", "DIV"),Map.entry("%", "MOD"),Map.entry(">", "GT"),Map.entry("<", "LT"),
                Map.entry("<=", "LE"),Map.entry(">=", "GE"),Map.entry("++", "INC"),Map.entry("(", "LP"),Map.entry(")", "RP"),Map.entry("{", "LB"),Map.entry("}", "RB"),
                Map.entry("|", "OR"),Map.entry("&", "AND"),Map.entry("==", "EE"),Map.entry("!", "NEG"),Map.entry(",", "COMMA"),Map.entry(";", "SEMI"),Map.entry("end", "END"));
    
public void Tokenize(String Input) {
    char[] input = Input.toCharArray();
    Tokens = new ArrayList<Token>();
    int index = 0; 
    while (index < input.length)
    {
        if(lexeme.type == null) //if the token is not already defined by type, call findLexType
        {
            findLexType(lexeme, input[index]);
            index++;
        }
        if(index >= input.length)//this is to stop an edge case caused by the way I set up the findLexType method that I'm too laz to fix
            break;
        if(checkIfValid(lexeme, input[index]))//this checks in the newest char received from the input stream is viable with the current lex type of the token; if not it is assumed the token is complete and the process resets with a fresh token
        {
            lexeme.lexime = lexeme.lexime + input[index];
            index++;
        }
        else
        {
            Tokens.add(lexeme);
            lexeme = new Token(null, "");
        }
    }
        printTokens();//calls method to print all tokens from token array
}
private void findLexType(Token lexeme, char c) {//this class defines the token by testing what type the char from the input stream is
        lexeme.lexime = Character.toString(c);
        if(Character.isDigit(c))
        {
            lexeme.type = "digit";
            return;
        }
        if(Character.isAlphabetic(c))
        {
            lexeme.type = "char";
            return;
        }
        if(Character.isSpaceChar(c))
        {
            lexeme.type = "space";
            return;
        }
        if(Character.toString(c) == "\"")
        {
            lexeme.type = "lit";
            return;
        }
        if(checkMap(c))
        {
            lexeme.type = "symbol";
            return;
        }
        else{
            lexeme.type = "unknown";   //default case incase something weird is in the input (such as at the end of a line or an indention) 
        }
    }
private boolean checkMap(char c) {//checks map to get output for token
       return (lex.get(Character.toString(c)) != null);
    }        
private class Token { //token abstract class
            String type; 
            String lexime;
            public Token(String type, String lexime) 
            {
                this.type = type;
                this.lexime = lexime;   
            }
   }         
private void printTokens() {//prints the tokens based on the type criteria
        for(Token item : Tokens){
            if(lex.get(item.lexime.trim()) != null)
                System.out.println(lex.get(item.lexime.trim()));
            else if(item.type == "char")
                System.out.println("IDENT:" + item.lexime + " ");
            else if(item.type == "digit")
                System.out.println("INT_LIT:" + item.lexime + " ");
            else if(item.type == "lit")
                System.out.println("STR_LIT:" + item.lexime + " ");
            else if(item.type == "error") //incase of syntax error
            {
                System.out.println("SYNTAX ERROR: INVALID IDENTITFER NAME");
                System.exit(0);
            }
        }
    }
private boolean checkIfValid(Token lexeme, char test) { //this method checks to see in input char is compatible with the currently selected token
        switch (lexeme.type) {
            case "digit": if(Character.isAlphabetic(test)) Tokens.add(errortoken); //syntax error case
                return (Character.isDigit(test));
            case "char": return (Character.isAlphabetic(test) || Character.isDigit(test));             
            case "space": return (Character.isSpaceChar(test));            
            case "symbol": return (lex.containsKey(lexeme.lexime + test)); 
            case "lit": return !(Character.toString(test) == "\"");
            case "unkown": return false;   
            default: return false;  
        } 
}
}
