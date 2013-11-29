package com.google.devrel.samples.ttt;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Serialize;

@Entity
public class BattleshipGame implements Serializable{

	private static final long serialVersionUID = -4563586825277566740L;
	static {
		 ObjectifyService.register(BattleshipGame.class);
	}
	@Id
	public Long id = 12345678L;
	
//	@PrimaryKey
	private String stringKey;   // Changed to string because SecretKey cannot be stored in datastore
	
    @Serialize
	public ComputerBoard BoardUserA;
    @Serialize
	public ComputerBoard BoardUserB;
	
	public boolean winner; // 0 means A, 1 means B
	
	public BattleshipGame() {
		BoardUserA = new ComputerBoard();
		BoardUserB = new ComputerBoard();
		winner = false;
		setKey();
	}

	public String getKey() {
		return stringKey;
	}

	public void setKey() {
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("AES");
			SecureRandom random = new SecureRandom(); // cryptograph. secure random 
			keyGen.init(random); 
			SecretKey key = keyGen.generateKey();
			stringKey = key.toString();			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
