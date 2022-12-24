import static java.lang.System.exit;
import java.io.*;

/**
 * User validation to make sure that game size entered is correct.
 */
public class StartGame {

    public static void main(String[] args){
        if(args.length != 1){
            System.out.println("To start must enter command: java StartGame <gameSize>");
            exit(0);
        }

        int gameSize = Integer.parseInt(args[0]);

        if(gameSize % 5 != 0 || gameSize > 100 || gameSize <= 0){
            System.out.println("The game size must be divisible by 5. Games size must also be between 5 and 100.");
        }

        String[] bingoCalls = readBingoCalls();
        System.out.println(bingoCalls);
        MasterBoardGUI gui = new MasterBoardGUI(gameSize, bingoCalls);

    }

     
    public static String[] readBingoCalls(){
        String[] bingoCalls = new String[90]; 

        String delimiter = ",";
        try {
            File file = new File("./BingoCalls.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = " ";
            String[] tempArr;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                bingoCalls[counter] = tempArr[1];
                counter++;
            }
            br.close();
        }
        catch(Exception ioe) { 
            ioe.printStackTrace();
        }
  
        return bingoCalls;
    }
    
}
