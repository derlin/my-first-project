
import java.io.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.Event.*;


/**
 *
 * @author Lucy
 * @date December 2011
 */
public class BallFrameAr extends JFrame{

public static void main(String[]args){
  BallFrameAr Win = new BallFrameAr(args);
  Win.setVisible(true);
  Win.setDefaultCloseOperation(EXIT_ON_CLOSE);
} // end main


public BallFrameAr(String[] args){
  super("Moving Balls");
  setSize(800,650);
      Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
          setLocation( (screensize.width - getWidth())/2, 
                                 (screensize.height - getHeight())/2 );
      setLayout(new BorderLayout());
      BallPanel Panel;
      
   String[] Temp = new String[150];
   int Counter = 0;
     
       try{
  // Open the file that is the first 
  // command line parameter
  FileInputStream fstream = new FileInputStream("resources/coeur2.txt");
  // Get the object of DataInputStream
  DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  String strLine;
  //Read File Line By Line
  while ((strLine = br.readLine()) != null)   {
  // Print the content on the console
  Temp[Counter] = strLine;
  Counter++;
  }
  //Close the input stream
  in.close();
    }catch (Exception e){//Catch exception if any
  System.err.println("Error: " + e.getMessage());
  }//end try
  
  Panel = new BallPanel(Counter, Temp);
  add(Panel, BorderLayout.CENTER);
  add(Panel.getButton(), BorderLayout.SOUTH);
     
     
    /*
    if(args.length < 2 || args.length%2 !=0){
      Panel = new BallPanel(args);
    }else{
        
        try{
          Integer.parseInt(args[1]);
        }catch(NumberFormatException e){
         Panel = new BallPanel(args);
         add(Panel, BorderLayout.CENTER);
         add(Panel.getButton(), BorderLayout.SOUTH);
         return;
       }//end try
        
      int x = 0;
      String[] StringTemp;
      StringTemp = new String[args.length/2];
      for(int i = 0; i < args.length; i += 2){
          StringTemp[x] = args[i];
          x++;
      }//end for 
      Panel = new BallPanel(StringTemp);
      x = 0;
      for(int i = 1; i < args.length; i += 2){
      try{
      Panel.Slog[x].setSize(Integer.parseInt(args[i]));
      }catch(NumberFormatException e){
      System.out.println("either you specify a size for each string, or for none.");
      return;
      }//end try
      x++;
     }//end for
    }//end if
      add(Panel, BorderLayout.CENTER);
      add(Panel.getButton(), BorderLayout.SOUTH);
     * 
     */
}// end constructor

} // end BallFrame