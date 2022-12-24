import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * GUI for the User Interface
 */
public class MasterBoardGUI {

    JFrame frame;
    JPanel displayPanel;

    int maxNumber;
    String bingoCalls[];
    BingoMasterBoard boardController;
    JPanel content;
    RoundButton buttons[];

    MasterBoardGUI(int maxNumber, String[] bingoCalls){
        this.maxNumber = maxNumber;
        this.bingoCalls = bingoCalls;
        boardController = new BingoMasterBoard(maxNumber);

        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        JFrame.setDefaultLookAndFeelDecorated(true);

        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File("./../images/ChristmasTreePattern.jpeg"));
            frame.setContentPane(new JPanel(new BorderLayout()) {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, 2000,1200, null);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        initialLayout();
        masterBoard();
        frame.add(content);
        frame.setVisible(true);

    }

    private void initialLayout(){
        content = new JPanel(new BorderLayout(5,5));
        content.setOpaque(false);

        //header
        JLabel headerLabel = new JLabel("McClure Christmas Bingo", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD|Font.ITALIC, 100));
        headerLabel.setForeground(new Color(1, 50, 32));

        //center
        JPanel directionsPanel = new JPanel();
        directionsPanel.setSize(200,50);
        directionsPanel.setOpaque(false);

            JLabel ballPickedLabel = new JLabel(" Roll to Start the Game! ", SwingConstants.CENTER);
            ballPickedLabel.setFont(new Font("Serif", Font.ITALIC, 60));
            ballPickedLabel.setForeground(new Color(1, 50, 32));
            ballPickedLabel.setOpaque(true);
            ballPickedLabel.setBackground(new Color(251, 172, 1));

        directionsPanel.add(ballPickedLabel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout (centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

            displayPanel = new JPanel();
            displayPanel.setOpaque(false);
            displayPanel.setLayout(new FlowLayout());

        centerPanel.add(displayPanel);
        centerPanel.add(directionsPanel);

        //roll button
        JButton rollButton = new JButton("<html><center> Roll <br> Bingo <br> Ball </html>");
        rollButton.setFont(new Font("Serif", Font.PLAIN, 30));
        rollButton.setForeground(new Color(1, 50, 32));
        rollButton.setBackground(new Color(251, 172, 1));
        rollButton.setBorderPainted(false);
        rollButton.setOpaque(true);
        rollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int ballPicked = boardController.pickNumber();
                
                //update text on what ball was picked 
                ballPickedLabel.setText("<html><center> Ball Picked: " + ballPicked +" <br> "+bingoCalls[ballPicked-1]+"</html> ");
                //highlight specific ball 
                buttons[ballPicked-1].doClick(); 
            }
        });

        //reset button
        JButton resetButton = new JButton("<html><br> Reset Bingo Game <br> <br></html>");
        resetButton.setFont(new Font("Serif", Font.PLAIN, 20));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.setVisible(false);
                new MasterBoardGUI(maxNumber, bingoCalls);
            }
        });

        //adding to frame
        content.add(rollButton, BorderLayout.EAST);
        content.add(new JPanel(){
            @Override
            public void setOpaque(boolean isOpaque) {
                super.setOpaque(false);
            }
        }, BorderLayout.WEST);
        content.add(headerLabel, BorderLayout.NORTH);
        content.add(centerPanel, BorderLayout.CENTER);
        content.add(resetButton, BorderLayout.SOUTH);

    }

    private void masterBoard(){

        JPanel board = new JPanel();
        board.setBackground(new Color( 23, 88, 0));
        //board.setSize(300,300);

        GridLayout layout = new GridLayout(maxNumber/10, 0,2,2);
        board.setLayout(layout);

        //initialise buttons 
        buttons = new RoundButton[maxNumber];

        for (int i = 0; i < maxNumber; i++) {
            buttons[i] = new RoundButton(String.valueOf(i+1));
        }
        for(int i = 0; i < maxNumber; i++)  board.add(buttons[i]); 
        
        displayPanel.add(board);
    }
}
