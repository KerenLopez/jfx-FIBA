
package dataStructures;

import model.Player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AVLTreeTest {
    
    private AVLTree<Double, Player> treeAVL;
    
    public void setupScenary1() {
        treeAVL =new AVLTree<>();
    }
    
    public void setupScenary2() {
        //PUNTOS
        treeAVL =new AVLTree<>();
        
        treeAVL.insert(4.0, new Player("Ray Allen", 30, "Conferencia Este", 4.0, 6.0, 9.0, 30.0, 5.0));
        treeAVL.insert(8.0, new Player("Paul Arizin", 28, "Toronto Raptors", 8.0, 4.0, 20.0, 70.0, 9.0));
        treeAVL.insert(6.0, new Player("Devin Booker", 24, "Phoenix Suns", 6.0, 10.0, 17.0, 50.0, 8.0));
        treeAVL.insert(10.0, new Player("Charles Barkley", 31, "Miami Heat", 10.0, 16.0, 6.0, 20.0, 7.0));
    }
    
    public void setupScenary3() {
        //ASISTENCIAS
        treeAVL= new AVLTree<>();

        treeAVL.insert(8.0, new Player("Paul Arizin", 28, "Toronto Raptors", 70, 4, 20, 8, 9));
        treeAVL.insert(4.0, new Player("Ray Allen", 30, "Conferencia Este", 30, 6, 9, 4, 5));
        treeAVL.insert(6.0, new Player("Devin Booker", 24, "Phoenix Suns", 50, 10, 17, 6, 8));
        treeAVL.insert(2.0, new Player("Charles Barkley", 31, "Miami Heat", 20, 16, 6, 2, 7));
    }
    
    public void setupScenary4() {
        //BLOQUEOS
        treeAVL =new AVLTree<>();
        
        treeAVL.insert(20.0, new Player("Charles Barkley", 31, "Miami Heat", 20.0,16.0,9.0,3.0,7.0));
        treeAVL.insert(40.0, new Player("Rick Barry", 26, "Indiana Pacers", 40.0,2.0,5.0,18.0,9.0));
        treeAVL.insert(60.0, new Player("Dave Bing", 27, "Brooklyn Nets", 60.0,13.0,8.0,2.0,1.0));
        treeAVL.insert(80.0, new Player("Larry Bird", 28, "Chicago Bulls", 80.0,2.0,19.0,17.0,5.0));
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
    }
    
    
}
