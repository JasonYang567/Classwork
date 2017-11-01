package caveExplorer;

import java.util.Scanner;

public class CaveExplorer {
	
	public static CaveRoom[][] caves; //every room in the cave
	public static Scanner in; //user input
	public static CaveRoom currentRoom; //changes based on how the user navigated
	public static Inventory inventory; //where all objects found in caves are kept
	private static boolean playing = true;
	private static NPC[] npcs;
	
	public static void main(String[] args)
	{
		in = new Scanner(System.in);
		CaveRoom.setUpCaves();//create caves and starting room
		inventory = new Inventory();
		startExploring();
	}
	
	public static void print(String s) {
		System.out.println(s);//LATER: consider replacing with the more sophisticated "printMulti"
	}
	
	public static void startExploring() {
		while(playing) {
			moveNPCs();
			print(inventory.getDescription());
			print(currentRoom.getDescription());
			print(currentRoom.getDirections());
			print("What would you like to do?");
			currentRoom.interpretInput(in.nextLine());
		}
	}
	
	private static void moveNPCs() {
		for(NPC n: npcs) {
			n.autoMove();
		}
		Inventory.updateMap();
	}
}
