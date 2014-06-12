/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hofbauer.robocode.simulateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Set;
import org.apache.commons.scxml.model.State;
import robocode.AdvancedRobot;
import robocode.BattleEndedEvent;
import robocode.CustomEvent;
import robocode.DeathEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

/**
 *
 * @author hofbauer
 */
public class RobotSimWithAction extends AdvancedRobot {

    static RobotStateMachine robotModel = null;

    Set currentLeafstates = null;

    public void run() {

        if (robotModel == null) {
            robotModel = new RobotStateMachine(this);

        }

        robotModel.getEngine().getRootContext()
                .set("BattleFieldWidth", this.getBattleFieldWidth());
        robotModel.getEngine().getRootContext()
                .set("BattleFieldHeight", this.getBattleFieldHeight());
        robotModel.getEngine().getRootContext().set("Width", this.getWidth());
        robotModel.getEngine().getRootContext().set("Height", this.getHeight());

        while (true) {

            robotModel.getEngine().getRootContext().set("X", this.getX());
            robotModel.getEngine().getRootContext().set("Y", this.getY());
            robotModel.getEngine().getRootContext().set("Heading", this.getHeading());
            System.out.println("heading : " + this.getHeading());
            robotModel.getEngine().getRootContext().set("Energy", this.getEnergy());
            //System.out.println("Energy: "+this.getEnergy() );
            robotModel.fireEvent("e");
            setAhead(100);

            execute();

        }
    }

    public void onHitWall(HitWallEvent e) {
        robotModel.fireEvent("onHitWall");

    }

    public void onScannedRobot(ScannedRobotEvent e) {
        this.robotModel.fireEvent("onScannedRobot");

    }

    public void onHitRobot(HitRobotEvent event) {
        robotModel.fireEvent("onHitRobot");

    }

    public void onHitByBullet(HitByBulletEvent event) {
        robotModel.fireEvent("onHitByBullet");

    }

    public void onCustomEvent(CustomEvent event) {
        if (event.getCondition().getName().equals("lowEnergy")) {
            robotModel.fireEvent("lowEnergy");

        }
    }

    public void onBattleEnded(BattleEndedEvent e) {

        robotModel.resetMachine();

    }

    public void onDeath(DeathEvent e) {
        robotModel.resetMachine();
    }

    public void onPaint(Graphics2D g) {
        g.setColor(Color.red);
        g.setFont(new Font("Arial Bold", Font.ITALIC, 20));
        g.drawString("mot à écrire", (int) getX(), (int) getY());
    }

}
