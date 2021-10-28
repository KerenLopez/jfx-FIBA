package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

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

}
