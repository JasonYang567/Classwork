package caveExplorer;

public class Enemy extends NPC{
	
	private int currentRow;
	private int currentCol;
	private NPCRoom currentRoom;
	private CaveRoom playerRoom;
	private boolean alive;
	
	public Enemy() {
		this.currentCol = -1;
		this.currentRow = -1;
		currentRoom = null;
		alive = true;
	}
	
	public void runEnemy() {
		int[] coordinates = calculateMove();
		int row = coordinates[0];
		int col = coordinates[1];
		move(row, col);
		if(currentRoom[row][col] == playerRoom)
	}
	
	public void move(int row, int col) {
		setPosition(row, col);
	}
	
	private int[] calculateMove() {
		int[][] possibleMoves = {{-1,0},{0,1},{1,0},{0,-1}};
		int index = (int)(Math.random() * possibleMoves.length);
		int[] newPosition = new int[2];
		newPosition[0] = currentRow + possibleMoves[index][0];
		newPosition[1] = currentCol + possibleMoves[index][1];
		while(currentRoom.getDoor(index) == null ||
				!(CaveExplorer.caves[newPosition[0]][newPosition[1]] 
						instanceof NPCRoom)) {
			index = (int)(Math.random() * possibleMoves.length);
			newPosition[0] = currentRow + possibleMoves[index][0];
			newPosition[1] = currentCol + possibleMoves[index][1];
		}
		return newPosition;
	}
}
