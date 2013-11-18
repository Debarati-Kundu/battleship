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

import java.util.List;
import java.util.Random;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.devrel.samples.ttt.Board;
import com.google.devrel.samples.ttt.ComputerBoard;
import com.google.devrel.samples.ttt.OfyService;


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
			  
//			  if ((sunkcount - str.sinkCount) != 0) {str.sinkDiff = true;}
//			  System.out.println(sunkcount + " " + str.sinkCount + " " + str.sinkDiff);
			  str.sinkCount = sunkcount;
			  if (sunkcount == NUM_SHIPS) {
				  
			//	  System.out.println(str.sinkCount);
				  str.allSunk = true;
				  str.sinkCount = sunkcount;
				  inputboard.allSunk = true;
				  inputboard.sinkCount = sunkcount;
			  }
			  inputboard.sinkDiff = str.sinkDiff;  
			  ofy().save().entity(str).now();
		  } 
	  
	  inputboard.setState(String.valueOf(incomingStateChars));
//      System.out.println(String.valueOf(incomingStateChars));
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
}
