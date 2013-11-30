package com.google.devrel.samples.ttt;

import java.io.Serializable;
import java.util.LinkedList;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Serialize;

@Entity
public class ComputerBoard implements Serializable{

	private static final long serialVersionUID = -8004574445464620527L;

	static {
		 ObjectifyService.register(ComputerBoard.class);
	} 
	@Id
	public Long id = 1234567L;
	private String gameID;

	private static final int NUM_SHIPS = 5;
	private static final int NUM_ROWS = 10;
	private static final int NUM_COLS = 10;
	private static final int GRIDSIZE = 100;
	
	// lengths of the various ships in the game
    private static final int AIRCRAFT_CARRIER_LENGTH = 5;
    private static final int BATTLESHIP_LENGTH = 4;
    private static final int DESTROYER_LENGTH = 3;
    private static final int SUBMARINE_LENGTH = 3;
    private static final int PATROL_BOAT_LENGTH = 2;

    // array of all ship lengths
    public final int[] SHIP_LENGTHS =
    {
        AIRCRAFT_CARRIER_LENGTH,
        BATTLESHIP_LENGTH,
        DESTROYER_LENGTH,
        SUBMARINE_LENGTH,
        PATROL_BOAT_LENGTH
    };
    public final String[] SHIP_NAMES =
        {
            "AIRCRAFT_CARRIER",
            "BATTLESHIP",
            "DESTROYER",
            "SUBMARINE",
            "PATROL_BOAT",
            "XXXX" // Means no ship, empty cell
        };

    private LinkedList<Integer> cellsToTry = new LinkedList<Integer>();
    public void push(Integer item) {cellsToTry.addFirst(item);}
    public Integer pop() {return cellsToTry.removeFirst();}
    public Integer peek() {return cellsToTry.getFirst();}
    public int stackSize() {return cellsToTry.size();}
    public boolean isEmpty() {return cellsToTry.isEmpty();}
    
    public int mynum = 2;
    private String state;
    
    @Serialize
    public char[] stateChars = new char[GRIDSIZE];
    
    public void setStateFromBoard() {
    	stateChars = state.toCharArray();
    	System.out.println(stateChars);
    	return;
    }
    
    @Serialize
	public boolean[][] hasShip = new boolean[NUM_ROWS][NUM_COLS];
    @Serialize
    public String[][] shipName = new String[NUM_ROWS][NUM_COLS];
    @Serialize
    public Integer[][] shipNumber = new Integer[NUM_ROWS][NUM_COLS];
    @Serialize
    public int[][] shipLoc = new int[NUM_SHIPS][AIRCRAFT_CARRIER_LENGTH];
    @Serialize
    public Integer[] numHits = new Integer[NUM_SHIPS];
    @Serialize
    public boolean[] sunk = new boolean[NUM_SHIPS];
    
    public boolean allSunk = false;
    public Integer sinkCount = 0;
    public boolean sinkDiff;
    
    // We also need to save information about the opponent
    @Serialize
	public Integer[][] opponentHasShip = new Integer[NUM_ROWS][NUM_COLS];
    @Serialize
    public boolean[] opponentSunk = new boolean[NUM_SHIPS];
    
//    @Serialize
//	public Cell[][] ComputerGrid = new Cell[NUM_ROWS][NUM_COLS];
    
    public ComputerBoard() {
    	for (int i = 0; i < NUM_ROWS; i++) {
        	for (int j = 0; j < NUM_COLS; j++) {
        		stateChars[10*i+j] = '-';
        		this.hasShip[i][j] = false;
        		this.shipName[i][j] = "XXX";
        		this.shipNumber[i][j] = -1;
   //     		this.ComputerGrid[i][j] = new Cell();
        	}
        }
    	
    	// Assume that you don't know anything about the opponent when you begin the game
    	for (int i = 0; i < NUM_ROWS; i++) {
        	for (int j = 0; j < NUM_COLS; j++) {
        		this.opponentHasShip[i][j] = -1;
        	}
    	}
    	for (int i = 0; i < NUM_SHIPS; i++) 
    		this.opponentSunk[i] = false;
    	
    	
    	// Stores the indices for each ship
    	for (int i = 0; i < NUM_SHIPS; i++) {
    		for (int j = 0; j < AIRCRAFT_CARRIER_LENGTH; j++) {
    				shipLoc[i][j] = -1;
    		}
    		numHits[i] = 0;
    		sunk[i] = false;
    	}
    	
    } 
    
