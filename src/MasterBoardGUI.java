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
    BingoMasterBoard boardController;
    JPanel content;
    RoundButton buttons[];

    MasterBoardGUI(int maxNumber){
        this.maxNumber = maxNumber;
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
            ballPickedLabel.setForeground(new Color(22, 88, 0));

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
                char row = 'a';
                if(ballPicked <  (maxNumber/5)+1 ){
                    row = 'B';
                }else if(ballPicked < ((maxNumber/5)*2 + 1) ){
                    row = 'I';
                }else if(ballPicked < ((maxNumber/5)*3 + 1) ){
                    row = 'N';
                }else if(ballPicked < ((maxNumber/5)*4 + 1) ){
                    row = 'G';
                }else{
                    row = 'O';
                }
                //update text on what ball was picked 
                ballPickedLabel.setText(" Ball Picked: " + row + ballPicked +" ");
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
                new MasterBoardGUI(maxNumber);
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

        GridLayout layout = new GridLayout(5, 0,2,2);
        board.setLayout(layout);

        //initialise buttons 
        buttons = new RoundButton[maxNumber];

        for (int i = 0; i < maxNumber; i++) {
            buttons[i] = new RoundButton(String.valueOf(i+1));
        }

        board.add(new RoundButton("<html> <b>B</b>"));
        for(int i=0; i < maxNumber/5; i++) board.add(buttons[i]);
        board.add(new RoundButton("<html> <b>I</b>"));
        for(int i=0; i < maxNumber/5; i++) board.add(buttons[i+(maxNumber/5)]);
        board.add(new RoundButton("<html> <b>N</b>"));
        for(int i=0; i < maxNumber/5; i++) board.add(buttons[i+(maxNumber/5)*2]);
        board.add(new RoundButton("<html> <b>G</b>"));
        for(int i=0; i < maxNumber/5; i++) board.add(buttons[i+(maxNumber/5)*3]);
        board.add(new RoundButton("<html> <b>O</b>"));
        for(int i=0; i < maxNumber/5; i++) board.add(buttons[i+(maxNumber/5)*4]);

        displayPanel.add(board);
    }
}
