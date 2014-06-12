package com.hofbauer.robocode.simulateur;

import robocode.AdvancedRobot;

public class RobotTools {

    public static void goTo(double nx, double ny, AdvancedRobot r) {
        double x = r.getX();
        double y = r.getY();
        double deltaX = nx - x;
        double deltaY = ny - y;

        double t = deltaY / deltaX;
        double arct = Math.abs(Math.atan(t));
        if (Math.abs(deltaX) < 2 && Math.abs(deltaY) < 2) {
            r.doNothing();
        } else if (Math.abs(deltaX) < 1) {
            if (ny > y) {
                direction(0, r);
                r.setAhead(deltaY);
            } else {
                direction(180, r);
                r.setAhead(-deltaY);
            }

        } else if (Math.abs(deltaY) < 1) {
            if (nx > x) {
                direction(90, r);
                r.setAhead(deltaX);
            } else {
                direction(270, r);
                r.setAhead(-deltaX);
            }

        } else {
            if (deltaY > 0 && deltaX > 0) {
                direction((90 - Math.toDegrees(arct)), r);

            } else if (deltaY < 0 && deltaX > 0) {
                direction((90 + Math.toDegrees(arct)), r);

            } else if (deltaY < 0 && deltaX < 0) {
                direction((270 - Math.toDegrees(arct)), r);

            } else if (deltaY > 0 && deltaX < 0) {
                direction((270 + Math.toDegrees(arct)), r);

            }
            r.setAhead(Math.sqrt(deltaY * deltaY + deltaX * deltaX));
        }

    }

    public static void direction(double directionVoulue, AdvancedRobot r) {
        double directionActuelle = r.getHeading();

        if (Math.abs(directionVoulue - directionActuelle) < 1) {

        } else if (directionVoulue > directionActuelle) {
            if ((360 - directionVoulue + directionActuelle) < (directionVoulue - directionActuelle)) {
                r.setTurnLeft(360 - directionVoulue + directionActuelle);
            } else {
                r.setTurnRight(directionVoulue - directionActuelle);
            }
        } else if (directionVoulue <= directionActuelle) {
            if ((360 - directionActuelle + directionVoulue) < (directionActuelle - directionVoulue)) {
                r.setTurnRight(360 - directionActuelle + directionVoulue);
            } else {
                r.setTurnLeft(directionActuelle - directionVoulue);
            }
        }
    }

}
