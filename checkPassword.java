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
        System.out.println("Enter your password:");
        String userPassword = in.nextLine();

        /* replace Scanner with this for hidden password input 
        Console cons;
        char[] passwd;
        String userPassword;
        if ((cons = System.console()) != null &&
            (passwd = cons.readPassword("%s", "Enter your password:\n")) != null) {
            userPassword = new String(passwd);
            java.util.Arrays.fill(passwd, ' ');
        }
        else {
            System.out.println("Error: Could not set up system console for user input.");
            return;
        }
        */

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
                        if (userPassword.equals(filePassword)){
                            System.out.format("\nOh no! Your password (%s) is common!\n\n", userPassword);
                            return;
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

        System.out.println("\nCongrats! Your password is not common!\n");
        return;
    }

}
