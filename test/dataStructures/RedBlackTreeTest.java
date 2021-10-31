package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Player;

public class RedBlackTreeTest {

	private RedBlackTree<Double, Player> rbt;
	
	public void setupScenary1() {
		rbt= new RedBlackTree<>();
	}
	
	public void setupScenary2() {
		rbt= new RedBlackTree<>();
		
		rbt.insert(4.0, new Player("Ray Allen", 30, "Conferencia Este", 30, 6, 9, 4, 5));
		rbt.insert(8.0, new Player("Paul Arizin", 28, "Toronto Raptors", 70, 4, 20, 8, 9));
		rbt.insert(6.0, new Player("Devin Booker", 24, "Phoenix Suns", 50, 10, 17, 6, 8));
		rbt.insert(10.0, new Player("Charles Barkley", 31, "Miami Heat", 20, 16, 6, 10, 7));


	}
	
	public void setupScenary3() {
		rbt= new RedBlackTree<>();
		
		rbt.insert(8.0, new Player("Paul Arizin", 28, "Toronto Raptors", 70, 4, 20, 8, 9));
		rbt.insert(4.0, new Player("Ray Allen", 30, "Conferencia Este", 30, 6, 9, 4, 5));
		rbt.insert(6.0, new Player("Devin Booker", 24, "Phoenix Suns", 50, 10, 17, 6, 8));
		rbt.insert(2.0, new Player("Charles Barkley", 31, "Miami Heat", 20, 16, 6, 2, 7));

	}
	
	
	@Test
	public void testInsert1() {
		setupScenary1();
		
				
		String name = "Ray Allen";
		int age = 30;
		String team = "Conferencia Este";
		double points = 30;
		double bounces = 6;
		double assists = 9;
		double steals = 4;
		double blocks = 5;
		
		Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

		rbt.insert(player.getSteals(), player);
		assertTrue(rbt.getRoot().getValue()==player);
		assertTrue(rbt.getRoot().getColor()=='B');
		assertTrue(rbt.getRoot().getLeft()==rbt.getNil());
		assertTrue(rbt.getRoot().getRight()==rbt.getNil());

	}
	
	@Test
	public void testInsert2() {
		setupScenary2();
		
				
		String name = "Rick Barry";
		int age = 26;
		String team = "Indiana Pacers";
		double points = 40;
		double bounces = 2;
		double assists = 5;
		double steals = 9;
		double blocks = 9;
		
		Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

		rbt.insert(player.getSteals(), player);
		
		NodeRBT<Double,Player> node= rbt.search(rbt.getRoot(),9.0);
		assertTrue(node.getValue()==player);
		assertTrue(node.getColor()=='B');
		assertTrue(node.getParent().getKey()==6);
		assertTrue(node.getParent().getRight()==node);
		assertTrue(node.getLeft().getKey()==8);
		assertTrue(node.getRight().getKey()==10);
	}
	
	@Test
	public void testInsert3() {
		setupScenary2();
		
				
		String name = "Rick Barry";
		int age = 26;
		String team = "Indiana Pacers";
		double points = 40;
		double bounces = 2;
		double assists = 5;
		double steals = 11;
		double blocks = 9;
		
		Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

		rbt.insert(player.getSteals(), player);
		
		NodeRBT<Double,Player> node= rbt.search(rbt.getRoot(),11.0);
		assertTrue(node.getValue()==player);
		assertTrue(node.getColor()=='R');
		assertTrue(node.getParent().getKey()==10);
		assertTrue(node.getParent().getColor()=='B');
		assertTrue(node.getParent().getRight()==node);
		assertTrue(node.getParent().getLeft().getKey()==8);
		assertTrue(node.getParent().getLeft().getColor()=='R');
		assertTrue(node.getLeft()==rbt.getNil());
		assertTrue(node.getRight()==rbt.getNil());
	}
	
	@Test
	public void testInsert4() {
		setupScenary2();
		
				
		String name = "Rick Barry";
		int age = 26;
		String team = "Indiana Pacers";
		double points = 40;
		double bounces = 2;
		double assists = 5;
		double steals = 5;
		double blocks = 9;
		
		Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

		rbt.insert(player.getSteals(), player);
		
		NodeRBT<Double,Player> node= rbt.search(rbt.getRoot(),5.0);
		assertTrue(node.getValue()==player);
		assertTrue(node.getColor()=='R');
		assertTrue(node.getParent().getKey()==4);
		assertTrue(node.getParent().getColor()=='B');
		assertTrue(node.getParent().getRight()==node);
		assertTrue(node.getLeft()==rbt.getNil());
		assertTrue(node.getRight()==rbt.getNil());
	}
	
