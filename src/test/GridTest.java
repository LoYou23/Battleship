package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.Grid;
import types.Orientation;
import types.Square;


class GridTest {
	
	private static Grid g;
	private static int[] ships = new int[] { 5, 4, 3, 3, 2 }; //TODO - Class for the Ships
	private static final int excpectedNumShips = 17; //5+4+3+3+2
	//ships contains the lengths of the 5 ships (Carrier, Battleship, Cruiser, Submarine and Destroyer)
	//I thought creating classes for the ships was not relevant for this exercise

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		g = new Grid();
	}
	
	@BeforeEach
	void setup() throws Exception {
		g.initField(); //reset the field
	}
	

	
	@Test
	void testShoot() {
		System.out.println("TEST SHOT");
		System.out.println(g);
		g.placeShip(2, Orientation.HORIZONTAL, 0, 0); //place a 2 pieces ship at (0,0) horizontally 
		Square square = g.shoot(0, 0);
		assertTrue(square==Square.DESTROYED);
		square = g.shoot(1,0);
		assertTrue(square==Square.MISSED);

		//Try to shot at (10,10), should raise an exception
		assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
			g.shoot(10, 10);
		});
		
		System.out.println(g);
	}
	
	@Test
	void testCheck() {
		System.out.println("TEST CHECK");
		g.placeShip(2, Orientation.HORIZONTAL, 0, 0);
		g.placeShip(2, Orientation.HORIZONTAL, 2, 2);
		System.out.println(g);
		//Checks if the game is over before even playing
		assertFalse(g.check());
		
		g.shoot(0, 0);
		assertFalse(g.check());
		
		//Missed shot
		g.shoot(3, 3);
		assertFalse(g.check());
		
		g.shoot(0, 1);
		g.shoot(2, 2);
		g.shoot(2, 3);
		//Checks if the game is over after shooting all occupied squares
		assertTrue(g.check());
		
		System.out.println(g);
	}
	
	@Test
	void testSetup() {
		g.setup(ships);
		System.out.println(g);
		assertEquals(excpectedNumShips,countPieces());
		assertFalse(g.check());
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