    public void setBoardFromState() {
    	stateChars = state.toCharArray();
    	for (int i = 0; i < GRIDSIZE; i++) {
    		int row = i/10;
    		int col = i%10;
    		if (stateChars[i] !='-') {
    			hasShip[row][col] = true;
    			if(stateChars[i] == 'A') shipNumber[row][col] = 0;
    			if(stateChars[i] == 'B') shipNumber[row][col] = 1;
    			if(stateChars[i] == 'D') shipNumber[row][col] = 2;
    			if(stateChars[i] == 'S') shipNumber[row][col] = 3;
    			if(stateChars[i] == 'P') shipNumber[row][col] = 4;
    		}
    	}
//    	System.out.println(stateChars);
    	return;
    }
    
    
    public void placeShips() {
    	int dir = 0;
    	int row = 0;		// Vertical coord
    	int col = 0;		// Horizontal coord
    	
    	boolean flag;
        boolean overlap;
        
        for (int k = 0; k < NUM_SHIPS; k++) {
        	flag = true;
        	while(flag) {       		
        		overlap = false;
                dir = (int)(Math.random()*(2)); //get a random direction, 0 = horizontal, 1 = vertical
                if(dir == 0) {
                	row = (int)(Math.random()*(NUM_ROWS));
                	col = (int)(Math.random()*(NUM_COLS - SHIP_LENGTHS[k] + 1));
                } else {
                	row = (int)(Math.random()*(NUM_ROWS - SHIP_LENGTHS[k] + 1));
                	col = (int)(Math.random()*(NUM_COLS));
                }
                
         //       if (ComputerGrid[row][col].hasShip == false) {
                if (hasShip[row][col] == false) {
                		for (int j = 0; j < SHIP_LENGTHS[k]; j++) {
                	//		if ((dir==0) && (ComputerGrid[row][col + j].hasShip)) {
                	 		if ((dir==0) && (hasShip[row][col + j])) {
                				overlap = true;
                			}
                	//		if ((dir==1) && (ComputerGrid[row + j][col].hasShip)) {
                	 		if ((dir==1) && (hasShip[row + j][col])) {
                				overlap = true;
                			}
                		}
                		if (overlap==false) {
                			flag = false;
                		}                	
                }
        	} // End of while loop over individual ships
        	for (int j = 0; j < SHIP_LENGTHS[k]; j++) {
                if (dir==0) {
                	
                	stateChars[10*row+col+j] = SHIP_NAMES[k].charAt(0);
                	
       //         	ComputerGrid[row][col + j].hasShip = true;
        			hasShip[row][col + j] = true;
       //         	ComputerGrid[row][col + j].shipName = SHIP_NAMES[k];
        			shipNumber[row][col + j] = k;
        			shipName[row][col + j] = SHIP_NAMES[k];
        			shipLoc[k][j] = row*NUM_COLS + col;
                }
                else {
          //      	ComputerGrid[row + j][col].hasShip = true;
                	stateChars[10*(row+j)+col] = SHIP_NAMES[k].charAt(0);
                	hasShip[row + j][col] = true;
          //      	ComputerGrid[row + j][col].shipName = SHIP_NAMES[k];
                	shipNumber[row + j][col] = k;
                	shipName[row + j][col] = SHIP_NAMES[k];
                	shipLoc[k][j] = row*NUM_COLS + col;
                }
        	}
        	for (int j = 1; j < SHIP_LENGTHS[k]; j++) {
        		if (shipLoc[k][j] != -1) {
        			if(dir == 0)  { 
        				shipLoc[k][j] = shipLoc[k][j-1] + 1;
        			}
        			else {
        				shipLoc[k][j] = shipLoc[k][j-1] + 10*dir;
        			}
        		}
        	}
        } // End of loop over all the ships
   
        //Debugging
        /*
        for (int i = 0; i < NUM_ROWS; i++) {
        	for (int j = 0; j < NUM_COLS; j++) {
     //   		System.out.println(ComputerGrid[i][j].hasShip);
     //   		System.out.println(ComputerGrid[i][j].shipName);
        		System.out.println(hasShip[i][j]);
        		System.out.println(shipName[i][j]);
        	}
        } */ 
        state = String.valueOf(stateChars);
    	return;
    } 
    
    public void printShipLoc() {
    	for(int i = 0; i < 10; i++) {
    		for(int j = 0; j < 10; j++) {
    			System.out.println(shipName[i][j]);
    		}
    	}
    }
    /*
	public static int[] getShipLengths() {
		return SHIP_LENGTHS;
	} */

    /*
	public static String[] getShipNames() {
		return SHIP_NAMES;
	} */

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

	/*
	public boolean[][] getHasShip() {
		return hasShip;
	}

	public static void setComputerGrid(Cell[][] computerGrid) {
		ComputerGrid = computerGrid;
	}*/
    
    
}
