/* Copyright 2013 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devrel.samples.ttt.spi;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.devrel.samples.ttt.BattleshipGame;
import com.google.devrel.samples.ttt.Board;
import com.google.devrel.samples.ttt.ComputerBoard;
import com.google.devrel.samples.ttt.OfyService;
import com.google.devrel.samples.ttt.myToken;


/**
 * Defines v1 of a Board resource as part of the tictactoe API, which provides
 * clients the ability to query for a computer's next move given an input
 * board.
 */
@Api(
    name = "tictactoe",
    version = "v1",
    clientIds = {Ids.WEB_CLIENT_ID, Ids.ANDROID_CLIENT_ID, Ids.IOS_CLIENT_ID},
    audiences = {Ids.ANDROID_AUDIENCE}
)
public class BoardV1 {
  public static final int NUM_SHIPS = 5;
  public static final int MAX_LENGTH = 5;
  
  public static final int NUM_ROWS = 10;
  public static final int NUM_COLS = 10;
  
  public static final char X = 'X';
  public static final char R = 'H';  // H for hit
  public static final char B = 'M';  // M for miss
  public static final char S = 'S';	 // Sunk
  public static final char O = 'O';
  public static final char DASH = '-';
 
@ApiMethod(name = "board.getUserShips")

public void getUserShips(ComputerBoard inputuserboard) {
	String presentGameid = inputuserboard.getGameID();
	List<BattleshipGame> s1 = OfyService.ofy().load().type(BattleshipGame.class).list();
	for(BattleshipGame str : s1 ) {		
		if (str.getKey().equals(presentGameid)) {
			str.BoardUserA = inputuserboard;
			str.BoardUserA.setBoardFromState();
			System.out.println(str.BoardUserB.getState());
		}
	}
	return;
} 
  
  
  @ApiMethod(name = "board.create")
  public void createBoard() {
	  ComputerBoard CG = new ComputerBoard();
	  CG.placeShips();
	  ofy().save().entity(CG).now();
	  
	  // Debugging
	  /*
	  for (int i = 0; i < 5; i++) {
  		for (int j = 0; j < 5; j++) {
  			System.out.println(CG.shipLoc[i][j]);
  		}
  		System.out.println("******");
	  } */
	  
	  // Debugging objectify
	  /*
	  System.out.println("Done with save");
	  List<ComputerBoard> s1 = OfyService.ofy().load().type(ComputerBoard.class).list();
	  System.out.println("Done with load");	  
	  for ( ComputerBoard str : s1 ) {
		  System.out.println(str.mynum);
	  }
	  System.out.println("Done with everything"); */
	  return;
  }
  
  @ApiMethod(name = "board.gethit", httpMethod = "POST")
	  public ComputerBoard getHit(ComputerBoard inputboard) {
	  String incomingState = inputboard.getState();  
	  char[] incomingStateChars = incomingState.toCharArray();
		  List<ComputerBoard> s1 = OfyService.ofy().load().type(ComputerBoard.class).list();
		  for ( ComputerBoard str : s1 ) {
	//		  System.out.println(str.id);
			  for (int i = 0; i < NUM_ROWS; i++) {
				  for (int j = 0; j < NUM_COLS; j++) {
				//	  str.hasShip[i][j] = true; // Only for testing
					  if(incomingStateChars[i*NUM_COLS + j] != DASH) {
						  if(str.hasShip[i][j])
							  incomingStateChars[i*NUM_COLS + j] = R;
						  else incomingStateChars[i*NUM_COLS + j] = B;
					  }
					  else incomingStateChars[i*NUM_COLS + j] = DASH;
				  }
			  }
			  Integer sunkcount = 0;
			  for (int i = 0; i < NUM_SHIPS; i++) {
				  int hitcount = 0;
				  for (int j = 0; j < str.SHIP_LENGTHS[i]; j++) {
					  if(incomingStateChars[str.shipLoc[i][j]] == R) {
						  hitcount++;
					  }
				  }
				  if (hitcount == str.SHIP_LENGTHS[i]) str.sunk[i] = true;
				  if(str.sunk[i]) {
					  sunkcount++;
					  inputboard.sunk[i] = true;
		//			  System.out.println("Sunk " + i);
					  for (int j = 0; j < str.SHIP_LENGTHS[i]; j++) {
						  incomingStateChars[str.shipLoc[i][j]] = S;
					  }
				  }
			  }
			  if (!sunkcount.equals(str.sinkCount)) str.sinkDiff = true;
			  	else str.sinkDiff = false;
			  
			  str.sinkCount = sunkcount;
			  if (sunkcount == NUM_SHIPS) {
				  str.allSunk = true;
				  str.sinkCount = sunkcount;
				  inputboard.allSunk = true;
				  inputboard.sinkCount = sunkcount;
			  }
			  inputboard.sinkDiff = str.sinkDiff;  
			  ofy().save().entity(str).now();
		  } 
	  
	  inputboard.setState(String.valueOf(incomingStateChars));
	  return inputboard;
  }
  
