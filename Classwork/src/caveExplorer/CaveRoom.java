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
			System.out.println("You can only enter 'w', 'a', 's', 'd'.");
			input = CaveExplorer.in.nextLine();
		}
		/*
		 * convert w,a,s,d to direction 0,3,2,1
		 * NO IF STATEMENTS
		 */
		int direction = "wdsa".indexOf(input);
		goToRoom(direction);
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setDefaultContents(String defaultContents) {
		this.defaultContents = defaultContents;
	}

	
	
}