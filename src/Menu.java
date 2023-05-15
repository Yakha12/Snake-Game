import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Menu extends JPanel{
    //Menu Buttons
    public Rectangle playButton = new Rectangle(GamePanel.SCREEN_WIDTH/2 -100,150,200,75 );
    public  Rectangle highScoreButton = new Rectangle(GamePanel.SCREEN_WIDTH/2-100,250,200,75);
    public  Rectangle exitButton = new Rectangle(GamePanel.SCREEN_WIDTH/2-100,350,200,75 );

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        /*Draw Title of the Game*/
            //Set Font
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        g.setColor(Color.white);
             //For lining up text on the center
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("SNAKE GAME",(GamePanel.SCREEN_WIDTH - metrics.stringWidth("SNAKE GAME"))/2,100);
        /*Draw Buttons for the game*/
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        //Draw Play Button
        g.drawString("Play",playButton.x+50,playButton.y+50 );
        g2d.draw(playButton);
        //Draw HighScore Button
        g.drawString("High Score",highScoreButton.x+35,highScoreButton.y +50);
        g2d.draw(highScoreButton);
        //Draw exit Button
        g.drawString("Quit Game",exitButton.x+35,exitButton.y+50 );
        g2d.draw(exitButton);
    }
    public MyMouseListener listener(){
        return new MyMouseListener();
    }
    class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            //Mouse Co-ordinates
            int mx = e.getX();
            int my = e.getY();
            if((mx >= playButton.x) && (mx < playButton.x+200)){
                if((my >= playButton.y) && (my < playButton.y+75)){
                    GamePanel.state = GamePanel.STATE.GAME;
                    System.out.println(GamePanel.state);
                    System.out.println("Play");
                }
            }
            if((mx >= highScoreButton.x) && (mx < highScoreButton.x+200)){
                if((my>= highScoreButton.y) && (my < highScoreButton.y+75)){
                    System.out.println("High Score");
                }
            }
            if((mx >= exitButton.x) && (mx < exitButton.x+200)){
                if((my>= exitButton.y) && (my< exitButton.y+75)){
                    System.out.println("Exit");
                }
            }

            // Handle mouse click event
            System.out.println("Mouse clicked on " + e.getComponent().getClass().getName());
        }
    }
}
