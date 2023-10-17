package javaifelsechecker;

import java.util.*;
import java.util.regex.*;

public class JavaIfElseChecker
{
    static Scanner sc = new Scanner(System.in);

    //main method
    public static void main(String[] args)
    {
        Boolean flag = true;

        //flag-controlled loop
        while (flag)
        {
            String testInput = getInput();

            System.out.println("Test Statement: " + testInput);
            System.out.println("Result:");

            Boolean validSyntax = checkSyntax(testInput);

            if (validSyntax)
            {
                System.out.println("You have entered a valid if-else statement!");
            }

            System.out.println("\n\nDo you want to continue?(Y/N)");
            String str = sc.nextLine().toLowerCase();
            if (str.equals("n") || str.equals("no"))
            {
                flag = false;
            }
        }

    }

    static Boolean checkSyntax(String textInput)
    {

        Boolean validSyntax = false;

        //checks each part of the if-then-else statement, returns true if all parts are correct
        if (checkIf(textInput))
            if (checkCondition(textInput))
                if (checkIfStmt(textInput))
                    if (checkElse(textInput))
                            validSyntax = true;
                    
                        
        
        
        return validSyntax;
    }

    static boolean checkIf(String testStmt)
    {
        String regex = "if.*";
        Pattern ptn1 = Pattern.compile(regex);
        Pattern ptn2 = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        if (ptn1.matcher(testStmt).matches())
        {
            return true;
        } else if (ptn2.matcher(testStmt).matches())
        {
            System.out.println("Invalid Statement -- 'if' must be lowercase!");
            System.exit(0);
            return false;
        } else
        {
            System.out.println("Invalid Statement -- Must begin with an 'if'");
            System.exit(0);
            return false;
        }
    }

    static boolean checkCondition(String testStmt)
    {
        String regex1 = "if\\(.*";
        String regex2 = "if(\\((?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*)\\.isEmpty\\(\\)))(?:(&&|\\|\\|)(?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*+)\\.isEmpty\\(\\))))*).*";
        String regex3 = "if(\\((?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*)\\.isEmpty\\(\\)))(?:(&&|\\|\\|)(?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*+)\\.isEmpty\\(\\))))*\\)).*";

        Pattern ptn1 = Pattern.compile(regex1);
        Pattern ptn2 = Pattern.compile(regex2);
        Pattern ptn3 = Pattern.compile(regex3);

        //checks the opening parenthesis of the condition
        if (!ptn1.matcher(testStmt).matches())
        {
            System.out.println("Invalid Statement -- Condition must start with a '(' ");
            System.exit(0);
            return false;
        } 
        //checks the syntax inside of the condition
        else if (!ptn2.matcher(testStmt).matches())
        {
            System.out.println("Invalid Statement -- Syntax error in the condition");
            System.exit(0);
            return false;
        }
        //checks the closing parenthesis of the condition
        else if (!ptn3.matcher(testStmt).matches())
        {
            System.out.println("Invalid Statement -- Condition must end with a ')' ");
            System.exit(0);
            return false;
        }

        return true;
    }

