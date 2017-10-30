package caveExplorer;

public class TwoDArrayPractice {
	
	public static void main(String[] args) {
		String[][] pic = new String[6][20];
		for(String[] row: pic) {
			for(int col = 0; col < row.length; col++) {
				row[col] = "| ";
			}
		}
//		drawHorizontalLine(pic,2);
//		drawVerticalLine(pic,3);
//		drawSlot(pic,4,6);
		drawBox(pic,5,8);
		print(pic);
	}
	
	/**
	 * A box looks like this:
	 * ___
	 * |*|
	 * |_|
	 * The '*' marks the coordinates of the box (it is not drawn)
	 * Avoid an AIOOBE, but any portions of the Box 
	 * that fits on the canvas should be drawn
	 * @param pic
	 * @param i
	 * @param j
	 */
	private static void drawBox(String[][] pic, int i, int j) {
		drawSlot(pic,i,j);
		drawSlot(pic,i+1,j);
		drawAt("_",pic,i+1,j);
	}

	private static void drawAt(String string, String[][] pic, int i, int j) {
		//always check the row before the column
		//because the row must exist before the
		
	}

	/**
	 * A slot looks like this:
	 * 		| |
	 * The coordinates of the slot are where the space
	 * between the two vertical line is found. in other words,
	 * to the left of (i,j) and a vertical 
	 * line to the right of (i,j)
	 * CATCH: No ArrayIndexOutOfBoundsException
	 * @param pic
	 * @param i
	 * @param j
	 */
	private static void drawSlot(String[][] pic, int i, int j) {
		if(i >= 0 && i < pic.length) {
			if(j > 0) {
				pic[i][j-1] = "|";
			}
			if(j < pic[i].length-1) {
				pic[i][j+1] = "|";
			}
		}
	}

	private static void drawVerticalLine(String[][] pic, int col) {
		for(int i = 0; i < pic.length; i++) {
			pic[i][col] = "|";
		}	
	}

	private static void drawHorizontalLine(String[][] pic, int row) {
		for(int i = 0; i < pic[row].length; i++) {
			pic[i][row] = "-";
		}
	}

	/**
	 * write a method that prints every string in the pic,
	 * not separated by arrays and no brackets
	 * @param pic
	 */
	public static void print(String[][] pic) {
		for(String[] row: pic) {
			for(String col: row) {
				System.out.println(col);
			}
			System.out.println("");
		}
	}
	
}
