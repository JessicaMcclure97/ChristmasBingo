import static java.lang.System.exit;

/**
 * User validation to make sure that game size entered is correct.
 */
public class StartGame {

    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("To start must enter command: java StartGame <gameSize>");
            exit(0);
        }

        int gameSize = Integer.parseInt(args[0]);

        if(gameSize % 5 != 0 || gameSize > 100 || gameSize <= 0){
            System.out.println("The game size must be divisible by 5. Games size must also be between 5 and 100.");
        }

        MasterBoardGUI gui = new MasterBoardGUI(gameSize);

    }
}
