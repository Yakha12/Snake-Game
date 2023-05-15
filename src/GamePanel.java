import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;

    //How big should the object in game be
    static final int UNIT_SIZE = 25;
    static final int GAME_UNIT = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
   //The Higher the number for delay the slower the game
    static final int DELAY = 75;

    //Fields for Game Objects manipulation
    //Array to Hold body co-ordinates for the Snake We use GAME UNIT because the snake will never be larger than the game

    final int  x[] = new int[GAME_UNIT];
    final int  y[] = new int[GAME_UNIT];
    int bodyParts = 6;
    int appleEaten;
    int appleX,appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel(){

    }
    public void startGame(){

    }

    public void paintComponent(Graphics g){

    }
    public void draw(Graphics g){

    }
    public  void move() {

    }
    public void  checkApple(){

    }
    public void checkCollisions(){

    }
    public void gameOver(Graphics g){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){}
    }
}