    static boolean checkIfStmt(String testStmt)
    {
        String regex1 = ".*\\)\\{.*";
        String regex2 = "if(\\((?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*)\\.isEmpty\\(\\)))(?:(&&|\\|\\|)(?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*+)\\.isEmpty\\(\\))))*\\))\\{(?:(?:(?:System.out.print(?:ln)?\\((?:(?:[a-zA-Z_]\\w*|[\\\"']\\w*[\\\"']))\\)\\;)|(?:[A-Za-z_]\\w*(?:\\+\\+|\\-\\-))\\;|(?:[a-zA-Z_]\\w*\\=(?:[a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?|\\\"(\\w| )*\\\"))\\;)*|(?:[a-zA-Z_]\\w*\\=([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?)((\\+|\\-|\\*|\\/)([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?))*)\\;)(\\}.*)?";
        String regex3 = "if(\\((?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*)\\.isEmpty\\(\\)))(?:(&&|\\|\\|)(?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*+)\\.isEmpty\\(\\))))*\\))\\{(?:(?:(?:System.out.print(?:ln)?\\((?:(?:[a-zA-Z_]\\w*|[\\\"']\\w*[\\\"']))\\)\\;)|(?:[A-Za-z_]\\w*(?:\\+\\+|\\-\\-))\\;|(?:[a-zA-Z_]\\w*\\=(?:[a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?|\\\"(\\w| )*\\\"))\\;)*|(?:[a-zA-Z_]\\w*\\=([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?)((\\+|\\-|\\*|\\/)([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?))*)\\;)\\}.*";
        
        Pattern ptn1 = Pattern.compile(regex1);
        Pattern ptn2 = Pattern.compile(regex2);
        Pattern ptn3 = Pattern.compile(regex3);

        //checks the opening curly brace of the statement
        if (!ptn1.matcher(testStmt).matches())
        {
            System.out.println("Invalid Statement -- The statement inside the 'if' block must start with a '{' ");
            System.exit(0);
            return false;
        }

        //checks the syntax inside of the statement
        else if (!ptn2.matcher(testStmt).matches())
        {
            System.out.println("Invalid Statement -- Syntax error inside the statement of if block");
            System.exit(0);
            return false;
        }

        //checks the closing curly brace of the statement
        else if (!ptn3.matcher(testStmt).matches())
        {
            System.out.println("Invalid Statement -- The statement inside the 'if' block must end with a '}' ");
            System.exit(0);
            return false;
        }
        
        return true;
    }

    static boolean checkElse(String testStmt)
    {
        String regex = ".*\\}else.*";
        String noElse = "if(\\((?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*)\\.isEmpty\\(\\)))(?:(&&|\\|\\|)(?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*+)\\.isEmpty\\(\\))))*\\))\\{(?:(?:(?:System.out.print(?:ln)?\\((?:(?:[a-zA-Z_]\\w*|[\\\"']\\w*[\\\"']))\\)\\;)|(?:[A-Za-z_]\\w*(?:\\+\\+|\\-\\-))\\;|(?:[a-zA-Z_]\\w*\\=(?:[a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?|\\\"(\\w| )*\\\"))\\;)*|(?:[a-zA-Z_]\\w*\\=([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?)((\\+|\\-|\\*|\\/)([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?))*)\\;)\\}";
        
        Pattern ptn1 = Pattern.compile(regex);
        Pattern ptn2 = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Pattern ptn3 = Pattern.compile(noElse);
        
        if (!testStmt.toLowerCase().contains("else"))
        {
            //checks if there is code after <if-stmt>
            if(ptn3.matcher(testStmt).matches())
            {
                return true;
            }
            else
            {
                System.out.println("Invalid Statement -- missing 'else' statement");
                System.exit(0);
                return false;
            }
            
        }
        else if (ptn1.matcher(testStmt).matches())
        {
            return checkElseStmt(testStmt);
        } 
        else if (ptn2.matcher(testStmt).matches())
        {
            System.out.println("Invalid Statement -- 'else' must be lowercase!");
            System.exit(0);
            return false;
        } 
        else
        {
            System.out.println("Invalid Statement --  missing 'else' statement");
            System.exit(0);
            return false;
        }
        
    }

