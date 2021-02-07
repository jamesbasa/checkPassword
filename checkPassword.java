import java.util.*;
import java.io.Console;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;


// Takes a password as input from user and checks if it is in the password list file
public class checkPassword{

    public static void main(String args[]) throws Exception {

        Scanner in = new Scanner(System.in);
        String userPassword;
        String hidden = "";
        boolean matchedPassword = false;
        boolean playing = true;
    
        while (playing) {

            // ask user if they want input to be hidden in the Terminal
	        System.out.println("Would you like your input to be hidden? (y/n):");
	        while (!hidden.matches("(?i)y|n")) {
	            hidden = in.nextLine();
	                
	            if (!hidden.matches("(?i)y|n")) {
	                System.out.println("Invalid input. (y/n):");
	            }
	        }

	        // receive hidden password input
	        if (hidden.matches("(?i)y")) {
	            Console cons;
	            char[] passwd;
	                
	            if ((cons = System.console()) != null &&
	                (passwd = cons.readPassword("%s", "Enter your password:\n")) != null) {
	                    userPassword = new String(passwd);
	                    java.util.Arrays.fill(passwd, ' ');
	            }
	            else {
	                    System.out.println("Error: Could not set up system console for user input.");
	                    return;
	            }
	        }
            // receive visible password input
	        else {
	            System.out.println("\nEnter your password:");
	            userPassword = in.nextLine();
	        }


	        File file = new File("rockyou.txt");
	        if (file.exists()){

	            if (file.isFile() && file.canRead()){
	                
	                BufferedReader br = null;

	                try {
	                    // BufferedReader reads from the password file
	                    br = new BufferedReader(new FileReader(file));
	                    String filePassword;

	                    while ((filePassword = br.readLine()) != null){
	                        //System.out.println(filePassword);

                            // password was found in the password file
	                        if (userPassword.equals(filePassword)){
                                matchedPassword = true;
	                            if (hidden.matches("(?i)y")) {
	                                System.out.println("\nOh no! Your password is common!");
	                            }
	                            else {
	                                System.out.format("\nOh no! Your password (%s) is common!\n", userPassword);
	                            }
	                            break;
	                        }
	                    }
	                }

	                catch (IOException exception) {
	                    exception.printStackTrace();
	                }

	                finally {
	                    try {
	                        if (br != null) {
	                            br.close();
	                        }
	                    }
	                    catch (IOException exception) {
	                        exception.printStackTrace();
	                    }
	                }

	            }
	        }

	        if (!matchedPassword) System.out.println("\nCongrats! Your password is not common!");

            // ask if the user wants to run the program again
            System.out.println("\nWould you like to try another input? (y) or type anything else to quit:");
	        if (!in.nextLine().matches("(?i)y")) {
	            playing = false;
	        }

            System.out.println();
            matchedPassword = false;
            hidden = "";

        }

        return;
    }

}
