import java.awt.*;

/**
 * @author Lucy
 */
public class SloganArray {
    Slogan[] SA;
    int Index;
    static int IndexTemp;
    private Font Police;
    private int Size;
    private Color Color;
    private int Speed;
    private boolean SloganGathered, SloganHidden;

    /**
     * Constructor : create an object of type SloganArray. The instances variables
     * are : SA = an array of letters, with a color, a font, a speed.
     *
     * @param Str  the letters of type Slogan stocked in the array
     * @param xmin the bottom limit of the panel
     * @param xmax the top limit of the panel
     * @param ymin the left limit of the panel
     * @param ymax the right limit of the panel
     */
    public SloganArray(String Str, double xmin,
                       double xmax, double ymin, double ymax) {
        SA = createArray(Str, xmin, xmax, ymin, ymax);
        Index = IndexTemp;
        Size = SA[0].Radius;
        Police = SA[0].Police;
        Color = SA[0].Color;
        Speed = SA[0].Speed;
        SloganGathered = false;
        SloganHidden = false;

    }// end constructor

    /**
     * Constructor : create an object of type SloganArray. The instances variables
     * are : SA = an array of letters, with a color, a font, a speed.
     *
     * @param Str   the letters of type Slogan stocked in the array* @param Size
     * @param C     the color of the letters
     * @param Speed the speed of the letters
     * @param xmin  the bottom limit of the panel
     * @param xmax  the top limit of the panel
     * @param ymin  the left limit of the panel
     * @param ymax  the right limit of the panel
     */
    public SloganArray(String Str, int Size, Color C, int Speed, double xmin,
                       double xmax, double ymin, double ymax) {
        SA = createArray(Str, xmin, xmax, ymin, ymax);
        setColor(C);
        setSpeed(Speed);
        setSize(Size);

        SloganGathered = false;
        SloganHidden = false;
        this.Size = Size;
        Color = C;
        this.Speed = Speed;
    }// end constructor

    /**
     * @param Str
     * @param xmin
     * @param xmax
     * @param ymin
     * @param ymax
     * @return an Array of type Slogan[] containing the letters
     * @throws IllegalArgumentException
     */
    public static Slogan[] createArray(String Str, double xmin,
                                       double xmax, double ymin, double ymax)
            throws IllegalArgumentException {
        if (Str.length() < 1)
            throw new IllegalArgumentException("no letters");

        IndexTemp = Str.length();
        Slogan[] SloganAr;
        SloganAr = new Slogan[IndexTemp];
        String Temp = "";
        for (int i = 0; i < Str.length(); i++) {
            Temp = "";
            Temp += Str.charAt(i);
            SloganAr[i] = new Slogan(xmin, xmax, ymin, ymax, Temp);
        }// end for
        return SloganAr;
    } // end sloganArray


    /**
     * This method draw the slogan in a graphic context depending on the speed, size,
     * color and direction of each of the letters it contains. If "SloganHidden" is
     * true, no drawing takes place. If the SloganGathered is true, the letters will be
     * gathered in the center of the panel.
     *
     * @param g      the graphic context in which the slogan is drawn
     * @param Width  the width of the panel
     * @param Height the height of the panel
     */

    public void draw(Graphics g, int Index, int Width, int Height) {
        if (SloganHidden == true)
            return;

        for (int i = Index - 1; i >= 0; i--) {
            SA[i].setLimits(0, Width, 0, Height);
            if (SloganGathered) {
                SA[i].gatherLetter(i, Index, Width, Height);
            } else {
                SA[i].move();
            }//end if
            SA[i].draw(g);
        }//end for
    }//end draw


    public static void drawSlog(Graphics g, SloganArray[] Array, int SlogIndex, int Width, int Height) {

        if (Array[0].SloganHidden == true)
            return;

        int OffsetY = SlogIndex * -10;
        for (int i = 0; i < SlogIndex; i++) {
            for (int j = 0; j < Array[i].Index; j++) {
                Array[i].SA[j].setLimits(0, Width, 0, Height);
                if (Array[i].SloganGathered) {
                    Array[i].SA[j].gatherLetter(j, Array[i].Index, Width, Height + OffsetY);

                    //Array[i].SA[j].gatherLetter(j, Array[i].Index, Width, Height + OffsetY + 2*Array[i].SA[j].Radius);
                } else {
                    Array[i].SA[j].move();
                }//end if
                Array[i].SA[j].draw(g);
            }//end for
            OffsetY += (Array[i].SA[0].Radius * 2);
        } // end for
    }//end draw


    /**
     * This method changes the direction of each of the letters of the slogan so that
     * they head towards the point given by the x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void headTowards(int x, int y) {
        for (int i = 0; i < Index; i++) {
            SA[i].headTowards(x, y);
        } // end for
    }// end headTowards

    /**
     * Gives a random direction to all of the letters contained in the slogan array.
     */
    public void setRandomDirection() {
        for (int i = 0; i < Index; i++) {
            SA[i].setRandomDirection();
        } // end for
    }// end setRandomDir

    /**
     * set the color of the letters.
     *
     * @param C the new color
     */
    public void setColor(Color C) {
        Color = C;
        for (int i = 0; i < Index; i++) {
            SA[i].setColor(Color);
        }// end for
    }// end setColor

    public void setRandomColor() {
        for (int i = 0; i < Index; i++)
            SA[i].setRandomColor();
    }// end random color

    /**
     * Sets the speed of the letters contained in the array.
     *
     * @param Speed
     */
    public void setSpeed(int Speed) {
        if (Speed < 0 || Speed > 50)
            return;
        this.Speed = Speed;
        for (int i = 0; i < Index; i++) {
            SA[i].setSpeed(Speed);
        }// end for
    }// end setSpeed


    /**
     * Set the size of the letters contained in the array
     *
     * @param Size
     */
    public void setSize(int Size) {
        if (Size < 0)
            return;
        this.Size = Size;
        for (int i = 0; i < Index; i++) {
            SA[i].setSize(Size);
        }// end for
    } // end setSize

    /**
     * hide or show the slogan. If b is set to true, the slogan won't be drawn the next time
     * the draw() method is called.
     *
     * @param b
     */
    public void hideSlogan(boolean b) {
        SloganHidden = b;
    }// end hideSlogan

    /**
     * @return a boolean value. If it is true, the slogan doesn't appear in the panel.
     */
    public boolean getSloganHidden() {
        return SloganHidden;
    }// end getSlog

    /**
     * If gatherSlogan is set to true, the letters will be heading towards the center
     * of the panel the next time draw() is called.
     *
     * @param b
     */
    public void gatherSlogan(boolean b) {
        SloganGathered = b;
    }// end gatherletter

    /**
     * @return a boolean value indicating if the slogan is
     * gathered in/heads towards the center of the panel or not.
     */
    public boolean getSloganGathered() {
        return SloganGathered;
    }// end get...

}// end class
