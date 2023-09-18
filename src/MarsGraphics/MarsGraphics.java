package MarsRover.MarsGraphics;

import MarsRover.Journey.Journey;
import MarsRover.Plateau.Plateau;
import MarsRover.Utilities.Utilities;

import javax.swing.*;
import java.awt.*;

public
class MarsGraphics extends JFrame {

    static final int scaler = 20;
    static final int xOffset = scaler * 3;
    static final int yOffset = scaler * 29;

    private static
    void OutputText(Graphics g, String text, Color C, int xIn, int yIn, int size) {

        int x = xOffset + xIn * scaler -  6;
        int y = yOffset - yIn * scaler +  6;

        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("Arial", Font.PLAIN, size);
        g2d.setFont(font);

        g2d.setColor(C);
        g2d.drawString(text, x, y);
    }
    public static
    void ShowText (Graphics g, String text, Color C,int xIn, int yIn, int size){

        OutputText(g, text, C, xIn, yIn, size);
    }

//    public static
//    void ClearText (Graphics g, String text, Color C,int xIn, int yIn){
//
//        OutputText(g, text, Color.WHITE, xIn, yIn);
//    }

    public static
    void drawBox (Graphics g,int x, int y){
        Graphics2D g2d = (Graphics2D) g;

        int x0, x1, y0, y1;

        x0 = x - 1;
        y0 = y - 1;
        x1 = x + 1;
        y1 = y + 1;

        g2d.drawLine(x0, y, x, y1);
        g2d.drawLine(x, y1, x1, y);
        g2d.drawLine(x1, y, x, y0);
        g2d.drawLine(x, y0, x0, y);
    }

    public static
    void drawLargeBox(Graphics g, int xIn, int yIn, Color color){

        int x = xOffset + xIn * scaler -  6;
        int y = yOffset - yIn * scaler -  6;

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(color);
        g2d.drawRect(x, y, 12, 12);
        g2d.fillRect(x, y, 12, 12);
    }

    public static
    void drawGrid (Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("Serif", Font.PLAIN, 15);
        g2d.setFont(font);

        g2d.setColor(Color.BLACK);

        for (int i = 3 * scaler; i <= 29 * scaler; i = i + scaler)
            for (int j = 3 * scaler; j <= 29 * scaler; j = j + scaler)
                drawBox(g2d, i, j);

        g2d.setColor(Color.BLUE);

        for (int i = 5; i < 28; i = i + 5) {
            String num = Integer.toString(i);
            g2d.drawString(num, xOffset + i * scaler - 10, yOffset + scaler);
        }

        for (int i = 5; i < 28; i = i + 5) {
            String num = Integer.toString(i);
            g2d.drawString(num, 35, yOffset - i * scaler);
        }

        g2d.drawString("(0,0)", 25, yOffset + scaler);

    } // end

    private static void drawBoundary(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int gap = 4;
        int xMax = Plateau.getxMax() - 1;
        int yMax = Plateau.getyMax() - 1;

        g2d.setColor(Color.GREEN);

        g2d.drawLine(3 * scaler - gap,          29 * scaler + gap,
                     (xMax + 3) * scaler + gap, 29 * scaler + gap ); // Bottom

        g2d.drawLine(3 * scaler - gap,          (29 - yMax) * scaler - gap,
                     (xMax + 3) * scaler + gap, (29 - yMax) * scaler - gap ); // Top

        g2d.drawLine(3 * scaler - gap,  29 * scaler - gap,
                     3 * scaler - gap, (29 - yMax) * scaler - gap ); // Left

        g2d.drawLine((xMax + 3) * scaler + gap, 29 * scaler + gap,
                     (xMax + 3) * scaler + gap,(29 - yMax) * scaler - gap ); // Right
    }



    public static
    void drawDirection(Graphics g, int xIn, int yIn, int length,
                       int xDir, int yDir, Color color) {
        // xDir & yDir will contain 0 or -1

        int startX = xOffset + xIn * scaler +  6 * xDir;
        int startY = yOffset - yIn * scaler +  6 * yDir;

        int endX = startX + length * xDir;
        int endY = startY + length * yDir;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.drawLine(startX, startY, endX, endY);
    }

    private static Graphics setUpGraphics() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Malcolm's Mars Rover. " +
                                  "    Keep it simple - always draw a large grid");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1000, 700);
        frame.getContentPane().setBackground(Color.WHITE);


        frame.setVisible(true);


        Graphics g = frame.getGraphics();
        System.out.printf("%n   OK - please view the graphics window");
        System.out.printf("%n        [show begins in 5 seconds]%n");

        return g;
    }

    public static Graphics prepareWindow() {

        Graphics g = setUpGraphics();

        for(int i=0; i<5; i++) {
            Font font = new Font("Arial", Font.PLAIN, 48);

            ShowText(g, Integer.toString(5 - i), Color.BLUE, 10, 10, 48);
            Utilities.goToSleep(1000);
            ShowText(g, Integer.toString(5 - i), Color.WHITE, 10, 10, 48);
        }

        drawGrid(g);
        drawBoundary(g);
        return g;
    }

    public static void DisplayJourney(Graphics g) {
        String journey = Journey.getTheJourney();

        int y =650;

        Graphics2D g2d = (Graphics2D) g;
        Font font = new Font("Arial", Font.BOLD, 18);
        g2d.setFont(font);

        g2d.setColor(Color.BLACK);
        g2d.drawString("Journey: ", 50, y);

        for(int i=0; i<journey.length(); i++) {
            char thisMoveChar = journey.charAt(i);
            String thisMove = String.valueOf(thisMoveChar);

            int currentIndex = Journey.getCurrentIndex();

            int x = 3 * xOffset + i * 16;

            if ( i == currentIndex )
                g2d.setColor(Color.BLUE);
            else
                g2d.setColor(Color.BLACK);

            g2d.drawString(thisMove, x, y);

        }
    }
}
