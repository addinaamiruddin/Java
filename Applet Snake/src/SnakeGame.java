import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends Applet implements Runnable,KeyListener{
    Graphics gfx;
    Image img;
    Thread thread;
    Snake snake;
    Boolean gameOver;
    public void init(){
        this.resize(400,400);
        gameOver=false;
        img=createImage(400,400);
        gfx=img.getGraphics();
        this.addKeyListener(this);
        snake=new Snake();
        thread=new Thread(this);//for runnable object
        thread.start();
    }
    public void paint(Graphics g){
        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, 400, 400);
       
        if(!gameOver){
           snake.draw(gfx);
        }
        else {
           gfx.setColor(Color.RED);
           gfx.drawString("Game Over", 180, 150);
        }
        g.drawImage(img, 0, 0, null);
    }
    public void update(Graphics g){
        paint(g);
    }
    public void repaint(Graphics g){
        paint(g);
    }

    public void run() {
        for(;;){//infinte for loop
            if(!gameOver)
            {
                //infinte for loop
                snake.move();
                this.checkGameOver();
            }

          this.repaint();
          try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
        }
        
    }
    public void checkGameOver() {
        if(snake.getX()<0 || snake.getX()>396)
            gameOver=true;
        if(snake.getY()<0 || snake.getY()>396)
            gameOver=true;
        if(snake.snakeCollision())
            gameOver=true;
    }
    public void reload(){
        this.destroy();
        this.init();
        thread.stop();
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_DOWN){
            snake.setIsMoving(true);
        }
        if(gameOver){
            if(e.getKeyCode() == KeyEvent.VK_R){
                reload();
            }
        }
        //snake movement
        if(e.getKeyCode()==KeyEvent.VK_UP){
            if(snake.getYDir()!=1){
                snake.setYDir(-1);
                snake.setXDir(0);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            if(snake.getYDir()!=-1){
                snake.setYDir(1);
                snake.setXDir(0);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(snake.getXDir()!=1){
                snake.setXDir(-1);
                snake.setYDir(0);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(snake.getXDir()!=-1){
                snake.setXDir(1);
                snake.setYDir(0);
            }
        }
        
        
    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}

