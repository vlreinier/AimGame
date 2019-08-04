import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends GameObject{
    private int xSize = 20;
    private int ySize = 20;
    private int sizeChange = ThreadLocalRandom.current().nextInt(1, 20);
    private Color color = randomColor();
    private Random random;

    public Player(int x, int y, ID id){
        super(x, y, id);
        velX = ThreadLocalRandom.current().nextInt(1, 5);
        velY = ThreadLocalRandom.current().nextInt(1, 5);
    }

    @Override
    public void tick(){


        x += velX;
        y += velY;

        if(x <= 0 || x >= AimGame.width - 40) velX *= -1;
        if(y <= 0 || y >= AimGame.height - 50) velY *= -1;

        xSize += sizeChange;
        ySize += sizeChange;

        if (xSize > 50 || ySize > 50) sizeChange = -1;
        if (xSize == 20 || ySize == 20) sizeChange = ThreadLocalRandom.current().nextInt(1, 20);

    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, xSize, ySize);
    }

    public Color randomColor(){
        random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }


}