	@Test
	public void testInsert5() {
		setupScenary3();
		
				
		String name = "Rick Barry";
		int age = 26;
		String team = "Indiana Pacers";
		double points = 40;
		double bounces = 2;
		double assists = 5;
		double steals = 1;
		double blocks = 9;
		
		Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

		rbt.insert(player.getSteals(), player);
		
		NodeRBT<Double,Player> node= rbt.search(rbt.getRoot(),1.0);
		assertTrue(node.getValue()==player);
		assertTrue(node.getColor()=='R');
		assertTrue(node.getParent().getKey()==2);
		assertTrue(node.getParent().getColor()=='B');
		assertTrue(node.getParent().getLeft()==node);
		assertTrue(node.getParent().getRight().getKey()==4);
		assertTrue(node.getParent().getRight().getColor()=='R');
		assertTrue(node.getLeft()==rbt.getNil());
		assertTrue(node.getRight()==rbt.getNil());
	}
	
	@Test
	public void testInsert6() {
		setupScenary3();
		
				
		String name = "Rick Barry";
		int age = 26;
		String team = "Indiana Pacers";
		double points = 40;
		double bounces = 2;
		double assists = 5;
		double steals = 3;
		double blocks = 9;
		
		Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

		rbt.insert(player.getSteals(), player);
		
		NodeRBT<Double,Player> node= rbt.search(rbt.getRoot(),3.0);
		assertTrue(node.getValue()==player);
		assertTrue(node.getColor()=='B');
		assertTrue(node.getParent().getKey()==6);
		assertTrue(node.getParent().getLeft()==node);
		assertTrue(node.getLeft().getKey()==2);
		assertTrue(node.getRight().getKey()==4);
	}
	@Test
	public void testInsert7() {
		setupScenary3();
		
				
		String name = "Rick Barry";
		int age = 26;
		String team = "Indiana Pacers";
		double points = 40;
		double bounces = 2;
		double assists = 5;
		double steals = 7;
		double blocks = 9;
		
		Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

		rbt.insert(player.getSteals(), player);
		
		NodeRBT<Double,Player> node= rbt.search(rbt.getRoot(),7.0);
		assertTrue(node.getValue()==player);
		assertTrue(node.getColor()=='R');
		assertTrue(node.getParent().getKey()==8);
		assertTrue(node.getParent().getColor()=='B');
		assertTrue(node.getParent().getLeft()==node);
		assertTrue(node.getLeft()==rbt.getNil());
		assertTrue(node.getRight()==rbt.getNil());
	}
	
	@Test
	public void testSearch1() {
		setupScenary1();
		
		double key=7;
		
		NodeRBT<Double,Player> node= rbt.search(rbt.getRoot(),key);
		
		assertTrue(node==null);
		
	}
	
	@Test
	public void testSearch2() {
		setupScenary2();
		
		double key=10;
		
		NodeRBT<Double,Player> node= rbt.search(rbt.getRoot(),key);
		
		assertFalse(node==null);
		assertTrue(node.getValue().getName().equals("Charles Barkley"));
		assertTrue(node.getValue().getAge()==31);
		assertTrue(node.getValue().getTeam().equals("Miami Heat"));
		assertTrue(node.getValue().getPoints()==20);
		assertTrue(node.getValue().getBounces()==16);
		assertTrue(node.getValue().getAssists()==6);
		assertTrue(node.getValue().getSteals()==10);
		assertTrue(node.getValue().getBlocks()==7);
			
	}
	
	@Test
	public void testSearch3() {
		setupScenary3();
		
		double key=2;
		
		NodeRBT<Double,Player> node= rbt.search(rbt.getRoot(),key);
		
		assertFalse(node==null);
		assertTrue(node.getValue().getName().equals("Charles Barkley"));
		assertTrue(node.getValue().getAge()==31);
		assertTrue(node.getValue().getTeam().equals("Miami Heat"));
		assertTrue(node.getValue().getPoints()==20);
		assertTrue(node.getValue().getBounces()==16);
		assertTrue(node.getValue().getAssists()==6);
		assertTrue(node.getValue().getSteals()==2);
		assertTrue(node.getValue().getBlocks()==7);
			
	}
	
	@Test
	public void testDelete1() {
		setupScenary2();
		double key=6;
		
		rbt.delete(key);
		
		assertTrue(rbt.getRoot().getKey()==8);
		
		assertTrue(rbt.getRoot().getLeft().getKey()==4);
		assertTrue(rbt.getRoot().getLeft().getColor()=='B');
		assertTrue(rbt.getRoot().getRight().getKey()==10);
		assertTrue(rbt.getRoot().getRight().getColor()=='B');
		
		
	}
	
