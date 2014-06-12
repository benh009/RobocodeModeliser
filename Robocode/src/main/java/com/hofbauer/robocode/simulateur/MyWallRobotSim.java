package com.hofbauer.robocode.simulateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import robocode.AdvancedRobot;
import robocode.Condition;
import robocode.CustomEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.TurnCompleteCondition;
//import test.Prout;

public class MyWallRobotSim extends AdvancedRobot {

	static RobotStateMachine myWall = null;
	String Cstate = "";

	public void run() {
		

		if (myWall == null) {
			myWall = new RobotStateMachine(this);
			System.out.print("init mywall");
		}
		
		myWall.getEngine().getRootContext()
				.set("BattleFieldWidth", this.getBattleFieldWidth());
		myWall.getEngine().getRootContext()
				.set("BattleFieldHeight", this.getBattleFieldHeight());
		myWall.getEngine().getRootContext().set("Width", this.getWidth());
		myWall.getEngine().getRootContext().set("Height", this.getHeight());

		

		while (true) {

			myWall.getEngine().getRootContext().set("X", this.getX());
			myWall.getEngine().getRootContext().set("Y", this.getY());
			myWall.getEngine().getRootContext().set("Energy", this.getEnergy());
			myWall.fireEvent("e");

			Cstate = myWall.getCurrentState();
			this.setInterruptible(true);
			// System.out.println(Cstate);
			if (Cstate.equals("avance")) {
				setAhead(100);
			} else if (Cstate.equals("recule")) {
				setBack(1000);
				
			} else if (Cstate.equals("attaque")) {
				fire(3);
				myWall.fireEvent("finAttaque");
				
				
			} else if (Cstate.equals("tourneADroite")) {
				
				setTurnRight(90);
				
			} else if (Cstate.equals("rienFaire")) {
				scan();
			} else if (Cstate.equals("goCornerNE")) {
				double m = Math.max(this.getWidth(), this.getHeight());
				RobotTools.goTo(this.getBattleFieldWidth() - m,
						this.getBattleFieldHeight() - m,this);
				// direction(180+45);

				
			} else if (Cstate.equals("goCornerNO")) {

				double m = Math.max(this.getWidth(), this.getHeight());
				RobotTools.goTo(m, this.getBattleFieldHeight() - m,this);
				// direction(90+45);
		
				

			}
			
			if(!myWall.getCurrentState().equals(Cstate))
			{
				setAhead(0);
				setTurnRight(0);
				

			}
			
			waitFor(new TurnCompleteCondition(this));

			

		}
	}

	public void onHitWall(HitWallEvent e) {
		myWall.fireEvent("onHitWall");

	}

	public void onScannedRobot(ScannedRobotEvent e) {
		stop(true);
		this.myWall.fireEvent("onScannedRobot");

		
	}

	public void onHitRobot(HitRobotEvent event) {
		myWall.fireEvent("onHitRobot");

	}

	public void onHitByBullet(HitByBulletEvent event) {
		myWall.fireEvent("onHitByBullet");
	}

	public void onCustomEvent(CustomEvent event) {
		// System.out.println("Event LowEnergy");
		if (event.getCondition().getName().equals("lowEnergy")) {
			myWall.fireEvent("lowEnergy");
		}
	}

	public void onPaint(Graphics2D g) {
		g.setColor(Color.red);
		g.setFont(new Font("Arial Bold", Font.ITALIC, 20));
		g.drawString(Cstate, (int) getX(), (int) getY());
	}


}
