package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
		fiba.getRbtSteals().insert(player1.getSteals(), player1);
		fiba.getRbtSteals().insert(player2.getSteals(), player2);
		fiba.getRbtSteals().insert(player3.getSteals(), player3);
		fiba.getRbtSteals().insert(player4.getSteals(), player4);
		fiba.getRbtSteals().insert(player5.getSteals(), player5);
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
        
        assertTrue(fiba.getRbtSteals().getRoot()!=fiba.getRbtSteals().getNil());
        assertEquals(fiba.getRbtSteals().getRoot().getKey(), 6.0);
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
        assertEquals(fiba.getABBofPointsByGame().searchNode(9.0).getValue().getName(), "Ray Allen");
        assertEquals(fiba.getABBofPointsByGame().searchNode(9.0).getSameKeyNodes().get(1).getKey(),9.0);
        assertEquals(fiba.getABBofPointsByGame().searchNode(9.0).getSameKeyNodes().get(1).getValue().getName(),"Joe Harris");
        assertEquals(fiba.getABBofAssists().searchNode(18.0).getValue().getName(), "Paul Arizin");
        assertEquals(fiba.getABBofAssists().searchNode(18.0).getSameKeyNodes().get(1).getKey(),18.0);
        assertEquals(fiba.getABBofAssists().searchNode(18.0).getSameKeyNodes().get(1).getValue().getName(),"Joe Harris");
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
		Player player = fiba.getABBofPointsByGame().searchNode(9.0).getSameKeyNodes().get(0).getValue();
		fiba.deletePlayer(player);
		assertEquals(fiba.getABBofPointsByGame().searchNode(9.0).getValue().getName(), "Ray Allen");
		assertTrue(fiba.getABBofPointsByGame().searchNode(9.0).getSameKeyNodes().isEmpty());
		assertEquals(fiba.getABBofAssists().searchNode(18.0).getValue().getName(), "Paul Arizin");
		assertTrue(fiba.getABBofAssists().searchNode(18.0).getSameKeyNodes().isEmpty());
		assertTrue(fiba.getPlayersByBounces().size()==4);
		assertEquals(fiba.getPlayersByBounces().get(3).getName(), "Dave Bing");
	}
	
	
	
	@Test
	public void testUpdatePlayer1() {
		setupScenary2();
		Player player = fiba.getABBofPointsByGame().searchNode(9.0).getSameKeyNodes().get(0).getValue();
		String name = "Charles Barkley";
		String age = "32";
		String team = "Miami Heat";
	    String points = "21";
		String bounces = "5";
		String assists = "5";
		String steals = "3";
		String blocks = "7";
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
        assertTrue(fiba.getABBofPointsByGame().searchNode(9.0).getSameKeyNodes().isEmpty());
        assertEquals(fiba.getABBofPointsByGame().searchNode(20.0).getRight().getValue(), player);
        assertEquals(fiba.getABBofPointsByGame().searchNode(20.0).getRight().getKey(), 21.0);
        assertEquals(fiba.getABBofAssists().searchNode(18.0).getValue().getName(), "Paul Arizin");
        assertTrue(fiba.getABBofAssists().searchNode(18.0).getSameKeyNodes().isEmpty());
        assertEquals(fiba.getABBofAssists().searchNode(5.0).getSameKeyNodes().get(0).getValue(), player);
        assertEquals(fiba.getABBofAssists().searchNode(5.0).getSameKeyNodes().get(0).getValue().getName(), "Charles Barkley");
        assertEquals(fiba.getPlayersByBounces().get(0).getBounces(),4.0);
        assertEquals(fiba.getPlayersByBounces().get(1).getBounces(),5.0);
        assertEquals(fiba.getPlayersByBounces().get(2).getBounces(),6.0);
        assertEquals(fiba.getPlayersByBounces().get(3).getBounces(),10.0);
        assertEquals(fiba.getPlayersByBounces().get(4).getBounces(),15.0);
	}
	
	@Test
	public void testUpdatePlayer2() {
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
	public void testUpdatePlayer3() {
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
	
	@Test
	public void testSearchPlayersLinearly1() {
		setupScenary1();
		String value = "20";
		String comparison = "LESS";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertTrue(listOfPlayers.isEmpty());
	}
	
	@Test
	public void testSearchPlayersLinearly2() {
		setupScenary2();
		String value = "6";
		String comparison = "EQUAL";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertEquals(listOfPlayers.size(), 1);
		assertEquals(listOfPlayers.get(0).getName(), "Ray Allen");
		assertEquals(listOfPlayers.get(0).getBounces(), 6.0);
	}
	
	@Test
	public void testSearchPlayersLinearly3() {
		setupScenary2();
		String value = "6";
		String comparison = "GREATER";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertEquals(listOfPlayers.size(), 3);
		assertEquals(listOfPlayers.get(0).getName(), "Devin Booker");
		assertEquals(listOfPlayers.get(0).getBounces(), 10.0);
		assertEquals(listOfPlayers.get(1).getName(), "Charles Barkley");
		assertEquals(listOfPlayers.get(1).getBounces(), 12.0);
		assertEquals(listOfPlayers.get(2).getName(), "Dave Bing");
		assertEquals(listOfPlayers.get(2).getBounces(), 15.0);
	}
	
	@Test
	public void testSearchPlayersLinearly4() {
		setupScenary2();
		String value = "12";
		String comparison = "LESS";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertEquals(listOfPlayers.size(), 3);
		assertEquals(listOfPlayers.get(0).getName(), "Paul Arizin");
		assertEquals(listOfPlayers.get(0).getBounces(), 4.0);
		assertEquals(listOfPlayers.get(1).getName(), "Ray Allen");
		assertEquals(listOfPlayers.get(1).getBounces(), 6.0);
		assertEquals(listOfPlayers.get(2).getName(), "Devin Booker");
		assertEquals(listOfPlayers.get(2).getBounces(), 10.0);
	}
	
	@Test
	public void testSearchPlayersLinearly5() {
		setupScenary2();
		String value = "6";
		String comparison = "EQUALGREATER";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertEquals(listOfPlayers.size(), 4);
		assertEquals(listOfPlayers.get(0).getName(), "Ray Allen");
		assertEquals(listOfPlayers.get(0).getBounces(), 6.0);
		assertEquals(listOfPlayers.get(1).getName(), "Devin Booker");
		assertEquals(listOfPlayers.get(1).getBounces(), 10.0);
		assertEquals(listOfPlayers.get(2).getName(), "Charles Barkley");
		assertEquals(listOfPlayers.get(2).getBounces(), 12.0);
		assertEquals(listOfPlayers.get(3).getName(), "Dave Bing");
		assertEquals(listOfPlayers.get(3).getBounces(), 15.0);
	}
	
	@Test
	public void testSearchPlayersLinearly6() {
		setupScenary2();
		String value = "12";
		String comparison = "EQUALLESS";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertEquals(listOfPlayers.size(), 4);
		assertEquals(listOfPlayers.get(0).getName(), "Paul Arizin");
		assertEquals(listOfPlayers.get(0).getBounces(), 4.0);
		assertEquals(listOfPlayers.get(1).getName(), "Ray Allen");
		assertEquals(listOfPlayers.get(1).getBounces(), 6.0);
		assertEquals(listOfPlayers.get(2).getName(), "Devin Booker");
		assertEquals(listOfPlayers.get(2).getBounces(), 10.0);
		assertEquals(listOfPlayers.get(3).getName(), "Charles Barkley");
		assertEquals(listOfPlayers.get(3).getBounces(), 12.0);
	}
	
	@Test
	public void testSearchPlayersLinearly7() {
		setupScenary2();
		String value = "-12";
		String comparison = "EQUALLESS";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		boolean exception = false;
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			exception = true;
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertTrue(listOfPlayers.isEmpty());
		assertTrue(exception);
	}
	
	@Test
	public void testSearchPlayersLinearly8() {
		setupScenary2();
		String value = "doce";
		String comparison = "EQUALLESS";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		boolean exception = false;
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			exception = true;
		}
		assertTrue(listOfPlayers.isEmpty());
		assertTrue(exception);
	}
	
	@Test
	public void testSearchPlayersABB1() {
		setupScenary1();
		String value = "12";
		String comparison = "POINTSLESS";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertTrue(listOfPlayers.isEmpty());
	}
	
	@Test
	public void testSearchPlayersABB2() {
		setupScenary2();
		String value = "9";
		String comparison = "POINTSEQUAL";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertEquals(listOfPlayers.size(), 2);
		assertEquals(listOfPlayers.get(0).getName(), "Ray Allen");
		assertEquals(listOfPlayers.get(0).getPoints(), 9.0);
		assertEquals(listOfPlayers.get(1).getName(), "Charles Barkley");
		assertEquals(listOfPlayers.get(1).getBounces(), 9.0);
	}
	
	@Test
	public void testSearchPlayersABB3() {
		setupScenary2();
		String value = "17";
		String comparison = "POINTSGREATER";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertEquals(listOfPlayers.size(), 2);
		assertEquals(listOfPlayers.get(0).getName(), "Dave Bing");
		assertEquals(listOfPlayers.get(0).getBounces(), 18.0);
		assertEquals(listOfPlayers.get(1).getName(), "Paul Arizin");
		assertEquals(listOfPlayers.get(1).getPoints(), 20.0);
	}
	
	@Test
	public void testSearchPlayersABB4() {
		setupScenary2();
		String value = "17";
		String comparison = "POINTSLESS";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertEquals(listOfPlayers.size(), 2);
		assertEquals(listOfPlayers.get(0).getName(), "Ray Allen");
		assertEquals(listOfPlayers.get(0).getPoints(), 9.0);
		assertEquals(listOfPlayers.get(1).getName(), "Charles Barkley");
		assertEquals(listOfPlayers.get(1).getBounces(), 9.0);
	}
	
	@Test
	public void testSearchPlayersABB5() {
		setupScenary2();
		String value = "17";
		String comparison = "POINTSEQUALGREATER";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertEquals(listOfPlayers.size(), 3);
		assertEquals(listOfPlayers.get(0).getName(), "Devin Booker");
		assertEquals(listOfPlayers.get(0).getBounces(), 17.0);
		assertEquals(listOfPlayers.get(1).getName(), "Dave Bing");
		assertEquals(listOfPlayers.get(1).getBounces(), 18.0);
		assertEquals(listOfPlayers.get(2).getName(), "Paul Arizin");
		assertEquals(listOfPlayers.get(2).getPoints(), 20.0);
	}
	
	public void testSearchPlayersABB6() {
		setupScenary2();
		String value = "17";
		String comparison = "POINTSLESS";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertEquals(listOfPlayers.size(), 3);
		assertEquals(listOfPlayers.get(0).getName(), "Ray Allen");
		assertEquals(listOfPlayers.get(0).getPoints(), 9.0);
		assertEquals(listOfPlayers.get(1).getName(), "Charles Barkley");
		assertEquals(listOfPlayers.get(1).getBounces(), 9.0);
		assertEquals(listOfPlayers.get(2).getName(), "Devin Booker");
		assertEquals(listOfPlayers.get(2).getBounces(), 17.0);
	}
	
	public void testSearchPlayersABB7() {
		setupScenary2();
		String value = "-17";
		String comparison = "POINTSEQUALLESS";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		boolean exception = false;
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			exception = true;
		}catch(NumberFormatException num) {
			fail("No se esperaba esta excepcion");
		}
		assertTrue(listOfPlayers.isEmpty());
		assertTrue(exception);
	}
	
	public void testSearchPlayersABB8() {
		setupScenary2();
		String value = "diecisiete";
		String comparison = "POINTSEQUALLESS";
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		boolean exception = false;
		try {
			listOfPlayers = fiba.searchPlayersLinearly(value, comparison);
		} catch (NegativeValueException e) {
			fail("No se esperaba esta excepcion");
		}catch(NumberFormatException num) {
			exception = true;
		}
		assertTrue(listOfPlayers.isEmpty());
		assertTrue(exception);
	}
}
