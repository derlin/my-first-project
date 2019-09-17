
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * This class create balls that move and bounce inside a graphics context.
 * Each ball is given a random location, direction, speed, radius and color. It also contain a boolean HasBorder
 * (randomly attributed) and the limits of the panel it moves into.
 * You can make it move by calling the move subroutine, which updates the location of the ball depending on
 * its speed and direction. The draw(Graphics g) subroutine is crucial since no ball will be visible if
 * you don't call it. It is possible to make the ball heading towards a given point of the panel by
 * calling the headTowards() method.
 *
 * @author Lucy Linder
 * @date December 2011
 */
public class Ball {

    protected Color Color;
    protected boolean HasBorder;
    protected double x, y; // position
    protected double xmin, xmax, ymin, ymax; // limites du panel
    protected double dx, dy; // d�placement de la balle sur les deux axes
    protected int Speed; // rapidit� de la balle
    protected int Radius; // p�rim�tre de la balle

    /**
     * Create a ball in the middle of a panel with a random direction, speed and color.
     *
     * @param xmin the top limit of the panel
     * @param xmax the bottom limit of the panel
     * @param ymin the left limit of the panel
     * @param ymax the right limit of the panel
     */
    public Ball(double xmin, double xmax, double ymin, double ymax) {

        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;

        Radius = (int) (10 * Math.random()) + 2;
        Speed = (int) (10 * Math.random()) + 8;
        Color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        if (Math.random() < 0.5) HasBorder = true;

        x = (xmax - xmin) / 2;
        y = (ymax - ymin) / 2;

        double angle = 2 * Math.PI * Math.random() - Math.PI;
        dx = Math.cos(angle);
        dy = Math.sin(angle);
    } // end constructor

    /**
     * Create a ball from a MouseClicked event.
     * The ball is created at the x and y coordinates of the click.
     *
     * @param xmin the top limit of the panel
     * @param xmax the bottom limit of the panel
     * @param ymin the left limit of the panel
     * @param ymax the right limit of the panel
     * @param e    the event generated by the MousePressed subroutine
     */
    public Ball(double xmin, double xmax, double ymin, double ymax, MouseEvent e) {
        this(xmin, xmax, ymin, ymax);
        double a = e.getX();
        double b = e.getY();
        x = a - Radius;
        y = b - Radius;

    } // end constructor 2

    /**
     * Draws a ball in a panel.
     *
     * @param g the graphics from the graphic context where the ball is drawn.
     */
    public void draw(Graphics g) {
        g.setColor(Color);
        g.fillOval((int) x - Radius, (int) y - Radius, 2 * Radius, 2 * Radius);
        if (HasBorder) {
            g.setColor(Color.WHITE);
            g.drawOval((int) x - Radius, (int) y - Radius, 2 * Radius, 2 * Radius);
        } // end if

    } // end draw

    /**
     * Change the current location (double x, double y) of the ball depending on its
     * speed and direction. If the new location is outside the panel, the ball will "bounce"
     * , i.e. its direction will be reversed.
     */
    public void move() {

        if (x + Radius >= xmax) {
            x = xmax - Radius;
            dx = -(dx);
        } // end if
        if (y + Radius >= ymax) {
            y = ymax - Radius;
            dy = -(dy);
        } // end if
        if (x - Radius <= xmin) {
            x = xmin + Radius;
            dx = -(dx);
        } // end if
        if (y - Radius <= ymin) {
            y = ymin + Radius;
            dy = -(dy);
        } // end if

        x = x + (dx * Speed);
        y = y + (dy * Speed);

    } // end move


    /**
     * Changes the direction of the ball so that it heads toward a given point in the panel.
     *
     * @param e The MouseEvent giving the coordinates of the point where the ball heads toward.
     */
    public void headTowards(int x, int y) {
        double diffx = x - this.x;
        double diffy = y - this.y;
        //if(diffx == 0 && diffy == 0)
        //return;
        double Hypo = Math.sqrt(diffx * diffx + diffy * diffy);
        dx = diffx / Hypo;
        dy = diffy / Hypo;
    } // end headTowards

    /**
     * Updates the limits inside which the ball moves (the sides against which it bounces).
     *
     * @param top    the top limit of the panel
     * @param bottom the bottom limit of the panel
     * @param left   the left limit of the panel
     * @param right  the right limit of the panel
     */
    public void setLimits(int top, int bottom, int left, int right) {
        xmin = top;
        xmax = bottom;
        ymin = left;
        ymax = right;
    }// end setLimits


    /**
     * set the color of the ball.
     */
    public void setColor(Color C) {
        Color = C;
    }// end setColor


    public void setRandomColor() {
        Color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

    /**
     * set the speed of the ball expressed in pixels.
     * The speed must be positive and less than 100 (too rapid for a ball).
     *
     * @param Speed
     */
    public void setSpeed(int Speed) {
        if (Speed < 0 || Speed > 50)
            return;
        this.Speed = Speed;
    }// end setSpeed

    public void setSize(int NewRadius) {
        if (NewRadius < 1)
            return;
        Radius = NewRadius;
    }// end setRadius

    public void setRandomDirection() {
        double angle = 2 * Math.PI * Math.random() - Math.PI;
        dx = Math.cos(angle);
        dy = Math.sin(angle);
    }// end setRandomDir

    public static void draw(Graphics g, ArrayList<Ball> ALB, int Index, int Width, int Height) {
        for (int i = 0; i < Index; i++) {
            if (i % 3 != 0) {
                ALB.get(i).setRandomColor();
            }// end if
            ALB.get(i).setLimits(0, Width, 0, Height);
            ALB.get(i).draw(g);
            ALB.get(i).move();
        }// end for
    }// end draw

    public static void headTowards(ArrayList<Ball> BallArray, int Index, int x, int y) {
        for (int i = 0; i < Index; i++) {
            BallArray.get(i).headTowards(x, y);
        }// end for
    }// end headToeards
} // end class
