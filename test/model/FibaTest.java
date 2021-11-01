package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.NegativeValueException;

public class FibaTest {

	private Fiba fiba;


	public void setupScenary1() {
		fiba = new Fiba();
	}

	public void setupScenary2() {
		fiba = new Fiba();
		Player player1 = new Player("Devin Booker",24,"Phoenix Suns",17,10,5,6,8);
		Player player2 = new Player("Ray Allen",30, "Conferencia Este",9,6,4,10,5);
		Player player3 = new Player("Paul Arizin",28,"Toronto Raptors",20,4,18,13,9);
		Player player4 = new Player("Charles Barkley",31,"Miami Heat",9,12,18,3,7);
		Player player5 = new Player("Dave Bing",27,"Brooklyn Nets",18,15,40,2,1);
		fiba.getABBofPointsByGame().insertNode(17.0, player1);
		fiba.getABBofPointsByGame().insertNode(9.0, player2);
		fiba.getABBofPointsByGame().insertNode(20.0, player3);
		fiba.getABBofPointsByGame().insertNode(9.0, player4);
		fiba.getABBofPointsByGame().insertNode(18.0, player5);
		fiba.getABBofAssists().insertNode(5.0, player1);
		fiba.getABBofAssists().insertNode(4.0, player2);
		fiba.getABBofAssists().insertNode(18.0, player3);
		fiba.getABBofAssists().insertNode(18.0, player4);
		fiba.getABBofAssists().insertNode(40.0, player5);
		fiba.getPlayersByBounces().add(player1);
		fiba.getPlayersByBounces().add(player2);
		fiba.getPlayersByBounces().add(player3);
		fiba.getPlayersByBounces().add(player4);
		fiba.getPlayersByBounces().add(player5);
		fiba.sortPlayers();
	}

	@Test
	public void testAddPlayer1() {
		setupScenary1();
		String name = "Joe Harris";
		String age = "27";
		String team = "Phoenix Suns";
	    String points = "9";
		String bounces = "10";
		String assists = "18";
		String steals = "6";
		String blocks = "8";
		boolean added = false;
        try {
			added = fiba.addPlayer(name,age,team,points,bounces,assists,steals,blocks);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		} catch(NumberFormatException n) {
			fail("No se esperaba esta excepcion");
		}
        assertTrue(added);
        assertTrue(fiba.getABBofAssists().getRoot()!=null);
        assertTrue(fiba.getABBofPointsByGame().getRoot()!=null);
        assertTrue(fiba.getPlayersByBounces().get(0)!=null);
        assertEquals(fiba.getABBofAssists().getRoot().getKey(), 18.0);
        assertEquals(fiba.getABBofPointsByGame().getRoot().getKey(), 9.0);
        assertEquals(fiba.getPlayersByBounces().get(0).getBounces(), 10.0);
        assertEquals(fiba.getPlayersByBounces().size(), 1);
	}

	@Test
	public void testAddPlayer2() {
		setupScenary2();
		String name = "Joe Harris";
		String age = "27";
		String team = "Phoenix Suns";
	    String points = "9";
		String bounces = "10";
		String assists = "18";
		String steals = "6";
		String blocks = "8";
		boolean added = false;
        try {
			added = fiba.addPlayer(name,age,team,points,bounces,assists,steals,blocks);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		} catch(NumberFormatException n) {
			fail("No se esperaba esta excepcion");
		}
        assertTrue(added);
        assertEquals(fiba.getABBofPointsByGame().searchNode(9.0).getLeft().getKey(),9.0);
        assertEquals(fiba.getABBofAssists().searchNode(18.0).getLeft().getKey(),18.0);
        assertEquals(fiba.getPlayersByBounces().get(3).getBounces(), 10);
        assertEquals(fiba.getPlayersByBounces().size(), 6);
	}

	@Test
	public void testAddPlayer3() {
		setupScenary2();
		String name = "Joe Harris";
		String age = "27";
		String team = "Phoenix Suns";
	    String points = "-9";
		String bounces = "10";
		String assists = "18";
		String steals = "6";
		String blocks = "8";
		boolean added = true;
        try {
			added = fiba.addPlayer(name,age,team,points,bounces,assists,steals,blocks);
		} catch (NegativeValueException e) {
			added = false;
		} catch(NumberFormatException n) {
			fail("No se esperaba esta excepcion");
		}
        assertFalse(added);
	}

	@Test
	public void testAddPlayer4() {
		setupScenary2();
		String name = "Joe Harris";
		String age = "27";
		String team = "Phoenix Suns";
	    String points = "9";
		String bounces = "diez";
		String assists = "18";
		String steals = "6";
		String blocks = "8";
		boolean added = true;
        try {
			added = fiba.addPlayer(name,age,team,points,bounces,assists,steals,blocks);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		} catch(NumberFormatException n) {
			added = false;
		}
        assertFalse(added);
	}
	
