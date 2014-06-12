/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hofbauer.robocode.simulateur.Action;

import com.hofbauer.robocode.simulateur.RobotStateMachine;
import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.scxml.ErrorReporter;
import org.apache.commons.scxml.EventDispatcher;
import org.apache.commons.scxml.SCInstance;
import org.apache.commons.scxml.SCXMLExpressionException;
import org.apache.commons.scxml.model.Action;
import org.apache.commons.scxml.model.ModelException;

/**
 *
 * @author hofbauer
 */
public class SetAheadAction extends Action {

    private String x;
    private String y;
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public SetAheadAction() {
        super();
    }

    @Override
    public void execute(EventDispatcher evtDispatcher, ErrorReporter errRep,
            SCInstance scInstance, Log appLog, Collection derivedEvents)
            throws ModelException, SCXMLExpressionException {
        RobotStateMachine.r.ahead(200);
        
        System.out.println("Mon Action est executée");

        System.out.println(x);
        System.out.println(y);

    }

}
    
