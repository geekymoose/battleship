/* 
 * Creation : 9 mars 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;


/**
 * <h1>HexaGrid_DEBUG</h1>
 * <p>public class HexaGrid_DEBUG</p>
 * 
 * @date 9 mars 2015
 * @author Constantin MASSON
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class HexaGrid_DEBUG extends JPanel{

    //Model m;

    int temp, tempX, tempY;
    boolean changedHex = false;

    boolean[][] selected = new boolean[10][10];
    int[][] tab = {{0, 1, 2, 0, 0, 1, 0, 1, 0, 0},
    {0, 1, 0, 0, 0, 1, 0, 1, 0, 0},
    {0, 1, 1, 0, 2, 2, 0, 0, 0, 0},
    {0, 0, 2, 0, 0, 1, 0, 1, 0, 0},
    {0, 1, 1, 0, 0, 1, 0, 0, 0, 0},
    {0, 0, 0, 0, 2, 1, 0, 0, 2, 0},
    {0, 1, 0, 0, 0, 2, 0, 1, 0, 0},
    {0, 2, 1, 0, 0, 1, 0, 0, 0, 0},
    {0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
    {0, 1, 2, 0, 0, 1, 0, 1, 0, 0}};
    int t, s, r, h;
    int BORDERS_FROM_TOP_AND_LEFT = 10;
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************

    public HexaGrid_DEBUG() {
        this.setPreferredSize(new Dimension(460, 550));
        initComponents();
        MyMouseListener ml = new MyMouseListener();
        addMouseListener(ml);
        addMouseMotionListener(ml);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    public void initComponents() {
        setHeight(50);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);

        //draw grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tab[j][i] == 0) {
                    fillHex(j, i, Color.CYAN, g2);
                    drawHex(j, i, Color.BLUE, g2);
                }
                if (tab[j][i] == 1) {
                    fillHex(j, i, Color.DARK_GRAY, g2);
                    drawHex(j, i, Color.BLUE, g2);
                }
                if (tab[j][i] == 2) {
                    fillHex(j, i, Color.GREEN, g2);
                    drawHex(j, i, Color.BLUE, g2);
                }
                if (tab[j][i] == 3) {
                    fillHex(j, i, Color.RED, g2);
                    drawHex(j, i, Color.BLUE, g2);
                }
                if (selected[j][i] == true) {
                    fillHex(j, i, Color.BLUE, g2);
                    drawHex(j, i, Color.BLUE, g2);
                }
            }
        }
    }

    /**
     * Determines the heagon coordinates
     *
     * @param x0
     * @param y0
     * @return a Polygon with all the coordinates of the hexagon
     */
    public Polygon hexagon(int x0, int y0) {
        int y = y0 + BORDERS_FROM_TOP_AND_LEFT;
        int x = x0 + BORDERS_FROM_TOP_AND_LEFT;
        if (s == 0 || h == 0) {
            System.out.println("ERROR: size of hex has not been set");
            return new Polygon();
        }

        int[] cx, cy;
        cx = new int[]{x + t, x + s + t, x + s + t + t, x + s + t, x + t, x};	//this is for the whole hexagon to be below and to the right of this point
        cy = new int[]{y, y, y + r, y + r + r, y + r + r, y + r};
        return new Polygon(cx, cy, 6);
    }

    /**
     * To draw the hexagons lines
     *
     * @param i
     * @param j
     * @param c
     * @param g2
     */
    public void drawHex(int i, int j, Color c, Graphics2D g2) {
        int x = i * (s + t);
        int y = j * h + (i % 2) * h / 2;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(c);
        g2.drawPolygon(hexagon(x, y));
    }

    /**
     * To fill the haxagons
     *
     * @param i
     * @param j
     * @param c
     * @param g2
     */
    public void fillHex(int i, int j, Color c, Graphics2D g2) {
        int x = i * (s + t);
        int y = j * h + (i % 2) * h / 2;

        g2.setColor(c);
        g2.fillPolygon(hexagon(x, y));
    }

    /**
     * Pixel to hexa
     *
     * @param mx
     * @param my
     * @return
     */
    public Point pxtoHex(int mx, int my) {
        Point p = new Point(-1, -1);

        //correction for BORDERS and XYVertex
        mx -= BORDERS_FROM_TOP_AND_LEFT;
        my -= BORDERS_FROM_TOP_AND_LEFT;

        int x = (int) (mx / (s + t)); //this gives a quick value for x. It works only on odd cols and doesn't handle the triangle sections. It assumes that the hexagon is a rectangle with width s+t (=1.5*s).
        int y = (int) ((my - (x % 2) * r) / h); //this gives the row easily. It needs to be offset by h/2 (=r)if it is in an even column

        /**
         * ****FIX for clicking in the triangle spaces (on the left side
         * only)******
         */
        //dx,dy are the number of pixels from the hex boundary. (ie. relative to the hex clicked in)
        int dx = mx - x * (s + t);
        int dy = my - y * h;

        if (my - (x % 2) * r < 0) {
            return p; // prevent clicking in the open halfhexes at the top of the screen
        }
		//System.out.println("dx=" + dx + " dy=" + dy + "  > " + dx*r/t + " <");

        //even columns
        if (x % 2 == 0) {
            if (dy > r) {	//bottom half of hexes
                if (dx * r / t < dy - r) {
                    x--;
                }
            }
            if (dy < r) {	//top half of hexes
                if ((t - dx) * r / t > dy) {
                    x--;
                    y--;
                }
            }
        } else {  // odd columns
            if (dy > h) {	//bottom half of hexes
                if (dx * r / t < dy - h) {
                    x--;
                    y++;
                }
            }
            if (dy < h) {	//top half of hexes
                //System.out.println("" + (t- dx)*r/t +  " " + (dy - r));
                if ((t - dx) * r / t > dy - r) {
                    x--;
                }
            }
        }
        p.x = x;
        p.y = y;
        return p;
    }

    private boolean hasChanged(Point p) {
        if (p.x == tempX && p.y == tempY) {
            return false;
        } else {
            return true;
        }
    }

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    /**
     * Set all the sizes
     *
     * @param side
     */
    public void setSide(int side) {
        s = side;
        t = (int) (s / 2);			//t = s sin(30) = (int) CalculateH(s);
        r = (int) (s * 0.8660254037844);	//r = s cos(30) = (int) CalculateR(s); 
        h = 2 * r;
    }

    /**
     * Set all the sizes
     *
     * @param height
     */
    public void setHeight(int height) {
        h = height;			// h = basic dimension: height (distance between two adj centresr aka size)
        r = h / 2;			// r = radius of inscribed circle
        s = (int) (h / 1.73205);	// s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
        t = (int) (r / 1.73205);	// t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
    }

    class MyMouseListener implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            //pixel to hexa
            Point p = new Point(pxtoHex(e.getX(), e.getY()));
            //System.out.println(p.x + " " + p.y);
            if (p.x < 10 && p.y < 10 && p.x >= 0 && p.y >= 0) {
                if (p.x < 10 && p.y < 10) {
                    tab[p.x][p.y] = 3;
                    repaint();
                }
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            //pixel to hexa
            Point p = new Point(pxtoHex(e.getX(), e.getY()));

            if (p.x < 10 && p.y < 10 && p.x >= 0 && p.y >= 0) {
                if (hasChanged(p)) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            selected[i][j] = false;
                        }
                    }
                }
                tempX = p.x;
                tempY = p.y;
                //System.out.println("temp = " + temp + "x=" + p.x + " y=" + p.y);
                selected[p.x][p.y] = true;
                repaint();
            } else {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        selected[i][j] = false;
                    }
                }
                repaint();
            }

        }

    }
}

