package caveExplorer;

public class CaveRoom {

	private String description; //tells what the room looks like
	private String directions; //tells what you can do
	private String contents; //a symbol representing what's in the room
	private String defaultContents;
	//the rooms are organized by directions, 'null' signifies no room/ door in that direction
	private CaveRoom[] borderingRooms;
	private Door[] doors;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public CaveRoom(String description) {
		this.description = description;
		setDefaultContents("");
		contents = defaultContents;
		//difference between defaultContents and content is  "contents" becomes an 'X' when you are
		//inside this room, when you leave it goes back to defaultContents
		
		//note: by default, arrays will populate with 'null' meaning there are no connections
		borderingRooms = new CaveRoom[4];
		doors = new Door[4];
		setDirections();
	}

	public void enter() {
		contents = "X";
	}
	
	public void leave() {
		contents = defaultContents;
	}
	


	/** This is how we join rooms together
	 * 
	 * @param direction
	 * @param anotherRoom
	 * @param door
	 */
	public void setConnection(int direction, CaveRoom anotherRoom, Door door) {
		addRoom(direction, anotherRoom, door);
		anotherRoom.addRoom(oppositeDirection(direction), this, door);
	}
	
	private void addRoom(int dir, CaveRoom caveRoom, Door door) {
		borderingRooms[dir] = caveRoom;
		doors[dir] = door;
		setDirections();//updates the directions
	}

	public void interpretInput(String input) {
		while(!isValid(input)) {
			printValidMoves();
			input = CaveExplorer.in.nextLine();
		}
		/*
		 * convert w,a,s,d to direction 0,3,2,1
		 * NO IF STATEMENTS
		 */
		int direction = validMoves().indexOf(input);
		if(direction < 4) {
			goToRoom(direction);
		}
		else {
			performAction(direction);
		}
	}
	
	/**
	 * override to create response to keys other than wdsa
	 * @param direction
	 */
	private void performAction(int direction) {
		CaveExplorer.print("That key does nothing");
	}

	/**
	 * override to change description and possible moves
	 */
	private void printValidMoves() {
		System.out.println("You can only enter 'w', 'a', 's', 'd'.");
	}

	/**
	 * override to add more moves
	 * @return
	 */
	public String validMoves() {
		return "wdsa";
	}
	
	/**
	 * returns true if w,a,s, or d is the input (NO IF STATEMENTS)
	 * @param input
	 * @return
	 */
	private boolean isValid(String input) {
		return "wdsa".indexOf(input) > -1 && input.length() == 1;
	}

	/**
	 * THIS IS WHERE YOU EDIT YOUR CAVES
	 */
	public static void setUpCaves() {
		//1. Determine size of caves
		CaveExplorer.caves = new CaveRoom[5][5];
		CaveRoom[][] c = CaveExplorer.caves;//create a shortcut for accessing CaveExplorer.caves
		//2. Populate with default caves
		for(int row = 0; row > c.length; row++) {
			for(int col = 0; col < c[row].length; col++) {
				c[row][col] = new CaveRoom("This cave has coordinates " + row + ", " + col);
			}
		}
		//3. Replace some default rooms with custom rooms. (SAVE FOR LATER)
		
		//4. set starting room
		CaveExplorer.currentRoom = c[0][1];
		CaveExplorer.currentRoom.enter();
		
		//5. Set up doors
		c[0][1].setConnection(SOUTH, c[1][1], new Door());
		c[1][1].setConnection(EAST, c[1][2], new Door());
	}
	
	public void goToRoom(int direction) {
		//make sure there is a room to go to:
		if(borderingRooms[direction] != null && doors[direction] != null &&
				doors[direction].isOpen()) {
			CaveExplorer.currentRoom.leave();
			CaveExplorer.currentRoom = borderingRooms[direction];
			CaveExplorer.currentRoom.enter();
			CaveExplorer.inventory.updateMap();
		}
		else {
			//print red text
			System.err.println("You can't do that!");
		}
	}
	
	/**
	 * returns the OPPOSITE direction
	 *   oD(0) returns 2
	 *   oD(1) returns 3
	 * @param dir
	 * @return
	 */
	public static int oppositeDirection(int dir) {
		int[] opposite = {2,3,0,1};
		return opposite[dir];
	}
	
	//for every door in door[] appends a string to "directions", describing the access
	//for example: "There is a door to the north"
	
	//if there are no doors at all, directions should say:
	// "There are no doors, you are trapped in here;
	public void setDirections() {
		directions = "";
		boolean doorFound = false;
		for(int i = 0; i < doors.length; i++)
		{
			if(doors[i] != null)
			{
				doorFound = true;
				directions += "\n   There is a " + doors[i].getDescription() + " to " +
				toDirection(i) + ". " + doors[i].getDetails();
			}
		}
		if(!doorFound)
		{
			directions += "There is no way out. You are trapped in here.";
		}
	}

	//converts an int to a direction
	// to Direction(0) -> "the North"
	public static String toDirection(int dir)
	{
		String[] directions = {"the North", "the East", "the South", "the West"};
		return directions[dir];
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}
	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setDefaultContents(String defaultContents) {
		this.defaultContents = defaultContents;
	}

	public Door getDoor(int direction) {
		// TODO Auto-generated method stub
		return doors[direction];
	}

	
	
}