    static boolean checkElseStmt(String testStmt)
    {     
        String regex1 = ".*else\\{.*";
        String regex2 = "if(\\((?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*)\\.isEmpty\\(\\)))(?:(&&|\\|\\|)(?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*+)\\.isEmpty\\(\\))))*\\))\\{(?:(?:(?:System.out.print(?:ln)?\\((?:(?:[a-zA-Z_]\\w*|[\\\"']\\w*[\\\"']))\\)\\;)|(?:[A-Za-z_]\\w*(?:\\+\\+|\\-\\-))\\;|(?:[a-zA-Z_]\\w*\\=(?:[a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?|\\\"(\\w| )*\\\"))\\;)*|(?:[a-zA-Z_]\\w*\\=([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?)((\\+|\\-|\\*|\\/)([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?))*)\\;)\\}(else\\{(?:(?:(?:System.out.print(?:ln)?\\((?:(?:[a-zA-Z_]\\w*|[\\\"']\\w*[\\\"']))\\)\\;)|(?:[A-Za-z_]\\w*(?:\\+\\+|\\-\\-))\\;|(?:[a-zA-Z_]\\w*\\=(?:[a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?|\\\"(\\w| )*\\\"))\\;)*|(?:[a-zA-Z_]\\w*\\=([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?)((\\+|\\-|\\*|\\/)([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?))*)\\;))?(\\}.*)?";
        String regex3 = "if(\\((?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*)\\.isEmpty\\(\\)))(?:(&&|\\|\\|)(?:((?:[a-zA-Z_]\\w*|(?:[0-9]+(?:\\.[0-9]+)?))(?:[<>]=?|==|!=)(?:[a-zA-Z_]\\w*|[0-9]+(?:\\.[0-9]+)?))|((?:[a-zA-Z_]\\w*)\\.(?:contains|equals)\\((?:[A-Za-z0-9]+|[\\\"']\\w*[\\\"'])\\))|((?:[a-zA-Z_]\\w*+)\\.isEmpty\\(\\))))*\\))\\{(?:(?:(?:System.out.print(?:ln)?\\((?:(?:[a-zA-Z_]\\w*|[\\\"']\\w*[\\\"']))\\)\\;)|(?:[A-Za-z_]\\w*(?:\\+\\+|\\-\\-))\\;|(?:[a-zA-Z_]\\w*\\=(?:[a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?|\\\"(\\w| )*\\\"))\\;)*|(?:[a-zA-Z_]\\w*\\=([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?)((\\+|\\-|\\*|\\/)([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?))*)\\;)\\}(else\\{(?:(?:(?:System.out.print(?:ln)?\\((?:(?:[a-zA-Z_]\\w*|[\\\"']\\w*[\\\"']))\\)\\;)|(?:[A-Za-z_]\\w*(?:\\+\\+|\\-\\-))\\;|(?:[a-zA-Z_]\\w*\\=(?:[a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?|\\\"(\\w| )*\\\"))\\;)*|(?:[a-zA-Z_]\\w*\\=([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?)((\\+|\\-|\\*|\\/)([a-zA-Z_]\\w*|[1-9][0-9]*(\\.[0-9]+)?))*)\\;)\\})?";

        Pattern ptn1 = Pattern.compile(regex1);
        Pattern ptn2 = Pattern.compile(regex2);
        Pattern ptn3 = Pattern.compile(regex3);
        
        //checks the opening curly brace of the statement
        if (!ptn1.matcher(testStmt).matches())
        {
            System.out.println("Invalid Statement -- The statement inside the 'else' block must start with a '{' ");
            System.exit(0);
            return false;
        }

        //checks the syntax inside of the statement
        else if (!ptn2.matcher(testStmt).matches())
        {
            System.out.println("Invalid Statement -- Syntax error inside the statement of else block");
            System.exit(0);
            return false;
        }

        //checks the closing curly brace of the statement
        else if (!ptn3.matcher(testStmt).matches())
        {
            System.out.println("Invalid Statement -- The statement inside the 'else' block must end with a '}' ");
            System.exit(0);
            return false;
        }
        
        return true;
    }

    static String getInput()
    {
        System.out.println("Enter a test statement:");

        String ifStmt = ""; //empty string

        while (sc.hasNextLine())    //for multi-line input
        {
            String str = sc.nextLine();
            if (str == null || str.isEmpty())
            {
                break;
            }
            ifStmt += str;
        }

        String regex = "(\\s*|\n)";     //searches for whitespaces and new lines
        String testStmt = ifStmt.replaceAll(regex, "");     //removes all unnecessary whitespaces and newlines
        return testStmt;
    }
}
