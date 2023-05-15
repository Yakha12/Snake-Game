import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

//Setting up the game world
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
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame(){
        newApple();
        running = true;
        //this because we are using the actionlistener interface
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){

        //visualise the game grid FOR DEV
        for (int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
            g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
            g.drawLine(0,i * UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
        }
        //DRAW APPLE
        g.setColor(Color.GREEN);
        //draw circle
        g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE);

        //DRAW SNAKE
        for (int i = 0; i<bodyParts;i++){
            //i = 0 is where the head is located
            if(i==0){
                g.setColor(Color.MAGENTA);
                g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
            }
            else {
                g.setColor(Color.magenta);
                g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
            }
        }
    }
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }
    public  void move() {
        //For loop to iterate through the Snake's bodyparts
        for (int i = bodyParts;i>0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        //move Snake
        switch (direction){
            case 'U':
                y[0]= y[0]-UNIT_SIZE;
                break;

            case 'D':
                y[0] = y[0]+UNIT_SIZE;
                break;
            case 'L':
                x[0]= x[0]-UNIT_SIZE;
                break;

            case 'R':
                x[0] = x[0]+UNIT_SIZE;
        }

    }
    public void  checkApple(){

    }
    public void checkCollisions(){

    }
    public void gameOver(Graphics g){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Check if game is running
        if (running)
        {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){}
    }
}
