package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static Main.GamePanel.*;

public class Bike{
     int bikePosX;
     int bikePosY;
     boolean alive=true;
     final int[] xTrail = new int[SCREEN_SIZE];
     final int[] yTrail = new int[SCREEN_SIZE];
     Color bikeColor;
     int trailIndex=0;
     char direction;
     int playerNum;

    public Bike(int bikePosX,int bikePosY, Color bikeColor,int playerNum){
        this.bikePosX = bikePosX;
        this.bikePosY = bikePosY;
        this.bikeColor= bikeColor;
        this.playerNum=playerNum;
        if(playerNum == 1){
            this.direction='s';
        }
        else{
            this.direction='u';
        }

    }

    public void move(Bike playerBike){
        playerBike.xTrail[playerBike.trailIndex] = playerBike.bikePosX;
        playerBike.yTrail[playerBike.trailIndex] = playerBike.bikePosY;
        playerBike.trailIndex++;

        switch (playerBike.playerNum)
        {
            case 1:
                switch (playerBike.direction){
                    case 'w':
                        playerBike.bikePosY -= 5;
                        break;
                    case 's':

                        playerBike.bikePosY += 5;
                        break;
                    case 'a':

                        playerBike.bikePosX -= 5;
                        break;
                    case 'd':

                        playerBike.bikePosX += 5;
                        break;
                }
                break;
            case 2:
                switch (playerBike.direction){
                    case 'u':
                        playerBike.bikePosY -= 5;
                        break;
                    case 'o':
                        playerBike.bikePosY += 5;
                        break;
                    case 'l':
                        playerBike.bikePosX -= 5;
                        break;
                    case 'r':
                        playerBike.bikePosX += 5;
                        break;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + playerBike.playerNum);
        }

    }


    public boolean checkCollisions(Bike bike, boolean running){
        //checks if bike collides with his own trail
        int i=0,flag=0;
        while (i < bike.trailIndex && flag==0) {
            if ((bike.bikePosY == bike.yTrail[i]) && (bike.bikePosX== bike.xTrail[i])) {

                flag = 1; // get out of the loop
                bike.alive = false; // game over
                running=false;
            }
            i++; // iterate from both arrays
        }
        //check if bike touches left and right border
        if((bike.bikePosX < 0 || bike.bikePosX > SCREEN_WIDTH) || (bike.bikePosY < 0 || bike.bikePosY > SCREEN_HEIGHT)){
            bike.alive=false;
            running=false;
        }
        return running;

    }



}
