package com.google.devrel.samples.ttt;

import java.io.Serializable;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
//Generic enclosing class for all sorts of datatypes

@Entity
public class myToken implements Serializable {
	private static final long serialVersionUID = 4472354978385910942L;
	static {
		 ObjectifyService.register(myToken.class);
	}
	@Id
	public Long id = 123456789L;
	private String state;
	private String gameID;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getGameID() {
		return gameID;
	}
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}
}
