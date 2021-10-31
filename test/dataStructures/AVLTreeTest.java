
package dataStructures;

import model.Player;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class AVLTreeTest {
    
    private AVLTree<Double, Player> treeAVL;
    
    public void setupScenary1() {
        treeAVL =new AVLTree<>();
    }
    
    public void setupScenary2() {
        treeAVL =new AVLTree<>();
        
        treeAVL.insert(4.0, new Player("Ray Allen", 30, "Conferencia Este", 4.0, 6.0, 9.0, 30.0, 5.0));
        treeAVL.insert(8.0, new Player("Paul Arizin", 28, "Toronto Raptors", 8.0, 4.0, 20.0, 70.0, 9.0));
        treeAVL.insert(6.0, new Player("Devin Booker", 24, "Phoenix Suns", 6.0, 10.0, 17.0, 50.0, 8.0));
        treeAVL.insert(10.0, new Player("Charles Barkley", 31, "Miami Heat", 10.0, 16.0, 6.0, 20.0, 7.0));
    }
    
    public void setupScenary3() {
        treeAVL= new AVLTree<>();

        treeAVL.insert(8.0, new Player("Paul Arizin", 28, "Toronto Raptors", 70, 4, 8, 20, 9));
        treeAVL.insert(4.0, new Player("Ray Allen", 30, "Conferencia Este", 30, 6, 4, 9, 5));
        treeAVL.insert(6.0, new Player("Devin Booker", 24, "Phoenix Suns", 50, 10, 6, 17, 8));
        treeAVL.insert(2.0, new Player("Charles Barkley", 31, "Miami Heat", 20, 16, 2, 6, 7));
    }
    
    public void setupScenary4() {
        treeAVL =new AVLTree<>();
        
        treeAVL.insert(8.0, new Player("Paul Arizin", 28, "Toronto Raptors", 70, 4, 9, 20, 8));
        treeAVL.insert(4.0, new Player("Ray Allen", 30, "Conferencia Este", 30, 6, 5, 9, 4));
        treeAVL.insert(6.0, new Player("Devin Booker", 24, "Phoenix Suns", 50, 10, 8, 17, 6));
        treeAVL.insert(2.0, new Player("Charles Barkley", 31, "Miami Heat", 20, 16, 7, 6, 2));
    }
    
    @Test
    public void testInsertNode1() {
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

        treeAVL.insert(player.getPoints(), player);
        assertTrue(treeAVL.getRoot().getValue()==player);
        assertTrue(treeAVL.getRoot().getLeft()==null);
        assertTrue(treeAVL.getRoot().getRight()==null);
    }
    
    @Test
    public void testInsertNode2() {
        setupScenary2();
        
        String name = "Rick Barry";
        int age = 26;
        String team = "Indiana Pacers";
        double points = 9;
        double bounces = 2;
        double assists = 5;
        double steals = 40;
        double blocks = 9;

        Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

        treeAVL.insert(player.getPoints(), player);

        NodeAVL<Double, Player> node= treeAVL.search(9.0);
        assertTrue(node.getValue()==player);
        assertTrue(node.getParent().getKey()==6);
        assertTrue(node.getParent().getRight()==node);
        assertTrue(node.getLeft().getKey()==8);
        assertTrue(node.getRight().getKey()==10);
    }
    
    @Test
    public void testInsertNode3() {
        setupScenary2();

        String name = "Rick Barry";
        int age = 26;
        String team = "Indiana Pacers";
        double points = 11;
        double bounces = 2;
        double assists = 5;
        double steals = 40;
        double blocks = 9;

        Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

        treeAVL.insert(player.getPoints(), player);

        NodeAVL<Double, Player> node= treeAVL.search(11.0);
        assertTrue(node.getValue()==player);
        assertTrue(node.getParent().getKey()==10);
        assertTrue(node.getParent().getRight()==node);
        assertTrue(node.getParent().getLeft().getKey()==8);
        assertTrue(node.getLeft()==null);
        assertTrue(node.getRight()==null);
    }
    
    @Test
    public void testInsertNode4() {
        setupScenary3();
        
        String name = "Rick Barry";
        int age = 26;
        String team = "Indiana Pacers";
        double points = 40;
        double bounces = 2;
        double assists = 1;
        double steals = 5;
        double blocks = 9;

        Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

        treeAVL.insert(player.getAssists(), player);

        NodeAVL<Double, Player> node= treeAVL.search(1.0);
        assertTrue(node.getValue()==player);
        assertTrue(node.getParent().getKey()==2);
        assertTrue(node.getParent().getLeft()==node);
        assertTrue(node.getParent().getRight().getKey()==4);
        assertTrue(node.getLeft()==null);
        assertTrue(node.getRight()==null);
    }
    
    @Test
    public void testInsertNode5() {
        setupScenary3();

        String name = "Rick Barry";
        int age = 26;
        String team = "Indiana Pacers";
        double points = 40;
        double bounces = 2;
        double assists = 3;
        double steals = 5;
        double blocks = 9;

        Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

        treeAVL.insert(player.getAssists(), player);

        NodeAVL<Double, Player> node= treeAVL.search(3.0);
        assertTrue(node.getValue()==player);
        assertTrue(node.getParent().getKey()==6);
        assertTrue(node.getParent().getLeft()==node);
        assertTrue(node.getLeft().getKey()==2);
        assertTrue(node.getRight().getKey()==4);
    }
    
    @Test
    public void testInsertNode6() {
        setupScenary4();
        
        String name = "Rick Barry";
        int age = 26;
        String team = "Indiana Pacers";
        double points = 40;
        double bounces = 2;
        double assists = 5;
        double steals = 9;
        double blocks = 3;

        Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

        treeAVL.insert(player.getBlocks(), player);

        NodeAVL<Double, Player> node= treeAVL.search(3.0);
        assertTrue(node.getValue()==player);
        assertTrue(node.getParent().getKey()==6);
        assertTrue(node.getParent().getLeft()==node);
        assertTrue(node.getLeft().getKey()==2);
        assertTrue(node.getRight().getKey()==4);
    }
    
    @Test
    public void testInsertNode7() {
        setupScenary4();

        String name = "Rick Barry";
        int age = 26;
        String team = "Indiana Pacers";
        double points = 40;
        double bounces = 2;
        double assists = 5;
        double steals = 9;
        double blocks = 7;

        Player player= new Player(name, age, team, points, bounces, assists, steals, blocks);

        treeAVL.insert(player.getBlocks(), player);

        NodeAVL<Double, Player> node= treeAVL.search(7.0);
        assertTrue(node.getValue()==player);
        assertTrue(node.getParent().getKey()==8);
        assertTrue(node.getParent().getLeft()==node);
        assertTrue(node.getLeft()==null);
        assertTrue(node.getRight()==null);
    }
    
    @Test
    public void testSearch1() {
        setupScenary1();
        double key=7;
        NodeAVL<Double, Player> node= treeAVL.search(key);
        assertTrue(node==null);
    }
    
    @Test
    public void testSearch2() {
        setupScenary2();
        double key=10;

        NodeAVL<Double, Player> node= treeAVL.search(key);

        assertFalse(node==null);
        assertTrue(node.getValue().getName().equals("Charles Barkley"));
        assertTrue(node.getValue().getAge()==31);
        assertTrue(node.getValue().getTeam().equals("Miami Heat"));
        assertTrue(node.getValue().getPoints()==10);
        assertTrue(node.getValue().getBounces()==16);
        assertTrue(node.getValue().getAssists()==6);
        assertTrue(node.getValue().getSteals()==20);
        assertTrue(node.getValue().getBlocks()==7);
    }
    
    @Test
    public void testSearch3() {
        setupScenary3();
        double key=2;

        NodeAVL<Double, Player> node= treeAVL.search(key);

        assertFalse(node==null);
        assertTrue(node.getValue().getName().equals("Charles Barkley"));
        assertTrue(node.getValue().getAge()==31);
        assertTrue(node.getValue().getTeam().equals("Miami Heat"));
        assertTrue(node.getValue().getPoints()==20);
        assertTrue(node.getValue().getBounces()==16);
        assertTrue(node.getValue().getAssists()==2);
        assertTrue(node.getValue().getSteals()==6);
        assertTrue(node.getValue().getBlocks()==7);
    }
    
    @Test
    public void testSearch4() {
        setupScenary4();
        double key=2;

        NodeAVL<Double, Player> node= treeAVL.search(key);

        assertFalse(node==null);
        assertTrue(node.getValue().getName().equals("Charles Barkley"));
        assertTrue(node.getValue().getAge()==31);
        assertTrue(node.getValue().getTeam().equals("Miami Heat"));
        assertTrue(node.getValue().getPoints()==20);
        assertTrue(node.getValue().getBounces()==16);
        assertTrue(node.getValue().getAssists()==7);
        assertTrue(node.getValue().getSteals()==6);
        assertTrue(node.getValue().getBlocks()==2);
    }
    
    @Test
    public void testDelete1() {
        setupScenary2();
        double key=6;

        treeAVL.delete(key);

        assertTrue(treeAVL.getRoot().getKey()==8);
        assertTrue(treeAVL.getRoot().getLeft().getKey()==4);
        assertTrue(treeAVL.getRoot().getRight().getKey()==10);
    }
	
    @Test
    public void testDelete2() {
        setupScenary2();
        double key=8;

        treeAVL.delete(key);

        assertTrue(treeAVL.getRoot().getKey()==6);

        assertTrue(treeAVL.getRoot().getLeft().getKey()==4);
        assertTrue(treeAVL.getRoot().getRight().getKey()==10);
    }

    @Test
    public void testDelete3() {
        setupScenary3();
        double key=6;

        treeAVL.delete(key);

        assertTrue(treeAVL.getRoot().getKey()==4);

        assertTrue(treeAVL.getRoot().getLeft().getKey()==2);
        assertTrue(treeAVL.getRoot().getRight().getKey()==8);
    }

    @Test
    public void testDelete4() {
        setupScenary3();
        double key=4;

        treeAVL.delete(key);

        assertTrue(treeAVL.getRoot().getKey()==6);

        assertTrue(treeAVL.getRoot().getLeft().getKey()==2);
        assertTrue(treeAVL.getRoot().getRight().getKey()==8);

    }
    
    @Test
    public void testPreOrder1() {
        setupScenary1();
        assertTrue(treeAVL.preOrder().isEmpty());
    }

    @Test
    public void testPreOrder2() {
        setupScenary2();
        ArrayList<NodeAVL<Double, Player>> nodes =treeAVL.preOrder();
        assertEquals(nodes.get(0).getKey(), 6.0);
        assertEquals(nodes.get(1).getKey(), 4.0);
        assertEquals(nodes.get(2).getKey(), 8.0);
        assertEquals(nodes.get(3).getKey(), 10.0);
    }

    @Test
    public void testPreOrder3() {
        setupScenary3();
        ArrayList<NodeAVL<Double, Player>> nodes =treeAVL.preOrder();
        assertEquals(nodes.get(0).getKey(), 6.0);
        assertEquals(nodes.get(1).getKey(), 4.0);
        assertEquals(nodes.get(2).getKey(), 2.0);
        assertEquals(nodes.get(3).getKey(), 8.0);
    }
}
