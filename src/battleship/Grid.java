/**
 * 
 */
package battleship;

import java.util.Random;

import types.Square;

/**
 * @author youss
 *
 */
public class Grid {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random(System.currentTimeMillis());
		Grid g = new Grid();
		System.out.println(g.toString());
		// g.placeShip(5, orientation.HORIZONTAL, new Point(0,0));
		// g.placeShip(5, orientation.VERTICAL, new Point(0,1));
		int[] ships = new int[] { 5, 4, 3, 3, 2 };
		g.setup(ships);
		System.out.println(g.toString());
		int n = 20;
		for (int i = 0; i < n; i++) {
			int x = rand.nextInt(GRID_LENGTH);
			int y = rand.nextInt(GRID_WIDTH);
			g.shot(x, y);
		}
		System.out.println(n + " tirs au hasard...\n");
		System.out.println(g.toString());
	}

	private static final int GRID_LENGTH = 10;
	private static final int GRID_WIDTH = 10;

	private Square[][] field;


	public Grid() {
		field = new Square[GRID_LENGTH][GRID_WIDTH];
		initGrid();
	}


	private enum orientation {
		VERTICAL, HORIZONTAL
	}

	private void placePiece(int x, int y) {

		field[x] [y]= Square.OCCUPIED;

	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();

		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_LENGTH; j++) {
				if (field[i][j]== Square.EMPTY) {
					b.append(".  ");
				}

				else if (field[i][j] == Square.OCCUPIED) {
					b.append("O  ");
				}

				else if (field[i][j] == Square.SHOT)
					b.append("X  ");

				else {
					b.append("D  ");
				}
			}

			b.append("\n");
		}

		return b.toString();
	}

	private void initGrid() {
		for (int i = 0; i < GRID_LENGTH; i++) {
			for (int j = 0; j < GRID_WIDTH; j++) {
				field[i][j] = Square.EMPTY;
			}
		}
	}

	private boolean isSquareAvailable(int x, int y) {
		if (field[x][y] == Square.EMPTY)
			return true;
		else
			return false;
	}

	private boolean placeShip(int shiplength, orientation o, int x, int y) {
		int deltaX, deltaY;
		int currentX = x;
		int currentY = y;
		if (o == orientation.VERTICAL) {
			deltaX = 0;
			deltaY = 1;
		}

		else { // o == orientation.HORIZONTAL
			deltaX = 1;
			deltaY = 0;
		}

		for (int i = 0; i < shiplength; i++) {
			if (!isSquareAvailable(currentX, currentY))
				return false;
			else {
				currentX = currentX + deltaX;
				currentY = currentY + deltaY;
			}

			if (currentX >= GRID_LENGTH || currentY >= GRID_WIDTH)
				return false;

		}
		currentX = x;
		currentY = y;// réinitialiser le point initial
		for (int j = 0; j < shiplength; j++) {

			placePiece(currentX, currentY);

			currentX = currentX + deltaX;
			currentY = currentY + deltaY;

		}

		return true;
	}

	private orientation pickRandomOrientation(Random rand) {
		if (rand.nextBoolean())
			return orientation.HORIZONTAL;
		else
			return orientation.VERTICAL;
	}

	public void setup(int[] ships) {
		initGrid();
		boolean out = false;
		Random rand = new Random(System.currentTimeMillis());
		for (int shiplength : ships) {
			orientation o = pickRandomOrientation(rand);
			// System.out.println(o);
			int[] listX = Utils.getRandomRange(GRID_LENGTH);
			int[] listY = Utils.getRandomRange(GRID_WIDTH);
			for (int x : listX) {
				out = false;
				for (int y : listY) {
					if (placeShip(shiplength, o, x, y)) {
						out = true;
						break;
					}
				}
				if (out)
					break;
			}
		}
	}

	public boolean check() {
		int nbDestroyed = 0;
		for (int i = 0; i < GRID_LENGTH; i++) {
			for (int j = 0; j < GRID_WIDTH; j++) {
				if (field[i][j] == Square.OCCUPIED)
					return false;

				else if (field[i][j] == Square.DESTROYED)
					nbDestroyed++;
			}
		}
		return nbDestroyed > 0;
	}

	public Square shot(int x, int y) {
		if (field[x][y] == Square.OCCUPIED)
			field[x][y]= Square.DESTROYED;
		else if (field[x][y] == Square.EMPTY)
			field[x][y] =  Square.SHOT;
		
		return field[x][y];
	}
	
	public Square getSquareState(int x, int y) {
		return field[x][y];
	}
	

}
