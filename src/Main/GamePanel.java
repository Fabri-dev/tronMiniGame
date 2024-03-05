package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT= 800;
    static final int SCREEN_SIZE= SCREEN_WIDTH * SCREEN_HEIGHT;
    static final int BACKGROUND_SPACE_SIZE= 30;
    static final int BIKE_SIZE=15;
    static final int DELAY=90;

    boolean running=false;
    public Timer timer;

    private final Bike bike1;
    private final Bike bike2;
    public GamePanel(){
        bike1 = new Bike(100,100,Color.orange,1);
        bike2 = new Bike(700,700,Color.blue,2);
        this.addKeyListener(new MyKeyAdapter());
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);

        startGame();
    }

    public void startGame(){
        running=true;
        timer= new Timer(DELAY, this);

        timer.start();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        if(running){
             for(int i=0 ; i < SCREEN_HEIGHT; i++){
                 g.drawLine(i*BACKGROUND_SPACE_SIZE,0, i*BACKGROUND_SPACE_SIZE, SCREEN_HEIGHT);
                 g.drawLine(0,i*BACKGROUND_SPACE_SIZE, SCREEN_WIDTH,i*BACKGROUND_SPACE_SIZE);
             }

             //bike 1

            for (int i = 0; i < bike1.trailIndex; i++) {
                int trailSize=BIKE_SIZE-10;
                int trailX = bike1.xTrail[i]+5;
                int trailY = bike1.yTrail[i]+5;
                g.setColor(bike1.bikeColor);
                g.fillRect(trailX, trailY, trailSize, trailSize);
            }
            g.setColor(bike1.bikeColor);
            g.fillRect(bike1.bikePosX,bike1.bikePosY, BIKE_SIZE, BIKE_SIZE);

            //bike 2

            for (int i = 0; i < bike2.trailIndex; i++) {
                int trailSize=BIKE_SIZE-10;
                int trailX = bike2.xTrail[i]+5;
                int trailY = bike2.yTrail[i]+5;
                g.setColor(bike2.bikeColor);
                g.fillRect(trailX, trailY, trailSize, trailSize);
            }
            g.setColor(bike2.bikeColor);
            g.fillRect(bike2.bikePosX,bike2.bikePosY, BIKE_SIZE, BIKE_SIZE);

        }
        else {
            g.setColor(Color.white);
            g.setFont(new Font("Sans serif",Font.BOLD,75));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Game over",(SCREEN_WIDTH-metrics.stringWidth("Game over"))/4,SCREEN_HEIGHT/2);
            if(bike1.alive){
                g.setColor(Color.orange);
                g.drawString("Orange wins!", (SCREEN_WIDTH-metrics.stringWidth("Orange wins!"))/4, 600);
            }
            else{
                g.setColor(Color.blue);
                g.drawString("Blue wins!", (SCREEN_WIDTH-metrics.stringWidth("Blue wins!"))/4, 600);
            }

        }
    }




    public void gameOver(Graphics g){

    }


    public void actionPerformed(ActionEvent e) {
        if (running){
            bike1.move(bike1);
            bike2.move(bike2);


             running= bike1.checkCollisions(bike1, running);
            running=bike2.checkCollisions(bike2, running);
            checkCrash(bike1,bike2);
        }else {
            timer.stop();
        }

        repaint();
    }

    public void checkCrash(Bike bike1, Bike bike2){


        //check for collisions from bike1 to bike2
        for(int i=0; i < bike2.trailIndex; i++){
            if ((bike1.bikePosY == bike2.yTrail[i]) && (bike1.bikePosX== bike2.xTrail[i])) {


                running=false;
                bike1.alive = false; // game over
                break;
            }
        }


        //check for collisions from bike2 to bike1
        for (int i=0; i < bike1.trailIndex; i++)
        {
            if ((bike2.bikePosY == bike1.yTrail[i]) && (bike2.bikePosX== bike1.xTrail[i])) {
                running=false;
                bike2.alive = false; // game over
                break;
            }
        }


    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_W:
                    if(bike1.direction != 's'){
                        bike1.direction='w';
                    }
                    break;
                case KeyEvent.VK_S:
                    if(bike1.direction != 'w'){
                        bike1.direction='s';
                    }
                    break;
                case KeyEvent.VK_D:
                    if(bike1.direction != 'a'){
                        bike1.direction='d';
                    }
                    break;
                case KeyEvent.VK_A:
                    if(bike1.direction != 'd'){
                        bike1.direction='a';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(bike2.direction != 'o'){
                        bike2.direction='u';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(bike2.direction != 'u'){
                        bike2.direction='o';
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(bike2.direction != 'r'){
                        bike2.direction='l';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(bike2.direction != 'l'){
                        bike2.direction='r';
                    }
                    break;
            }
        }
    }


}
