package com.hofbauer.robocode.simulateur;

import com.hofbauer.robocode.simulateur.Action.TestAction;
import java.awt.List;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.scxml.TriggerEvent;
import org.apache.commons.scxml.env.AbstractStateMachine;
import org.apache.commons.scxml.env.SimpleSCXMLListener;
import org.apache.commons.scxml.model.CustomAction;
import org.apache.commons.scxml.model.Data;
import org.apache.commons.scxml.model.State;

import robocode.AdvancedRobot;
import robocode.Robot;

public class RobotStateMachine extends MyAbstractStateMachine {

    public String getCurrentState() {
        Set states = getEngine().getCurrentStatus().getStates();
        return ((org.apache.commons.scxml.model.State) states.iterator().
                next()).getId();
    }

    public static ArrayList<CustomAction> getActionArrayList() {
        ArrayList<CustomAction> customActions = new ArrayList<CustomAction>();
        CustomAction ca
                = new CustomAction("http://my.custom-actions.domain/CUSTOM",
                        "testAction", TestAction.class);
        customActions.add(ca);
        return customActions;
    }

    public RobotStateMachine() {

        super(RobotStateMachine.class.getClass().getResource("/com/hofbauer/robocode/simulation/scxml/testSimple.scxml"), getActionArrayList());

        //RobotToGuiListener myListerner = new RobotToGuiListener(null, 9999);
        //this.getEngine().addListener(this.getEngine().getStateMachine(), myListerner);
    }
    public static AdvancedRobot r;
    RobotToGuiListener myListerner;

    public RobotStateMachine(AdvancedRobot r) {

        super(RobotStateMachine.class.getClass().getResource("/com/hofbauer/robocode/simulation/scxml/testSimple.scxml"), getActionArrayList());
		//super(RobotStateMachine.class.getClass().getResource("/projet/testModuleBasic.scxml"));

        //a garder car sinon n'accepte pas le robot
        this.r = r;

        //problème au niveau des threads a quitter 
        //myListerner = new RobotToGuiListener(null, 9999);
        //this.getEngine().addListener(this.getEngine().getStateMachine(), myListerner);

    }

    static RobotStateMachine testsimu;

    public static void main(String[] args) {

        testsimu = new RobotStateMachine();

        //simple Listener
        SimpleSCXMLListener sListerner = new SimpleSCXMLListener();
                //testsimu.getEngine().addListener(testsimu.getEngine().getStateMachine(), sListerner);

        //listener pour scxmlgui
        //RobotToGuiListener myListerner = new RobotToGuiListener(null, 9999);
        //testsimu.getEngine().addListener(testsimu.getEngine().getStateMachine(), myListerner);
        //testsimu.fireEvent("changeSens");
        System.out.println(testsimu.getCurrentState());
        testsimu.fireEvent("onHitWall");
        System.out.println(testsimu.getCurrentState());

    }

}
