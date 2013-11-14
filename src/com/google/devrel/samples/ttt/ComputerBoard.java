package com.google.devrel.samples.ttt;

public class ComputerBoard {
	
	private static final int NUM_SHIPS = 5;
	private static final int NUM_ROWS = 10;
	private static final int NUM_COLS = 10;
	
	// lengths of the various ships in the game
    private static final int AIRCRAFT_CARRIER_LENGTH = 5;
    private static final int BATTLESHIP_LENGTH = 4;
    private static final int DESTROYER_LENGTH = 3;
    private static final int SUBMARINE_LENGTH = 3;
    private static final int PATROL_BOAT_LENGTH = 2;

    // array of all ship lengths
    private static final int[] SHIP_LENGTHS =
    {
        AIRCRAFT_CARRIER_LENGTH,
        BATTLESHIP_LENGTH,
        DESTROYER_LENGTH,
        SUBMARINE_LENGTH,
        PATROL_BOAT_LENGTH
    };
    private static final String[] SHIP_NAMES =
        {
            "AIRCRAFT_CARRIER",
            "BATTLESHIP",
            "DESTROYER",
            "SUBMARINE",
            "PATROL_BOAT",
            "XXXX" // Means no ship, empty cell
        };
    
	private Cell[][] ComputerGrid;
    
//    public Cell ComputerGrid[NUM_ROWS][NUM_COLS];
    
    public ComputerBoard() {
    	for (int i = 0; i < NUM_ROWS; i++) {
        	for (int j = 0; j < NUM_COLS; j++) {
        		this.ComputerGrid[i][j] = new Cell();
        	}
        }
    	System.out.println("hit1");
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
        		row = (int)(Math.random()*(NUM_ROWS)); //get a random x coordinate
                col = (int)(Math.random()*(NUM_COLS)); //get a random y coordinate
                dir = (int)(Math.random()*(2)); //get a random direction, 0 = horizontal, 1 = vertical
                System.out.println("####");
                System.out.println(ComputerGrid[0][0].hasShip);
                if ((ComputerGrid[row][col].hasShip == false) && ((dir == 0) && ((col + SHIP_LENGTHS[k]) < NUM_COLS)) 
                		&& ((dir == 1) && ((row + SHIP_LENGTHS[k]) < NUM_ROWS))) {
                		for (int j = 0; j < SHIP_LENGTHS[k]; j++) {
                			if ((dir==0) && (ComputerGrid[row][col + j].hasShip)) {
                				overlap = true;
                			}
                			if ((dir==0) && (ComputerGrid[row + j][col].hasShip)) {
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
                	ComputerGrid[row][col + j].hasShip = true;
                	ComputerGrid[row][col + j].shipName = SHIP_NAMES[k];
                }
                else {
                	ComputerGrid[row + j][col].hasShip = true;
                	ComputerGrid[row + j][col].shipName = SHIP_NAMES[k];
                }
        	}
        	
        } // End of loop over all the ships
    	
        for (int i = 0; i < NUM_ROWS; i++) {
        	for (int j = 0; j < NUM_COLS; j++) {
        		System.out.println(ComputerGrid[i][j].shipName);
        	}
        }
    	return;
    }

	public static int[] getShipLengths() {
		return SHIP_LENGTHS;
	}

	public static String[] getShipNames() {
		return SHIP_NAMES;
	}

	/*
	public static Cell[][] getComputerGrid() {
		return ComputerGrid;
	}

	public static void setComputerGrid(Cell[][] computerGrid) {
		ComputerGrid = computerGrid;
	}*/
    
    
}
