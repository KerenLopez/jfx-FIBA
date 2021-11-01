package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Player;

public class BSTtreeTest {
	
	private BSTtree<Double, Player> pointsTree;

	public void setupScenary1() {
		pointsTree =new BSTtree<Double, Player>();
	}
	
	public void setupScenary2() {
		pointsTree =new BSTtree<Double, Player>();
		Player player1 = new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0); 
		pointsTree.insertNode(50.0, player1);
		Player player2 = new Player("Ray Allen", 30, "Conferencia Este", 30.0,6.0,12.0,10.0,5.0); 
		pointsTree.insertNode(30.0, player2);
		Player player3 = new Player("Paul Arizin", 28, "Toronto Raptors", 70.0,4.0,8.0,13.0,9.0); 
		pointsTree.insertNode(70.0, player3);
		Player player4 = new Player("Charles Barkley", 31, "Miami Heat", 20.0,16.0,9.0,3.0,7.0); 
		pointsTree.insertNode(20.0, player4);
		Player player5 = new Player("Rick Barry", 26, "Indiana Pacers", 40.0,2.0,5.0,18.0,9.0); 
		pointsTree.insertNode(40.0, player5);
		Player player6 = new Player("Dave Bing", 27, "Brooklyn Nets", 60.0,13.0,8.0,2.0,1.0); 
		pointsTree.insertNode(60.0, player6);
		Player player7 = new Player("Larry Bird", 28, "Chicago Bulls", 80.0,2.0,19.0,17.0,5.0); 
		pointsTree.insertNode(80.0, player7);
	}
	
	public void setupScenary3() {
		pointsTree =new BSTtree<Double, Player>();
		Player player1 = new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0); 
		pointsTree.insertNode(50.0, player1);
		Player player2 = new Player("Ray Allen", 30, "Conferencia Este", 30.0,6.0,12.0,10.0,5.0); 
		pointsTree.insertNode(30.0, player2);
		Player player3 = new Player("Paul Arizin", 28, "Toronto Raptors", 70.0,4.0,8.0,13.0,9.0); 
		pointsTree.insertNode(70.0, player3);
		Player player5 = new Player("Rick Barry", 26, "Indiana Pacers", 40.0,2.0,5.0,18.0,9.0); 
		pointsTree.insertNode(40.0, player5);
		Player player6 = new Player("Dave Bing", 27, "Brooklyn Nets", 60.0,13.0,8.0,2.0,1.0); 
		pointsTree.insertNode(60.0, player6);
		Player player7 = new Player("Larry Bird", 28, "Chicago Bulls", 80.0,2.0,19.0,17.0,5.0); 
		pointsTree.insertNode(80.0, player7);
	}
	
	public void setupScenary4() {
		pointsTree =new BSTtree<Double, Player>();
		Player player1 = new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0); 
		pointsTree.insertNode(50.0, player1);
		Player player2 = new Player("Rick Barry", 26, "Indiana Pacers", 40.0,2.0,5.0,18.0,9.0); 
		pointsTree.insertNode(40.0, player2);
		Player player3 = new Player("Paul Arizin", 28, "Toronto Raptors", 70.0,4.0,8.0,13.0,9.0); 
		pointsTree.insertNode(70.0, player3);
		Player player4 = new Player("Dave Bing", 27, "Brooklyn Nets", 60.0,13.0,8.0,2.0,1.0); 
		pointsTree.insertNode(60.0, player4);
		Player player5 = new Player("Larry Bird", 28, "Chicago Bulls", 80.0,2.0,19.0,17.0,5.0); 
		pointsTree.insertNode(80.0, player5);
	}
	
	
	@Test
	public void testInsertNode1() {
		setupScenary1();
		double key = 50.0;
		Player player1 = new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0); 
		pointsTree.insertNode(50.0, player1);
		assertEquals(pointsTree.getRoot().getKey(), key);
		assertEquals(pointsTree.getRoot().getValue().getName(), player1.getName());
	}
	
	@Test
	public void testInsertNode2() {
		setupScenary2();
		double key = 10.0;
		Player player8 = new Player("Chris Bosh", 24, "Atlanta Hawks", 10.0,10.0,15.0,6.0,8.0); 
		pointsTree.insertNode(key, player8);
		assertEquals(pointsTree.searchNode(20.0).getLeft().getKey(), key);
		assertEquals(pointsTree.searchNode(20.0).getLeft().getValue().getName(), player8.getName());
	}
	
	@Test
	public void testInsertNode3() {
		setupScenary2();
		double key = 90.0;
		Player player8 = new Player("Carl Braun", 23, "Orlando Magic", 90.0,10.0,15.0,6.0,8.0); 
		pointsTree.insertNode(key, player8);
		assertEquals(pointsTree.searchNode(80.0).getRight().getKey(), key);
		assertEquals(pointsTree.searchNode(80.0).getRight().getValue().getName(), player8.getName());
	}
	
	@Test
	public void testSearchNode1() {
		setupScenary1();
		double key = 50.0;
		assertEquals(pointsTree.searchNode(key), null);
	}
	
	@Test
	public void testSearchNode2() {
		setupScenary2();
		double key = 10.0;
		assertEquals(pointsTree.searchNode(key), null);
	}
	@Test
	public void testSearchNode3() {
		setupScenary2();
		double key = 60.0;
		assertEquals(pointsTree.searchNode(key).getValue().getName(), "Dave Bing");
		assertEquals(pointsTree.searchNode(key).getValue().getAge(), 27.0);
		assertEquals(pointsTree.searchNode(key).getValue().getTeam(), "Brooklyn Nets");
		assertEquals(pointsTree.searchNode(key).getValue().getPoints(), 60.0);
		assertEquals(pointsTree.searchNode(key).getValue().getAssists(), 8.0);
		assertEquals(pointsTree.searchNode(key).getValue().getBlocks(), 1.0);
		assertEquals(pointsTree.searchNode(key).getValue().getBounces(), 13.0);
		assertEquals(pointsTree.searchNode(key).getValue().getSteals(), 2.0);
	}
	
	@Test
	public void testDeleteNode1() {
		setupScenary1();
		double key = 50.0;
		assertFalse(pointsTree.deleteNode(key));
	}
	
	@Test
	public void testDeleteNode2() {
		setupScenary2();
		double key = 10.0;
		assertFalse(pointsTree.deleteNode(key));
	}
	
	@Test
	public void testDeleteNode3() {
		setupScenary2();
		double key = 20.0;
		assertTrue(pointsTree.deleteNode(key));
		assertEquals(pointsTree.searchNode(30.0).getLeft(),null);
	}
	
	@Test
	public void testDeleteNode4() {
		setupScenary3();
		double key = 30.0;
		assertTrue(pointsTree.deleteNode(key));
		assertEquals(pointsTree.getRoot().getLeft().getKey(),40.0); 
	}
	
	@Test
	public void testDeleteNode5() {
		setupScenary4();
		double key = 50.0;
		assertTrue(pointsTree.deleteNode(key));
		assertEquals(pointsTree.getRoot().getKey(), 60.0);
		assertEquals(pointsTree.getRoot().getLeft().getKey(), 40.0);
		assertEquals(pointsTree.getRoot().getRight().getKey(), 70.0);
	}
	
	@Test
	public void testInorderTraversal1() {
		setupScenary1();
		assertTrue(pointsTree.inorderTraversal().isEmpty());
	}
	
	@Test
	public void testInorderTraversal2() {
		setupScenary2();
		ArrayList<BSTNode<Double,Player>> nodes =pointsTree.inorderTraversal();
		assertEquals(nodes.get(0).getKey(), 20.0);
		assertEquals(nodes.get(0), pointsTree.searchNode(20.0));
		assertEquals(nodes.get(1).getKey(), 30.0);
		assertEquals(nodes.get(1), pointsTree.searchNode(30.0));
		assertEquals(nodes.get(2).getKey(), 40.0);
		assertEquals(nodes.get(2), pointsTree.searchNode(40.0));
		assertEquals(nodes.get(3).getKey(), 50.0);
		assertEquals(nodes.get(3), pointsTree.searchNode(50.0));
		assertEquals(nodes.get(4).getKey(), 60.0);
		assertEquals(nodes.get(4), pointsTree.searchNode(60.0));
		assertEquals(nodes.get(5).getKey(), 70.0);
		assertEquals(nodes.get(5), pointsTree.searchNode(70.0));
		assertEquals(nodes.get(6).getKey(), 80.0);
		assertEquals(nodes.get(6), pointsTree.searchNode(80.0));
	}
}