	@Test
	public void testDeletePlayer() {
		setupScenary2();
		Player player = new Player("Charles Barkley",31,"Miami Heat",9,12,18,3,7);
		fiba.deletePlayer(player);
		assertEquals(fiba.getABBofPointsByGame().searchNode(9.0).getValue().getName(), "Paul Arizin");
		assertEquals(fiba.getABBofPointsByGame().searchNode(9.0).getLeft(),null);
		assertEquals(fiba.getABBofAssists().searchNode(18.0).getValue().getName(), "Paul Arizin");
		assertEquals(fiba.getABBofAssists().searchNode(18.0).getLeft(), null);
		assertTrue(fiba.getPlayersByBounces().size()==4);
		assertEquals(fiba.getPlayersByBounces().get(3).getName(), "Dave Bing");
	}
	
	@Test
	public void testUpdatePlayer1() {
		setupScenary1();
		Player player = new Player("Charles Barkley",31,"Miami Heat",9,12,18,3,7);
		String name = "Charles Barkley";
		String age = "32";
		String team = "Miami Heat";
	    String points = "12";
		String bounces = "12";
		String assists = "12";
		String steals = "4";
		String blocks = "7";
		boolean updated = true;
        try {
			updated = fiba.updatePlayer(player,name,age,team,points,bounces,assists,steals,blocks);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		} catch(NumberFormatException n) {
			fail("No se esperaba esta excepcion");
		}
        assertFalse(updated);
	}
	
	@Test
	public void testUpdatePlayer2() {
		setupScenary2();
		Player player = new Player("Charles Barkley",31,"Miami Heat",9,12,18,3,7);
		String name = "Charles Barkley";
		String age = "32";
		String team = "Miami Heat";
	    String points = "21";
		String bounces = "5";
		String assists = "3";
		String steals = "3";
		String blocks = "7";
		Player updatedPlayer = new Player("Charles Barkley",32,"Miami Heat",21,5,3,3,7);
		boolean updated = false;
		try {
			updated = fiba.updatePlayer(player,name,age,team,points,bounces,assists,steals,blocks);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		} catch(NumberFormatException n) {
			fail("No se esperaba esta excepcion");
		}
        assertTrue(updated);
        assertEquals(fiba.getABBofPointsByGame().searchNode(9.0).getValue().getName(), "Ray Allen");
        assertEquals(fiba.getABBofPointsByGame().searchNode(9.0).getLeft(), null);
        assertEquals(fiba.getABBofPointsByGame().searchNode(20.0).getRight().getValue(), updatedPlayer);
        assertEquals(fiba.getABBofAssists().searchNode(18.0).getValue().getName(), "Paul Arizin");
        assertEquals(fiba.getABBofAssists().searchNode(18.0).getLeft(), null);
        assertEquals(fiba.getABBofAssists().searchNode(4.0).getRight(), updatedPlayer);
        assertEquals(fiba.getPlayersByBounces().get(0).getBounces(),4.0);
        assertEquals(fiba.getPlayersByBounces().get(1).getBounces(),5.0);
        assertEquals(fiba.getPlayersByBounces().get(2).getBounces(),6.0);
        assertEquals(fiba.getPlayersByBounces().get(3).getBounces(),10.0);
        assertEquals(fiba.getPlayersByBounces().get(4).getBounces(),15.0);
	}
	
	@Test
	public void testUpdatePlayer3() {
		setupScenary2();
		Player player = new Player("Charles Barkley",31,"Miami Heat",9,12,18,3,7);
		String name = "Charles Barkley";
		String age = "32";
		String team = "Miami Heat";
	    String points = "-5";
		String bounces = "5";
		String assists = "3";
		String steals = "3";
		String blocks = "7";
		boolean updated = true;
		try {
			updated = fiba.updatePlayer(player,name,age,team,points,bounces,assists,steals,blocks);
		} catch (NegativeValueException e) {
			updated = false;
		} catch(NumberFormatException n) {
			fail("No se esperaba esta excepcion");
		}
        assertFalse(updated);
	}
	
	@Test
	public void testUpdatePlayer4() {
		setupScenary2();
		Player player = new Player("Charles Barkley",31,"Miami Heat",9,12,18,3,7);
		String name = "Charles Barkley";
		String age = "32";
		String team = "Miami Heat";
	    String points = "21";
		String bounces = "5";
		String assists = "nueve";
		String steals = "3";
		String blocks = "7";
		boolean updated = true;
		try {
			updated = fiba.updatePlayer(player,name,age,team,points,bounces,assists,steals,blocks);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		} catch(NumberFormatException n) {
			updated = false;
		}
        assertFalse(updated);
	}
}
