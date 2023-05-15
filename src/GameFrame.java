import javax.swing.*;

//this basically the game window
public class GameFrame extends JFrame
{
    GameFrame()
    {
        //Create an instance of Game panel to add to game frame
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();// will fit the Frame around all added components
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
