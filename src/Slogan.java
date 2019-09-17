
import java.awt.*;
import java.util.*;
/**
 *
 * @author Lucy
 * @date December 201
 */
public class Slogan extends Ball{
  String Letter;
  Font Police;
  
  
  public Slogan(double xmin, double xmax, double ymin, double ymax, String Letter){
   super(xmin, xmax, ymin, ymax);
   Radius = 15;
   Police = new Font("monospaced", Font.BOLD, Radius);
   Color = Color.WHITE;
   HasBorder = false;
   Speed = 9;
   this.Letter = Letter;
  }// end constructor

  public Slogan(double xmin, double xmax, double ymin, double ymax, String Letter, int Size, Color C, int Speed){
   super(xmin, xmax, ymin, ymax);
   Radius = Size;
   Police = new Font("monospaced", Font.BOLD, Radius);
   Color = C;
   HasBorder = false;
   this.Speed = Speed;
   this.Letter = Letter;
  }// end constructor
   
    @Override
 public void draw(Graphics g){
    g.setColor(Color);
    g.setFont(Police);
    g.drawString(Letter, (int) x, (int) y);
  } // end draw
   
 @Override
 public void move(){

  if(x + Radius >= xmax){
  x = xmax - Radius;
  dx = -(dx);
  } // end if
  if(y >= ymax){
  y = ymax ;
  dy = -(dy);
  } // end if
  if(x  <= xmin){
  x = xmin;
  dx = -(dx);
  } // end if
  if(y - Radius <= ymin){
  y = ymin + Radius;
  dy = -(dy);
  } // end if
  
  x = x + (dx * Speed);
  y = y + (dy * Speed);

} // end move
 
    @Override
public void setSize(int NewSize){
   Radius = NewSize;
   Police = new Font(Police.getFontName(), Police.getStyle(), NewSize);
}// end setSize

public void setFont(Font NewFont){
    Police = NewFont;
}// end setFont
 
 /** This subroutine enables you to gather the letters and form a word in the middle of the panel.
 * By calling it by all the letters in the slogan array, the letters will gather 
 * in the middle of the panel and stop moving.
 * @precondition all the letters/slogan in the array have the same Radius
 * @param Pos the position of the letter in the array
 */
 public void gatherLetter(int Pos,int Index, int Width, int Height){   
    
     double OffsetX = (Width - ((Index - 1) * (Radius/2)))/ 2  + (Radius/2 * Pos); // on peut enlever le /2 de Radius/2 pour que redevienne comme avant
     double OffsetY = Height/2 - Radius/2;
     
      if( x > OffsetX - Radius/2 &&
           x < OffsetX + Radius/2 &&     
           y > OffsetY - Radius/2 &&
           y < OffsetY + Radius/2){
         x = OffsetX;
         y = OffsetY;
           return;
       } // end if
       
       if(x > OffsetX - Radius/2 &&
          x < OffsetX + Radius/2){
       headTowards((int)OffsetX, (int)OffsetY);
        y = y + (dy * Speed);   
        return;
       }// end if
       
       if(y > OffsetY - Radius/2 &&
          y < OffsetY + Radius/2){
        headTowards((int)OffsetX, (int)OffsetY);
        x = x + (dx * Speed);
        return;
        } // end if 
       
     
     /*
       if( x > OffsetX - Speed/2 &&
           x < OffsetX + Speed/2 &&     
           y > OffsetY - Speed/2 &&
           y < OffsetY + Speed/2){
         x = OffsetX;
         y = OffsetY;
           return;
       } // end if
       
       if(x > OffsetX - Speed/2 &&
          x < OffsetX + Speed/2){
       headTowards((int)OffsetX, (int)OffsetY);
        y = y + (dy * Speed);   
        return;
       }// end if
       
       if(y > OffsetY - Speed/2 &&
          y < OffsetY + Speed/2){
        headTowards((int)OffsetX, (int)OffsetY);
        x = x + (dx * Speed);
        return;
        } // end if 
       */
        headTowards((int)OffsetX, (int)OffsetY);
        x = x + (dx * Speed);
        y = y + (dy * Speed);
 }//end gatherLetter
 /**
  * this method calculate the coordinate x of the first letter in order to 
  * draw the slogan at the center of the panel.
   * @param Pos the position of the letter in the array
   * @param Height the height of the panel
   * @param Width the width of the panel
   * @return 
  */
 

} // end class
