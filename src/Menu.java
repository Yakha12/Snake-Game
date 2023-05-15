import java.awt.*;

import javax.swing.*;

public class Menu extends JPanel{
    //Menu Buttons
    public Rectangle playButton = new Rectangle(GamePanel.SCREEN_WIDTH/2 -100,150,200,75 );
    public  Rectangle exitButton = new Rectangle(GamePanel.SCREEN_WIDTH/2-100,250,200,75);
    public  Rectangle highScoreButton = new Rectangle(GamePanel.SCREEN_WIDTH/2-100,350,200,75 );
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
}
