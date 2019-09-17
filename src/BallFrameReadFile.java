
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
public class BallFrameReadFile extends JFrame{

public static void main(String[]args){
  BallFrameReadFile Win = new BallFrameReadFile();
  Win.setVisible(true);
  Win.setDefaultCloseOperation(EXIT_ON_CLOSE);
} // end main


public BallFrameReadFile(){
  super("Moving sketch");
  setSize(600,550);
      Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
          setLocation( (screensize.width - getWidth())/2, 
                                 (screensize.height - getHeight())/2 );
      setLayout(new BorderLayout());
      SlogPanel Panel;
      
   String[] Temp = new String[150];
   int Counter = 0;
     
       try{
  // Open the file that is the first 
  // command line parameter
  FileInputStream fstream = new FileInputStream("coeur_amir.txt");
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
  
  Panel = new SlogPanel(Counter, Temp);
  add(Panel, BorderLayout.CENTER);
  add(Panel.getButton(), BorderLayout.SOUTH);
     
}// end constructor

} // end BallFrame