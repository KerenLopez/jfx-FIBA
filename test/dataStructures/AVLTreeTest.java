
package dataStructures;

import model.Player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AVLTreeTest {
    
    private AVLTree<Double, Player> treeAVL;
    
    public void setupScenary1() {
        treeAVL =new AVLTree<Double, Player>();
    }
    
    public void setupScenary2() {
        treeAVL =new AVLTree<Double, Player>();
        
        treeAVL.insert(4.0, new Player("Ray Allen", 30, "Conferencia Este", 30, 6, 9, 4, 5));
		treeAVL.insert(8.0, new Player("Paul Arizin", 28, "Toronto Raptors", 70, 4, 20, 8, 9));
		treeAVL.insert(6.0, new Player("Devin Booker", 24, "Phoenix Suns", 50, 10, 17, 6, 8));
		treeAVL.insert(10.0, new Player("Charles Barkley", 31, "Miami Heat", 20, 16, 6, 10, 7));
    }
    
    public void setupScenary3() {
        treeAVL =new AVLTree<Double, Player>();
        
        treeAVL.insert(15.0, new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0));
        treeAVL.insert(12.0, new Player("Ray Allen", 30, "Conferencia Este", 30.0,6.0,12.0,10.0,5.0));
        treeAVL.insert(8.0, new Player("Paul Arizin", 28, "Toronto Raptors", 70.0,4.0,8.0,13.0,9.0));
        treeAVL.insert(9.0, new Player("Charles Barkley", 31, "Miami Heat", 20.0,16.0,9.0,3.0,7.0));
        treeAVL.insert(5.0, new Player("Rick Barry", 26, "Indiana Pacers", 40.0,2.0,5.0,18.0,9.0));
        treeAVL.insert(8.0, new Player("Dave Bing", 27, "Brooklyn Nets", 60.0,13.0,8.0,2.0,1.0));
        treeAVL.insert(19.0, new Player("Larry Bird", 28, "Chicago Bulls", 80.0,2.0,19.0,17.0,5.0));
    }
    
    public void setupScenary4() {
        treeAVL =new AVLTree<Double, Player>();
        
        treeAVL.insert(50.0, new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0));
        treeAVL.insert(30.0, new Player("Ray Allen", 30, "Conferencia Este", 30.0,6.0,12.0,10.0,5.0));
        treeAVL.insert(70.0, new Player("Paul Arizin", 28, "Toronto Raptors", 70.0,4.0,8.0,13.0,9.0));
        treeAVL.insert(20.0, new Player("Charles Barkley", 31, "Miami Heat", 20.0,16.0,9.0,3.0,7.0));
        treeAVL.insert(40.0, new Player("Rick Barry", 26, "Indiana Pacers", 40.0,2.0,5.0,18.0,9.0));
        treeAVL.insert(60.0, new Player("Dave Bing", 27, "Brooklyn Nets", 60.0,13.0,8.0,2.0,1.0));
        treeAVL.insert(80.0, new Player("Larry Bird", 28, "Chicago Bulls", 80.0,2.0,19.0,17.0,5.0));
    }
    
    @Test
    public void testInsertNode1() {
        setupScenary1();
        double key = 50.0;
        Player player1 = new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0); 
        treeAVL.insert(50.0, player1);
        assertEquals(treeAVL.getRoot().getKey(), key);
        assertEquals(treeAVL.getRoot().getValue().getName(), player1.getName());
    }
    
    @Test
    public void testInsertNode2() {
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

		treeAVL.insert(player.getSteals(), player);
		
		NodeAVL<Double, Player> node= treeAVL.search(9.0);
		assertTrue(node.getValue()==player);
		assertTrue(node.getParent().getKey()==6);
		assertTrue(node.getParent().getRight()==node);
		assertTrue(node.getLeft().getKey()==8);
		assertTrue(node.getRight().getKey()==10);
    }
    
    /*@Test
    public void testInsertNode3() {
            setupScenary2();
            double key = 90.0;
            Player player8 = new Player("Carl Braun", 23, "Orlando Magic", 90.0,10.0,15.0,6.0,8.0); 
            treeAVL.insert(key, player8);
            assertEquals(treeAVL.search(80.0).getRight().getKey(), key);
            assertEquals(treeAVL.search(80.0).getRight().getValue().getName(), player8.getName());
    }
    
    @Test
    public void testInsertNode4() {
		setupScenary2();
		double key = 40.0;
		Player player8 = new Player("Kobe Bryant", 32, "Houston Rockets", 40.0,10.0,15.0,6.0,8.0); 
		treeAVL.insert(key, player8);
		assertEquals(treeAVL.search(40.0).getLeft().getKey(), key);
		assertEquals(treeAVL.search(40.0).getLeft().getValue().getName(), player8.getName());
    }*/
    
    /*@Test
    public void testInsertNode3() {
        setupScenary1();
        double key = 50.0;
        Player player1 = new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0); 
        treeAVL.insert(50.0, player1);
        assertEquals(treeAVL.getRoot().getKey(), key);
        assertEquals(treeAVL.getRoot().getValue().getName(), player1.getName());
    }
    
    @Test
    public void testInsertNode4() {
        setupScenary1();
        double key = 50.0;
        Player player1 = new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0); 
        treeAVL.insert(50.0, player1);
        assertEquals(treeAVL.getRoot().getKey(), key);
        assertEquals(treeAVL.getRoot().getValue().getName(), player1.getName());
    }
    
    @Test
    public void testInsertNode5() {
        setupScenary1();
        double key = 50.0;
        Player player1 = new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0); 
        treeAVL.insert(50.0, player1);
        assertEquals(treeAVL.getRoot().getKey(), key);
        assertEquals(treeAVL.getRoot().getValue().getName(), player1.getName());
    }
    
    @Test
    public void testInsertNode6() {
        setupScenary1();
        double key = 50.0;
        Player player1 = new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0); 
        treeAVL.insert(50.0, player1);
        assertEquals(treeAVL.getRoot().getKey(), key);
        assertEquals(treeAVL.getRoot().getValue().getName(), player1.getName());
    }
    
    @Test
    public void testInsertNode7() {
        setupScenary1();
        double key = 50.0;
        Player player1 = new Player("Devin Booker", 24, "Phoenix Suns", 50.0,10.0,15.0,6.0,8.0); 
        treeAVL.insert(50.0, player1);
        assertEquals(treeAVL.getRoot().getKey(), key);
        assertEquals(treeAVL.getRoot().getValue().getName(), player1.getName());
    }
    
    @Test
    public void testSearchNode1() {
        setupScenary1();
        double key = 50.0;
        assertEquals(treeAVL.search(key), null);
    }
    
    @Test
    public void testDeleteNode1() {
        setupScenary1();
        double key = 50.0;
        assertFalse(treeAVL.deleted(key));
    }
    
    @Test
    public void testPreOrder1() {
            setupScenary1();
            assertTrue(treeAVL.preOrder().isEmpty());
    }*/
}
