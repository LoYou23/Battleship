package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.Grid;
import types.Square;


class GridTest {
	
	private static Grid g;
	private static int[] ships = new int[] { 5, 4, 3, 3, 2 }; //TODO - Class for the Ships
	//ships contains the lengths of the 5 ships (Carrier, Battleship, Cruiser, Submarine and Destroyer)
	//I thought creating classes for the ships was not relevant for this exercise

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		g = new Grid();
	}

	@BeforeEach
	void setUp() throws Exception {
		
		g.setup(ships);
	}
	
	@Test
	void testShoot() {
		System.out.println("TEST SHOT");
		System.out.println(g);
		g.shoot(7, 8);
		//Shot at (7,8) then check the state of this square
		Square square = g.getField()[7][8];
		assertFalse(square == Square.OCCUPIED);
		assertFalse(square == Square.EMPTY);
		assertTrue(square == Square.DESTROYED || square == Square.SHOT);

		//Try to shot at (10,10), should raise an exception
		assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
			g.shoot(10, 10);
		});
		
		System.out.println(g);
	}
	
	@Test
	void testCheck() {
		System.out.println("TEST CHECK");
		System.out.println(g);
		//Checks if the game is over before even playing
		assertFalse(g.check());
		shotAll();
		//Checks if the game is over after shooting all squares
		assertTrue(g.check());
		
		System.out.println(g);
	}
	
	@Test
	void testSetup() {
		int numPieces = 0;
		for(int num : ships)
			numPieces = numPieces + num;
		//Checks if the number of pieces is the same between ships list and the field
		assertEquals(numPieces,countPieces());
		assertFalse(g.check());
	}
	
	private static void shotAll() { //Helper function to shot all squares
		for (int i = 0; i<10; i++) {
			for (int j = 0; j<10; j++) {
				g.shoot(i, j);
			}
		}
	}
	
	private static int countPieces() { //Helper function to count the pieces on the field
		int res = 0;
		Square field[][] = g.getField();
		for (int i = 0; i<10; i++) {
			for (int j = 0; j<10; j++) {
				if(field[i][j] == Square.OCCUPIED)
					res++;
			}
		}
		
		return res;
	}

}
