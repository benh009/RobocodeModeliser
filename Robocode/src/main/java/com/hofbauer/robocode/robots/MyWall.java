package com.hofbauer.robocode.robots;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import robocode.*;
//import java.awt.Color;

/**
 * ReculeAvance - a robot by (your name here)
 */
public class MyWall extends AdvancedRobot
{
	
	public MyWall()
	{


	}
	/**
	 * run: ReculeAvance's default behavior
	 */
	public void run() {
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:
		//setColors(Color.red,Color.blue,Color.green);


				
		
		while(true) {
			
		
			
			//permet d'aller dans le coin droit
			direction(Nord);
			directionGun(Est);
			ahead(getBattleFieldHeight()-getY()-getHeight()/2);
			roboProche=false;
			rotationInPoint();
			
			direction(Est);
			directionGun(Sud);
			ahead(getBattleFieldWidth()-getX()-getWidth()/2);
			roboProche=false;
			rotationInPoint();
			
			direction(Sud);
			directionGun(Ouest);
			ahead(getY()-getHeight()/2);
			roboProche=false;
			rotationInPoint();

			
			direction(Ouest);
			directionGun(Nord);
			ahead(getX()-getHeight()/2);
			roboProche=false;
			rotationInPoint();
			

			
			


		} 
	}

    




	public void onHitWall(HitWallEvent e) {
		turnLeft(180);
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {

		smartFire(e.getDistance());
		if(!roboProche)
		{
		System.out.println("touché");
		stop();
		scan();
		resume();
		}
		if(e.getDistance() < 200)
		{
			System.out.println("Proche");
			roboProche=true;
		}
		

		
		
		
	}
	public void smartFire(double robotDistance) {
		if (robotDistance > 200 || getEnergy() < 15) {
			fire(1);
		} else if (robotDistance > 50) {
			fire(2);
		} else {
			fire(3);
		}
	}
	
	public void onHitByBullet(HitByBulletEvent event)
	{
		roboProche=true;
	
	}
   public void onHitRobot(HitRobotEvent event) {
       if (event.getBearing() > -90 && event.getBearing() <= 90) {
           back(100);
       } else {
           ahead(100);
       }
   }
   
   /**
    *          1
    *       3      4
    *          2
    * 
    *           0
    *      270     90
    *          180
    * */
   public int Nord =0;
   public int Sud = 180 ;
   public int Ouest = 270;
   public int Est = 90;
   public void direction(int directionVoulue )
   {
       double directionActuelle = getHeading();
       if (directionVoulue>directionActuelle)
       {
           if((360-directionVoulue+directionActuelle)<(directionVoulue-directionActuelle))
           {
               turnLeft(360-directionVoulue+directionActuelle);
           }
           else
           {
               turnRight(directionVoulue-directionActuelle);
           }
       }
       else if(directionVoulue<=directionActuelle )
       {
           if((360-directionActuelle+directionVoulue)<(directionActuelle-directionVoulue))
           {
               turnRight(360-directionActuelle+directionVoulue);
           }
           else
           {
               turnLeft(directionActuelle-directionVoulue);
           }
       }
   }
   public void directionGun(int directionVoulue )
   {
       double directionActuelle = getGunHeading();
       if (directionVoulue>directionActuelle)
       {
           if((360-directionVoulue+directionActuelle)<(directionVoulue-directionActuelle))
           {
               turnGunLeft(360-directionVoulue+directionActuelle);
           }
           else
           {
               turnGunRight(directionVoulue-directionActuelle);
           }
       }
       else if(directionVoulue<=directionActuelle )
       {
           if((360-directionActuelle+directionVoulue)<(directionActuelle-directionVoulue))
           {
               turnGunRight(360-directionActuelle+directionVoulue);
           }
           else
           {
               turnGunLeft(directionActuelle-directionVoulue);
           }
       }
   }
   public boolean roboProche=  false;
   public void rotationInPoint()
   {
		boolean DansLeCoin=true;
		while(!roboProche && DansLeCoin)
		{
		if(getX()-getWidth()/2==0 && getY()-getHeight()/2==0)
		{
			directionGun(Nord);
			directionGun(Est);
		}
		else if (getX()+getWidth()/2==getBattleFieldWidth() && getY()+getHeight()/2==getBattleFieldHeight())
		{
			directionGun(Ouest);
			directionGun(Sud);
		}
		else if (getX()-getWidth()/2==0 && getY()+getHeight()/2==getBattleFieldHeight())
		{
			directionGun(Est);
			directionGun(Sud);
		}
		else if (getX()+getWidth()/2==getBattleFieldWidth() && getY()-getHeight()/2==0)
		{
			directionGun(Nord);
			directionGun(Ouest);
		}
		else 
		{
			DansLeCoin= false;
		}
		}
	}



	
}
