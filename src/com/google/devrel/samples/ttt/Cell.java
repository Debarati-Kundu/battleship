package com.google.devrel.samples.ttt;

import java.io.Serializable;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Serialize;

@Entity
public class Cell implements Serializable{
	
	static {
		 ObjectifyService.register(Cell.class);
	} 
	private static final long serialVersionUID = 6910028598881278592L;
	@Id
	public Long id;
	@Serialize
	public boolean hasShip;
	@Serialize
	public String shipName;
	public Cell() {
		this.hasShip = false;
		this.shipName = "XXX";
	}
}
