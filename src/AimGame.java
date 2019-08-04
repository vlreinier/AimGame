import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class AimGame extends Canvas implements Runnable{
    private Thread thread;
    private boolean running = false;
    public static final int width = 1000;
    public static final int height = 800;
    private Handler handler;
    Random random;

    public AimGame(){
        handler = new Handler();
        new Window(width, height, "first game", this);

        random = new Random();
        for (int i = 0; i < 40; i++){
            handler.addObject(new Player(random.nextInt(width - 50), random.nextInt(height - 50), ID.Player));
        }
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D)bs.getDrawGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.white);
        g.fillRect(0,0, width, height);

        handler.render(g);
        g.dispose();
        bs.show();
    }

}
