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
	void testShot() {
		System.out.println("TEST SHOT");
		Square square = g.shot(7, 8);
		assertTrue(square == Square.DESTROYED || square == Square.SHOT);
		square = g.shot(7, 8);
		assertFalse(square == Square.OCCUPIED);
		assertFalse(square == Square.EMPTY);
		assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
			g.shot(10, 10);
		});
		
		System.out.println(g);
	}
	
	@Test
	void testCheck() {
		System.out.println("TEST CHECK");
		System.out.println(g);
		
		assertFalse(g.check());
		shotAll();
		assertTrue(g.check());
		
		System.out.println(g);
	}
	
	@Test
	void testSetup() {
		System.out.println("TEST SETUP");
		int numPieces = 0;
		for(int num : ships)
			numPieces = numPieces + num;
		assertEquals(numPieces,countPieces());
		assertFalse(g.check());
	}
	
	private static void shotAll() { //Helper function to shot all squares
		for (int i = 0; i<10; i++) {
			for (int j = 0; j<10; j++) {
				g.shot(i, j);
			}
		}
	}
	
	private static int countPieces() {
		int res = 0;
		for (int i = 0; i<10; i++) {
			for (int j = 0; j<10; j++) {
				if(g.getSquareState(i, j) == Square.OCCUPIED)
					res++;
			}
		}
		
		return res;
	}

}
