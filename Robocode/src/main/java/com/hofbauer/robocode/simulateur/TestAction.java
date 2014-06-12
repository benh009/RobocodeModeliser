package com.hofbauer.robocode.simulateur;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.scxml.ErrorReporter;
import org.apache.commons.scxml.EventDispatcher;
import org.apache.commons.scxml.SCInstance;
import org.apache.commons.scxml.SCXMLExpressionException;
import org.apache.commons.scxml.TriggerEvent;
import org.apache.commons.scxml.model.Action;
import org.apache.commons.scxml.model.ModelException;

public class TestAction extends Action {
	
	private String x ;
	      /** Serial version UID. */
	  private static final long serialVersionUID = 1L;
	
	      public String getX() {
	          return x;
	    }

      public void setX(String x) {
	         this.x = x;
    }
	public TestAction()
	{
		super();
	}

	@Override
	public void execute(EventDispatcher evtDispatcher, ErrorReporter errRep,
			SCInstance scInstance, Log appLog, Collection derivedEvents)
			throws ModelException, SCXMLExpressionException {
		System.out.println("Mon Action est executée");
		
		
		System.out.println(x);

	}



}