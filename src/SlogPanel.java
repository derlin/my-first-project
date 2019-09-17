

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author Lucy
 * @date December 2011
 */
public class SlogPanel extends JPanel implements MouseListener, MouseMotionListener {

    SloganArray[] Slog;
    boolean IsDragged = false;
    JButton GatherLetterB;
    static int SlogIndex;
    Timer t = new Timer(50, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    /* Constructeur, ajoute les listener au panel
     */
    public SlogPanel(int Counter, String... Str)
            throws IllegalArgumentException {
        super();
        if (Str.length > 500) {
            throw new IllegalArgumentException("Too many arguments. 50 maximum! ");
        }
        setBackground(Color.BLACK);
        Slog = new SloganArray[Counter];
        for (int i = 0; i < Counter; i++) {
            Slog[i] = new SloganArray(Str[i], 0, 800, 0, 650);
            Slog[i].setSize(10);
            SlogIndex++;
        }//end
        addMouseListener(this);
        addMouseMotionListener(this);
        t.start();

    } // end constructor


    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (SlogIndex > 0) {
            for (int i = 0; i < SlogIndex - 1; i++)
                Slog[i].setRandomColor();
            SloganArray.drawSlog(g, Slog, SlogIndex, getWidth(), getHeight());

        }
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("monospaced", Font.BOLD, 12));
        g.drawString("by Lucy Linder", getWidth() - 101, getHeight() - 5);
    } // end paintcomponant


    public void mousePressed(MouseEvent e) {
        if (IsDragged) {
            return;

        } else if (e.isAltDown()) {
            for (int i = 0; i < SlogIndex; i++) {
                Slog[i].hideSlogan(!Slog[i].getSloganHidden());
                if (Slog[i].getSloganHidden()) {
                    GatherLetterB.setEnabled(false);
                } else {
                    GatherLetterB.setEnabled(true);
                } // end if
            } // end if

        } else {
            for (int i = 0; i < SlogIndex; i++)
                Slog[i].headTowards(e.getX(), e.getY());
        } // end if
        IsDragged = true;
    } // end mousePressed

    public void mouseClicked(MouseEvent e) {
    }

    ;

    public void mouseExited(MouseEvent e) {
    }

    ;

    public void mouseReleased(MouseEvent e) {
        IsDragged = false;
    }

    ;

    public void mouseEntered(MouseEvent e) {
    }

    ;

    public void mouseMoved(MouseEvent e) {
    }

    ;

    public void mouseDragged(MouseEvent e) {
        for (int i = 0; i < SlogIndex; i++)
            Slog[i].headTowards(e.getX(), e.getY());
    }// end MouseDragged


    public JPanel getButton() {
        JPanel JP = new JPanel();

        JButton PauseB = new JButton("Pause");
        PauseB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String AComm = e.getActionCommand();
                JButton a = (JButton) e.getSource();
                if (AComm.equals("Pause")) {
                    t.stop();
                    a.setText("Play  ");
                } else {
                    t.start();
                    a.setText("Pause");
                }
            } // end actionPerf
        }); // end anonymous inner class

        GatherLetterB = new JButton("Gather letters");
        GatherLetterB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String AComm = e.getActionCommand();
                JButton a = (JButton) e.getSource();
                if (AComm.equals("Gather letters")) {
                    for (int i = 0; i < SlogIndex; i++)
                        Slog[i].gatherSlogan(true);
                    a.setText("Spread letters");
                } else {
                    for (int i = 0; i < SlogIndex; i++)
                        Slog[i].gatherSlogan(false);
                    //Slog.setRandomDirection();
                    a.setText("Gather letters");
                }
            } // end actionPerf
        }); // end anonymous inner class

        if (SlogIndex > 0)
            JP.add(GatherLetterB);
        JP.add(PauseB);
        return JP;
    }//end getButton

}// end class