  /**
   * Provides the ability to insert a new Score entity.
   * 
   * @param board object representing the state of the board
   * @return the board including the computer's move
   */
  @ApiMethod(name = "board.getmove", httpMethod = "POST")
  public Board getmove(Board board) {
    char[][] parsed = parseBoard(board.getState());
    int free = countFree(parsed);
    parsed = addMove(parsed, free);
    
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < parsed.length; i++) {
      builder.append(String.valueOf(parsed[i]));
    }
    Board updated = new Board();
    updated.setState(builder.toString());
    
//    ComputerBoard CG = new ComputerBoard();
//    CG.placeShips();
    
    return updated;
  }
  
  private char[][] parseBoard(String boardString) {
    char[][] board = new char[3][3];
    char[] chars = boardString.toCharArray();
    if (chars.length == 9) {
      for (int i = 0; i < chars.length; i++) {
        board[i/3][i%3] = chars[i];
      }
    }
    
    return board;
  }
  
  private int countFree(char[][] board) {
    int count = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != X && board[i][j] != O) {
          count++;
        }
      }
    }
    return count;
  }
  
  private char[][] addMove(char[][] board, int free) {
    int index = new Random().nextInt(free) + 1;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == DASH) {
          if (free == index) {
            board[i][j] = O;
            return board;
          } else {
            free--;
          }
        }
      }
    }
    // Only occurs when empty > the number of actual empty squares.
    return board;
  }
 
  /*
   * Testing out channel API
   * Mainly for testing purposes
   */
  @ApiMethod(name = "board.getchannel")
  public myToken getChannel(User user) {
	  
	  ChannelService channelService = ChannelServiceFactory.getChannelService();
	  String token = channelService.createChannel(user.getEmail());
	  myToken mt = new myToken();
	  mt.setState(token);
	  return mt;
  }
  
  // Returns if hit or miss
  // Piggyback server's move???
  @ApiMethod(name = "board.getusermove", httpMethod = "POST")
  public myToken getUserMove(myToken mt) {
	  
	  Integer nbd_r[] = {0, -1, 0, 1};
	  Integer nbd_c[] = {-1, 0, 1, 0};
	  
	  myToken mt1 = new myToken();
	  mt1.setGameID("XXX");
	  mt1.setMysunk("XXX");
	  String result = "MISS";
	  Integer x = Integer.valueOf(mt.getState());
	  Integer index = 0;
	  List<BattleshipGame> s1 = OfyService.ofy().load().type(BattleshipGame.class).list(); 
	  for ( BattleshipGame str : s1 ) {
		  String temp1 = str.getKey();
		  String temp2 = mt.getGameID();
		  if(temp1.equals(temp2)) {
			  Integer row = x/10;
			  Integer col = x%10;
			  if(str.BoardUserB.hasShip[row][col] == true) {
				  result = "HIT";
				  str.BoardUserA.opponentHasShip[row][col] = 1;
				  Integer whichShip = str.BoardUserB.shipNumber[row][col];
				  str.BoardUserB.numHits[whichShip]++;
				  if(str.BoardUserB.numHits[whichShip].equals(str.BoardUserB.SHIP_LENGTHS[whichShip])) {
					  System.out.println(str.BoardUserB.SHIP_NAMES[whichShip] + " sunk");
					  str.BoardUserB.sunk[whichShip] = true;
					  str.BoardUserA.opponentSunk[whichShip] = true;
					  mt1.setGameID(str.BoardUserB.SHIP_NAMES[whichShip]);
				  }
			  } else {
				  str.BoardUserA.opponentHasShip[row][col] = 0;
			  }
			  mt1.setState(result);
			  // Add code for server's move iff there is no winning yet, otherwise stop
			  //Maybe create a separate endpoint. :-? 
			  // At the moment, just random firing by the server, Strategy v1
			  
			  boolean tried = true;
			  /*
			  while(tried) {
				  row = (int)(Math.random()*(NUM_ROWS));
				  col = (int)(Math.random()*(NUM_COLS));
				  if(str.BoardUserB.opponentHasShip[row][col] == -1)
					  tried = false;
			  }*/
			  
			  ///////// Better Algorithm ////////////
			  // Hunting Mode
	//		  System.out.println(str.BoardUserB.stackSize());
			  if(str.BoardUserB.isEmpty()) {
	//			  System.out.println("Hunt mode");
				  tried = true;
				  while(tried) {
					  row = (int)(Math.random()*(NUM_ROWS));
					  col = (int)(Math.random()*(NUM_COLS));
					  if(str.BoardUserB.opponentHasShip[row][col] == -1)
						  tried = false;
				  }
				  System.out.println("Hunt mode "+row + " "+col);
			  } else {
				  
				  // Target mode
				  Integer move = str.BoardUserB.pop();
				  row = move/10;
				  col = move%10;
				  System.out.println("Target mode "+row + " "+col);
			  }
			  
			  
          	  index = 12*row + col;
          	  mt1.setCelltarget(Integer.toString(index));
          	  
          	  if(str.BoardUserA.hasShip[row][col] == true) {
          		  
          		  for (int j = 0; j < 4; j++) {
          			  Integer r1 = row + nbd_r[j];
          			  Integer c1 = col + nbd_c[j];
          			  if((r1 >=0) && (r1<10) && (c1 >=0) && (c1<10)) {
          				if(str.BoardUserB.opponentHasShip[r1][c1] == -1) { 
          					Integer index1 = 10*r1+c1;
          					System.out.println(row + " " + col + " " + r1 + " " + c1 + " " + index1);
          					str.BoardUserB.push(index1);         					
          				}
          			  }
          		  }
          		  System.out.println("****");
          		  
          		  
          		  mt1.setCellstate("HIT");
          		  str.BoardUserB.opponentHasShip[row][col] = 1;
          		  Integer whichShip = str.BoardUserA.shipNumber[row][col];
          		  str.BoardUserA.numHits[whichShip]++;
          		  
          		  if(str.BoardUserA.numHits[whichShip].equals(str.BoardUserA.SHIP_LENGTHS[whichShip])) {
					  System.out.println(str.BoardUserA.SHIP_NAMES[whichShip] + " lost");
					  str.BoardUserA.sunk[whichShip] = true;
					  str.BoardUserB.opponentSunk[whichShip] = true;
					  mt1.setMysunk(str.BoardUserA.SHIP_NAMES[whichShip]);
				  }
          		  
          	  } else {
          		  str.BoardUserB.opponentHasShip[row][col] = 0;
        		  mt1.setCellstate("MISS");
          	  }
		  }
	  }	  
	  return mt1;
  }
  
  
  @ApiMethod(name = "board.gamecreate")
  public myToken newGame() {
	  BattleshipGame BG = new BattleshipGame();
	  String k1 = BG.getKey();
	  BG.BoardUserB.setGameID(k1); 
	  BG.BoardUserB.placeShips();   // We are creating arrangements by server as soon as the user logs in
	  ofy().save().entity(BG).now();		   
	  myToken mt = new myToken();
	  mt.setState(k1);
//	  System.out.println(k1);

	  // Debugging
	  /*
	  System.out.println("After objectify save");	  
	  List<BattleshipGame> s1 = OfyService.ofy().load().type(BattleshipGame.class).list();
	  System.out.println("Done with load");	  
	  for ( BattleshipGame str : s1 ) {
		  System.out.println(str.id);
	  }
	  System.out.println("Done with everything"); */
	  
	  return mt;
  }
/*

  */
}