	@Test
	public void testDelete2() {
		setupScenary2();
		double key=8;
		
		rbt.delete(key);
		
		assertTrue(rbt.getRoot().getKey()==6);
		
		assertTrue(rbt.getRoot().getLeft().getKey()==4);
		assertTrue(rbt.getRoot().getLeft().getColor()=='B');
		assertTrue(rbt.getRoot().getRight().getKey()==10);
		assertTrue(rbt.getRoot().getRight().getColor()=='B');
		
		
	}
	
	@Test
	public void testDelete3() {
		setupScenary2();
		double key=4;
		
		rbt.delete(key);
		
		assertTrue(rbt.getRoot().getKey()==8);
		
		assertTrue(rbt.getRoot().getLeft().getKey()==6);
		assertTrue(rbt.getRoot().getLeft().getColor()=='B');
		assertTrue(rbt.getRoot().getRight().getKey()==10);
		assertTrue(rbt.getRoot().getRight().getColor()=='B');
		
		
	}
	
	@Test
	public void testDelete4() {
		setupScenary2();
		double key=10;
		
		rbt.delete(key);
		
		assertTrue(rbt.getRoot().getKey()==6);
		
		assertTrue(rbt.getRoot().getLeft().getKey()==4);
		assertTrue(rbt.getRoot().getLeft().getColor()=='B');
		assertTrue(rbt.getRoot().getRight().getKey()==8);
		assertTrue(rbt.getRoot().getRight().getColor()=='B');
		
		
	}
	
	@Test
	public void testDelete5() {
		setupScenary3();
		double key=6;
		
		rbt.delete(key);
		
		assertTrue(rbt.getRoot().getKey()==4);
		
		assertTrue(rbt.getRoot().getLeft().getKey()==2);
		assertTrue(rbt.getRoot().getLeft().getColor()=='B');
		assertTrue(rbt.getRoot().getRight().getKey()==8);
		assertTrue(rbt.getRoot().getRight().getColor()=='B');
		
		
	}
	
	@Test
	public void testDelete6() {
		setupScenary3();
		double key=4;
		
		rbt.delete(key);
		
		assertTrue(rbt.getRoot().getKey()==6);
		
		assertTrue(rbt.getRoot().getLeft().getKey()==2);
		assertTrue(rbt.getRoot().getLeft().getColor()=='B');
		assertTrue(rbt.getRoot().getRight().getKey()==8);
		assertTrue(rbt.getRoot().getRight().getColor()=='B');
		
		
	}
	
	@Test
	public void testDelete7() {
		setupScenary3();
		double key=8;
		
		rbt.delete(key);
		
		assertTrue(rbt.getRoot().getKey()==4);
		
		assertTrue(rbt.getRoot().getLeft().getKey()==2);
		assertTrue(rbt.getRoot().getLeft().getColor()=='B');
		assertTrue(rbt.getRoot().getRight().getKey()==6);
		assertTrue(rbt.getRoot().getRight().getColor()=='B');
		
		
	}
	
	@Test
	public void testDelete8() {
		setupScenary3();
		double key=2;
		
		rbt.delete(key);
		
		assertTrue(rbt.getRoot().getKey()==6);
		
		assertTrue(rbt.getRoot().getLeft().getKey()==4);
		assertTrue(rbt.getRoot().getLeft().getColor()=='B');
		assertTrue(rbt.getRoot().getRight().getKey()==8);
		assertTrue(rbt.getRoot().getRight().getColor()=='B');
		
		
	}
	
	@Test
	public void testInorderTraversal1() {
		setupScenary1();
		assertTrue(rbt.inorderTraversal().isEmpty());
	}
	
	@Test
	public void testInorderTraversal2() {
		setupScenary2();
		ArrayList<NodeRBT<Double,Player>> nodes =rbt.inorderTraversal();
		assertEquals(nodes.get(0).getKey(), 4.0);
		assertEquals(nodes.get(1).getKey(), 6.0);
		assertEquals(nodes.get(2).getKey(), 8.0);
		assertEquals(nodes.get(3).getKey(), 10.0);

	}
	
	@Test
	public void testInorderTraversal3() {
		setupScenary3();
		ArrayList<NodeRBT<Double,Player>> nodes =rbt.inorderTraversal();
		assertEquals(nodes.get(0).getKey(), 2.0);
		assertEquals(nodes.get(1).getKey(), 4.0);
		assertEquals(nodes.get(2).getKey(), 6.0);
		assertEquals(nodes.get(3).getKey(), 8.0);

	}

}
