import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author Lucy
 * @date December 2011
 */
public class BallPanel extends JPanel implements MouseListener, MouseMotionListener{

    ArrayList<Ball> MyBalls;
    SloganArray[] Slog;

    boolean IsDragged = false;
    JButton GatherLetterB;
    static int BallIndex, SlogIndex;
    Timer t = new Timer(50, new ActionListener() {
       public void actionPerformed(ActionEvent e) {
       repaint();
       }});
   
/* Constructeur, ajoute les listener au panel
 */
public BallPanel(int Counter, String... Str) 
        throws IllegalArgumentException{
  super();
  if(Str.length > 500){
      throw new IllegalArgumentException("Too many arguments. 50 maximum! ");
  }
    setBackground(Color.BLACK);
     MyBalls = new ArrayList<Ball>(); 
     Slog = new SloganArray[Counter];
     for(int i = 0; i < Counter; i++){
      Slog[i] = new SloganArray(Str[i], 0, 800, 0, 650);
      Slog[i].setSize(10);
      SlogIndex++;  
     }//end 
     addMouseListener(this);
     addMouseMotionListener(this);
     t.start();
     
} // end constructor




    @Override
public void paintComponent(Graphics g){          
   g.setColor(Color.BLACK);
   g.fillRect(0, 0, getWidth(), getHeight());     
Ball.draw(g, MyBalls, BallIndex, getWidth(), getHeight());
if(SlogIndex > 0){ 
              for(int i = 0; i < SlogIndex - 1; i++)
              Slog[i].setRandomColor();
   SloganArray.drawSlog(g, Slog, SlogIndex, getWidth(), getHeight());
   
}
g.setColor(Color.DARK_GRAY);
g.setFont(new Font("monospaced", Font.BOLD, 12));
g.drawString("by Lucy Linder", getWidth() - 101, getHeight() - 5);
} // end paintcomponant


public void mousePressed(MouseEvent e){
 if(IsDragged)
     return;
 if(e.isMetaDown()){
   MyBalls.add(BallIndex, new Ball(0, getWidth(), 0, getHeight(), e));
   BallIndex++; 
 }else if(e.isAltDown()){
  for(int i = 0; i < SlogIndex; i++){ 
   Slog[i].hideSlogan(!Slog[i].getSloganHidden());
   if(Slog[i].getSloganHidden()){
     GatherLetterB.setEnabled(false);
     }else{
     GatherLetterB.setEnabled(true);
     } // end if   
  } // end if
 }else if(e.isControlDown()){
     MyBalls = new ArrayList<Ball>();
     BallIndex = 0;
 }else{
    Ball.headTowards(MyBalls, BallIndex, e.getX(), e.getY());
    for(int i=0; i < SlogIndex; i++)
    Slog[i].headTowards(e.getX(), e.getY());
 } // end if 
 IsDragged = true;
 } // end mousePressed

public void mouseClicked(MouseEvent e){};
public void mouseExited(MouseEvent e){};
public void mouseReleased(MouseEvent e){IsDragged = false;};
public void mouseEntered(MouseEvent e){};
public void mouseMoved(MouseEvent e){};
public void mouseDragged(MouseEvent e){
  Ball.headTowards(MyBalls, BallIndex, e.getX(), e.getY()); 
  for(int i=0; i < SlogIndex; i++)
  Slog[i].headTowards(e.getX(), e.getY());
}// end MouseDragged


public JPanel getButton(){
  JPanel JP = new JPanel();
  
  JButton NewBallB = new JButton("new ball");
  NewBallB.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        MyBalls.add(BallIndex, new Ball(0, getWidth(), 0, getHeight()));
        BallIndex++;
    } // end actionPerf
}); // end anonymous inner class
  
  JButton PauseB = new JButton("Pause");
  PauseB.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        String AComm = e.getActionCommand();
        JButton a = (JButton) e.getSource();
        if(AComm.equals("Pause")){
        t.stop();
        a.setText("Play  ");
        }else{
        t.start();
        a.setText("Pause");   
        }
    } // end actionPerf
}); // end anonymous inner class
 
  GatherLetterB = new JButton("Gather letters");
  GatherLetterB.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        String AComm = e.getActionCommand();
        JButton a = (JButton) e.getSource();
        if(AComm.equals("Gather letters")){
          for(int i=0; i < SlogIndex; i++)
          Slog[i].gatherSlogan(true);
        a.setText("Spread letters");
        }else{
         for(int i=0; i < SlogIndex; i++)
         Slog[i].gatherSlogan(false);
        //Slog.setRandomDirection();
        a.setText("Gather letters");   
        }
    } // end actionPerf
}); // end anonymous inner class
  
  JP.add(NewBallB);
  if(SlogIndex > 0)
  JP.add(GatherLetterB);
  JP.add(PauseB);
  return JP;
}//end getButton

}// end class



