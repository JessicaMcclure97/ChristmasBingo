import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Controls the bingo buttons to check
 * off which have already been played.
 */
public class RoundButton extends JButton {

    public static final Color RED = new Color( 228, 102, 60);
    public static final Color GREEN = new Color( 142, 229, 22);

    public RoundButton(String label) {
        super(label);

        setBackground(RED);
        setFocusable(false);

        /*
         These statements enlarge the button so that it
         becomes a circle rather than an oval.
        */
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

        /*
         This call causes the JButton not to paint the background.
         This allows us to paint a round background.
        */
        setContentAreaFilled(false);

        /*
         Lets the user select if the ball has been picked or not.
         */
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if(getBackground() == GREEN){
                    setBackground(RED);
                }else{
                    setBackground(GREEN);
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        //if (getModel().isPressed()) {
            g.setColor(getBackground());

        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(Color.darkGray);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    // Hit detection.
    Shape shape;

    public boolean contains(int x, int y) {
        // If the button has changed size,  make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}