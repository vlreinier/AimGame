import com.sun.javaws.util.JfxHelper;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Canvas;

public class Window extends Canvas{

    public Window(int width, int height, String title, AimGame game){

        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();

    }

}
