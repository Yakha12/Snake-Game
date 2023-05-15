import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

//Setting up the game world
public class GamePanel extends JPanel implements ActionListener {
   //Determine when the game is still on the menu or starts playing
    private enum STATE {
        MENU,
        GAME
    };
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

    //Menu Fields
    private  STATE state = STATE.MENU;
    private Menu menu;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        //Create Menu Object
        menu = new Menu();
        startGame();
    }
    public void startGame(){
        //Only run if game is not in the menu
        if(state == STATE.GAME) {
            newApple();
            running = true;
            //this because we are using the actionlistener interface
            timer = new Timer(DELAY, this);
            timer.start();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(state == STATE.GAME) {
            if (running) {
                //visualise the game grid FOR DEV
                for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                    g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                    g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
                }
                //DRAW APPLE
                g.setColor(Color.GREEN);
                //draw circle
                g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

                //DRAW SNAKE
                for (int i = 0; i < bodyParts; i++) {
                    //i = 0 is where the head is located
                    if (i == 0) {
                        g.setColor(Color.MAGENTA);
                        g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    } else {
                        g.setColor(Color.magenta);
                        g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                }
                //Score Board
                g.setColor(Color.red);
                g.setFont(new Font("Ink Free", Font.BOLD, 40));
                //For lining up text on the center
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score: " + appleEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + appleEaten)) / 2, g.getFont().getSize());

            } else {
                gameOver(g);
            }
        }
        else if (state == STATE.MENU ){
            menu.draw(g);
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
        if ((x[0]==appleX) && (y[0]==appleY)){
            bodyParts++;
            appleEaten++;
            newApple();
        }
    }
    public void checkCollisions(){
        //Check if head collides with body
        for (int i = bodyParts;i<0;i--){
            //if this is true it mean the head hit the body
            if ((x[0]==x[i])&&(y[0]==y[i])){
                running=false;
            }
        }
        //Check if head touches left boarder
        if(x[0] < 0){
             running = false;
        }
        //Check if head touches right boarder
        if(x[0] > SCREEN_WIDTH){
            running = false;
        }
        //Check if head touches top boarder
        if(y[0] < 0){
            running = false;
        }
        //Check if head touches bottom boarder
        if(y[0] > SCREEN_HEIGHT){
            running = false;
        }
        //if game is not running then stop the timer
        if (!running){
            timer.stop();
        }
    }
    public void gameOver(Graphics g){
        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        //For lining up text on the center
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        String gameOverStr = "Game Over";
        g.drawString(gameOverStr,(SCREEN_WIDTH - metrics1.stringWidth(gameOverStr))/2,SCREEN_HEIGHT/02);

        //Score Board
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,40));
        //For lining up text on the center
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: "+appleEaten,(SCREEN_WIDTH - metrics2.stringWidth("Score: "+appleEaten))/2,g.getFont().getSize());

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
        //Control the Snake
        @Override
        public void keyPressed(KeyEvent e){
            if (state == STATE.GAME){
                switch (e.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                        //Limit user to 90 degree turns
                        if (direction != 'R'){
                            direction = 'L';
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
                        //Limit user to 90 degree turns
                        if (direction != 'L'){
                            direction = 'R';
                        }
                        break;

                    case KeyEvent.VK_UP:
                        //Limit user to 90 degree turns
                        if (direction != 'D'){
                            direction = 'U';
                        }
                        break;

                    case KeyEvent.VK_DOWN:
                        //Limit user to 90 degree turns
                        if (direction != 'U'){
                            direction = 'D';
                        }
                        break;

                }
            }
        }
    }
}
