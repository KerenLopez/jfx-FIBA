package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dataStructures.BSTNode;
import dataStructures.BSTtree;
import dataStructures.NodeAVL;
import dataStructures.AVLTree;
import dataStructures.NodeRBT;
import dataStructures.RedBlackTree;
import exceptions.NegativeValueException;

public class Fiba implements Serializable {

	private static final long serialVersionUID = 1;

	private BSTtree<Double, Player> ABBofPointsByGame;
	private BSTtree<Double, Player> ABBofAssists;
	private ArrayList<Player> playersByBounces;
	private RedBlackTree<Double, Player> rbtSteals;
	private AVLTree<Double, Player> AVLPointsByGame;
	private AVLTree<Double, Player> AVLAssists;
	private AVLTree<Double, Player> AVLBlocksByGame;

	public final static String FIBA_SAVE_PATH_FILE="data/fiba.ackldo";

	public Fiba() {
		ABBofPointsByGame = new BSTtree<Double, Player>(); 
		ABBofAssists = new BSTtree<Double, Player>();
		playersByBounces = new ArrayList<Player>();
		rbtSteals = new RedBlackTree<Double, Player>();
		AVLPointsByGame=new AVLTree<Double, Player>(); 
		AVLAssists =new AVLTree<Double, Player>(); 
		AVLBlocksByGame=new AVLTree<Double, Player>(); 
	}

	public boolean addPlayer(String n, String ag, String t, String p, String bo, String a, String st, String bl) throws NegativeValueException, IOException{
		boolean added = false;
		Integer age = Integer.parseInt(ag);
		Double points = Double.parseDouble(p);
		Double bounces = Double.parseDouble(bo);
		Double assists = Double.parseDouble(a);
		Double steals = Double.parseDouble(st);
		Double blocks = Double.parseDouble(bl);
		boolean correct = true;
		if(age<0) {
			correct = false;
			throw new NegativeValueException(age);
		}
		if(points<0) {
			correct = false;
			throw new NegativeValueException(points);
		}
		if(bounces<0) {
			correct = false;
			throw new NegativeValueException(bounces);
		}
		if(assists<0) {
			correct = false;
			throw new NegativeValueException(assists);
		}
		if(steals<0) {
			correct = false;
			throw new NegativeValueException(steals);
		}
		if(blocks<0) {
			correct = false;
			throw new NegativeValueException(blocks);
		}
		if(correct) {
			Player player = new Player(n, age, t, points, bounces, assists, steals, blocks);
			ABBofPointsByGame.insertNode(player.getPoints(), player);
			ABBofAssists.insertNode(player.getAssists(), player);
                        AVLPointsByGame.insert(player.getPoints(), player);
                        AVLAssists.insert(player.getAssists(), player);
                        AVLBlocksByGame.insert(player.getBlocks(), player);
			rbtSteals.insert(player.getSteals(), player);
			playersByBounces.add(player);
			sortPlayers();
			added=true;
		}
		saveDataFIBA();
		return added;
	}

	public void deletePlayer(Player player) throws IOException {
		deletePlayerABBOfPointsByGame(player);
		deletePlayerABBOfAssists(player);
		playersByBounces.remove(player);
                deletePlayerAVLTreePoints(player);
                deletePlayerAVLTreeAssists(player);
                deletePlayerAVLTreeBlocks(player);
		deletePlayerRedBlackTree(player);
		saveDataFIBA();
	}


	private void deletePlayerABBOfPointsByGame(Player player) {
		boolean deleted = false;
		BSTNode<Double,Player> foundedPP = ABBofPointsByGame.searchNode(player.getPoints());
		if(foundedPP.getValue()==player) {
			ABBofPointsByGame.deleteNode(player.getPoints());
			deleted = true;
		}else {
			for(int i=0;i<foundedPP.getSameKeyNodes().size() && !deleted;i++) {
				if(foundedPP.getSameKeyNodes().get(i).getValue()==player) {
					foundedPP.getSameKeyNodes().remove(i);
					deleted=true;
				}
			}
		}
	}

	private void deletePlayerABBOfAssists(Player player) {
		boolean deleted = false;
		BSTNode<Double,Player> foundedPA = ABBofAssists.searchNode(player.getAssists());
		if(foundedPA.getValue()==player) {
			ABBofAssists.deleteNode(player.getAssists());
			deleted = true;
		}else {
			for(int i=0;i<foundedPA.getSameKeyNodes().size() && !deleted;i++) {
				if(foundedPA.getSameKeyNodes().get(i).getValue()==player) {
					foundedPA.getSameKeyNodes().remove(i);
					deleted=true;
				}
			}
		}
	}

	private void deletePlayerAVLTreePoints(Player player) {
		NodeAVL<Double,Player> foundedPP = AVLPointsByGame.search(player.getPoints());

		if(foundedPP.getValue()==player) {
			AVLPointsByGame.delete(player.getSteals());
		}
		else {
			boolean exit=false;
			for(int i=0;i<foundedPP.getSameKeyNodes().size() && !exit;i++) {
				if(foundedPP.getSameKeyNodes().get(i).getValue()==player) {
					foundedPP.getSameKeyNodes().remove(i);
					exit=true;
				}
			}
		}
	}
        
        private void deletePlayerAVLTreeAssists(Player player) {
            NodeAVL<Double,Player> foundedPA = AVLAssists.search(player.getAssists());
            
            if(foundedPA.getValue()==player) {
			AVLAssists.delete(player.getSteals());
		}
            else {
                    boolean exit=false;
                    for(int i=0;i<foundedPA.getSameKeyNodes().size() && !exit;i++) {
                            if(foundedPA.getSameKeyNodes().get(i).getValue()==player) {
                                    foundedPA.getSameKeyNodes().remove(i);
                                    exit=true;
                            }
                    }
            }
        }
        
        private void deletePlayerAVLTreeBlocks(Player player) {
            NodeAVL<Double,Player> foundedPB = AVLBlocksByGame.search(player.getPoints());
            
            if(foundedPB.getValue()==player) {
			AVLBlocksByGame.delete(player.getSteals());
		}
            else {
                    boolean exit=false;
                    for(int i=0;i<foundedPB.getSameKeyNodes().size() && !exit;i++) {
                            if(foundedPB.getSameKeyNodes().get(i).getValue()==player) {
                                    foundedPB.getSameKeyNodes().remove(i);
                                    exit=true;
                            }
                    }
            }
        }

	private void deletePlayerRedBlackTree(Player player) {
		NodeRBT<Double,Player> nodeSteal=rbtSteals.search(rbtSteals.getRoot(), player.getSteals());
		if(nodeSteal.getValue()==player) {
			rbtSteals.delete(player.getSteals());
		}else {
			boolean exit=false;
			for(int i=0;i<nodeSteal.getSameKeyNodes().size() && !exit;i++) {
				if(nodeSteal.getSameKeyNodes().get(i).getValue()==player) {
					nodeSteal.getSameKeyNodes().remove(i);
					exit=true;
				}
			}
		}
	}

	public boolean updatePlayer(Player py, String n, String ag, String t, String p, String bo, String a, String st, String bl) throws NegativeValueException, IOException {
		boolean updated=false;
		Integer age = Integer.parseInt(ag);
		Double points = Double.parseDouble(p);
		Double bounces = Double.parseDouble(bo);
		Double assists = Double.parseDouble(a);
		Double steals = Double.parseDouble(st);
		Double blocks = Double.parseDouble(bl);
		boolean correct = true;
		if(age<0) {
			correct = false;
			throw new NegativeValueException(age);
		}
		if(points<0) {
			correct = false;
			throw new NegativeValueException(points);
		}
		if(bounces<0) {
			correct = false;
			throw new NegativeValueException(bounces);
		}
		if(assists<0) {
			correct = false;
			throw new NegativeValueException(assists);
		}
		if(steals<0) {
			correct = false;
			throw new NegativeValueException(steals);
		}
		if(blocks<0) {
			correct = false;
			throw new NegativeValueException(blocks);
		}
		if(correct) {
			py.setName(n);
			py.setAge(age);
			py.setBlocks(blocks);
			py.setTeam(t);

			if(py.getAssists()!=assists) {
				deletePlayerABBOfAssists(py);
                                deletePlayerAVLTreeAssists(py);
				py.setAssists(assists);
				ABBofAssists.insertNode(py.getAssists(),py);
                                AVLAssists.insert(py.getAssists(), py);
			}

			if(py.getPoints()!=points) {
				deletePlayerABBOfPointsByGame(py); 
                                deletePlayerAVLTreePoints(py);
				py.setPoints(points);
				ABBofPointsByGame.insertNode(py.getPoints(), py);
                                AVLPointsByGame.insert(py.getAssists(), py);
			}

			if(py.getSteals()!=steals) {
				deletePlayerRedBlackTree(py);				
				py.setSteals(steals);
				rbtSteals.insert(py.getSteals(), py);
			}

			if(py.getBounces()!=bounces) {
				py.setBounces(bounces);
				sortPlayers();
			}
                        
                        if(py.getBlocks()!=blocks) {
				deletePlayerAVLTreeBlocks(py);				
				py.setBlocks(blocks);
				AVLBlocksByGame.insert(py.getBlocks(), py);
			}
			updated=true;	
		}
		saveDataFIBA();
		return updated;
	}


	public void sortPlayers() {

		Collections.sort(playersByBounces, new Comparator<Player>(){
			@Override
			public int compare(Player player1, Player player2) {
				if(player2.getBounces()>player1.getBounces()){
					return -1;
				}else if(player2.getBounces()==player1.getBounces()){
					return 0;
				}else{
					return 1;
				}
			}
		});
	}

	public ArrayList<Player> searchPlayersLinearly(String value, String comparison) throws NegativeValueException{
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		Double v = Double.parseDouble(value);
		if(v<0) {
			throw new NegativeValueException(v);
		}
		switch(comparison) {
		case "EQUAL":
			for(int k=0;k<playersByBounces.size();k++) {
				if(playersByBounces.get(k).getBounces()==v) {
					listOfPlayers.add(playersByBounces.get(k));
				}
			}
			break;
		case "GREATER":
			for(int k=0;k<playersByBounces.size();k++) {
				if(playersByBounces.get(k).getBounces()>v) {
					listOfPlayers.add(playersByBounces.get(k));
				}
			}
			break;
		case "LESS":
			for(int k=0;k<playersByBounces.size();k++) {
				if(playersByBounces.get(k).getBounces()<v) {
					listOfPlayers.add(playersByBounces.get(k));
				}
			}
			break;
		case "EQUALGREATER":
			for(int k=0;k<playersByBounces.size();k++) {
				if(playersByBounces.get(k).getBounces()>=v) {
					listOfPlayers.add(playersByBounces.get(k));
				}
			}
			break;
		case "EQUALLESS":
			for(int k=0;k<playersByBounces.size();k++) {
				if(playersByBounces.get(k).getBounces()<=v) {
					listOfPlayers.add(playersByBounces.get(k));
				}
			}
			break;	
		}	
		return listOfPlayers;
	}

	public ArrayList<Player> searchPlayersABB(String value, String criteria) throws NegativeValueException {
		ArrayList<Player> listOfPlayers=new ArrayList<Player>();
		Double v = Double.parseDouble(value);
		if(v<0) {
			throw new NegativeValueException(v);
		}
		switch(criteria) {
		case "POINTSEQUAL":
			BSTNode<Double, Player> foundedPP = ABBofPointsByGame.searchNode(v);
			searchEqualNodes(listOfPlayers, foundedPP, v);
			break;
		case "POINTSGREATER":
			searchGreaterNodes(listOfPlayers, ABBofPointsByGame.getRoot(), v);
			break;
		case "POINTSLESS":
			searchLessNodes(listOfPlayers, ABBofPointsByGame.getRoot(), v);
			break;
		case "POINTSEQUALGREATER":
			BSTNode<Double, Player> foundedPP1 = ABBofPointsByGame.searchNode(v);
			searchEqualNodes(listOfPlayers, foundedPP1, v);
			searchGreaterNodes(listOfPlayers, ABBofPointsByGame.getRoot(), v);
			break;
		case "POINTSEQUALLESS":
			BSTNode<Double, Player> foundedPP2 = ABBofPointsByGame.searchNode(v);
			searchEqualNodes(listOfPlayers, foundedPP2, v);
			searchLessNodes(listOfPlayers, ABBofPointsByGame.getRoot(), v);
			break;
		case "ASSISTSEQUAL":
			BSTNode<Double, Player> foundedPA = ABBofAssists.searchNode(v);
			searchEqualNodes(listOfPlayers, foundedPA, v);
			break;
		case "ASSISTSGREATER":
			searchGreaterNodes(listOfPlayers, ABBofAssists.getRoot(), v);
			break;
		case "ASSISTSLESS":
			searchLessNodes(listOfPlayers, ABBofAssists.getRoot(), v);
			break;
		case "ASSISTSEQUALGREATER":
			BSTNode<Double, Player> foundedPA1 = ABBofAssists.searchNode(v);
			searchEqualNodes(listOfPlayers, foundedPA1, v);
			searchGreaterNodes(listOfPlayers, ABBofAssists.getRoot(), v);
			break;
		case "ASSISTSEQUALLESS":
			BSTNode<Double, Player> foundedPA2 = ABBofAssists.searchNode(v);
			searchEqualNodes(listOfPlayers, foundedPA2, v);
			searchLessNodes(listOfPlayers, ABBofAssists.getRoot(), v);
			break;	
		}
		return listOfPlayers;
	}

	private ArrayList<Player> searchEqualNodes(ArrayList<Player> list, BSTNode<Double, Player> node, double v) {
		if(node!=null) {
			list.add(node.getValue());
			while(node.getLeft().getKey()==v) {
				list.add(node.getValue());
				node = node.getLeft();
			}	
		}
		return list;
	}

	private ArrayList<Player> searchGreaterNodes(ArrayList<Player> list, BSTNode<Double,Player> node, double key) {
		if(node==null) {
			return list;
		}else if(node.compareTo(key)<0){
			list.add(node.getValue());
			return list;
		}else if(node.getLeft()!=null && node.getLeft().compareTo(key)<0){
			list.add(node.getLeft().getValue());
			return list;
		}else {
			return searchGreaterNodes(list, node.getRight(), key);
		}
	}

	private ArrayList<Player> searchLessNodes(ArrayList<Player> list, BSTNode<Double,Player> node, double key) {
		if(node==null) {
			return list;
		}else if(node.compareTo(key)>0){
			list.add(node.getValue());
			return list;
		}else if(node.getRight()!=null && node.getRight().compareTo(key)>0){
			list.add(node.getRight().getValue());
			return list;
		}else {
			return searchGreaterNodes(list, node.getLeft(), key);
		}
	}

	public void saveDataFIBA() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FIBA_SAVE_PATH_FILE));
		oos.writeObject(this);
		oos.close();
	}

	public Fiba loadDataFIBA(Fiba fiba) throws IOException, ClassNotFoundException{
		File f = new File(FIBA_SAVE_PATH_FILE);
		if(f.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			fiba = (Fiba)ois.readObject();
			ois.close();
		}
		return fiba;
	}

	public ArrayList<Player> searchPlayersAVL(String value, String comparison) {
		ArrayList<Player> list=new ArrayList<>();
		Double v = Double.parseDouble(value);
		NodeAVL<Double,Player> n= new NodeAVL<>(v,null);
		switch(comparison) {
		case "POINTSEQUAL":
			NodeAVL<Double, Player> foundedPP = AVLPointsByGame.search(v);
			searchEqualAVL(list, foundedPP);
			break;
		case "POINTSGREATER":
			searchGreaterAVL(list, AVLPointsByGame.getRoot(), n);
			break;
		case "POINTSLESS":
			searchLessAVL(list, AVLPointsByGame.getRoot(), n);
			break;
		case "POINTSEQUALGREATER":
			searchEqualGreaterAVL(list, AVLPointsByGame.getRoot(), n);
			break;
		case "POINTSEQUALLESS":
			searchEqualLessAVL(list, AVLPointsByGame.getRoot(), n);
			break;
		case "ASSISTSEQUAL":
			NodeAVL<Double, Player> foundedA = AVLAssists.search(v);
			searchEqualAVL(list, foundedA);
			break;
		case "ASSISTSGREATER":
			searchGreaterAVL(list, AVLAssists.getRoot(), n);
			break;
		case "ASSISTSLESS":
			searchLessAVL(list, AVLAssists.getRoot(), n);
			break;
		case "ASSISTSEQUALGREATER":
			searchEqualGreaterAVL(list, AVLAssists.getRoot(), n);
			break;
		case "ASSISTSEQUALLESS":
			searchEqualLessAVL(list, AVLAssists.getRoot(), n);
			break;	
		case "BLOCKSEQUAL":
			NodeAVL<Double, Player> foundedB = AVLBlocksByGame.search(v);
			searchEqualAVL(list, foundedB);
			break;
		case "BLOCKSGREATER":
			searchGreaterAVL(list, AVLBlocksByGame.getRoot(), n);
			break;
		case "BLOCKSLESS":
			searchLessAVL(list, AVLBlocksByGame.getRoot(), n);
			break;
		case "BLOCKSEQUALGREATER":
			searchEqualGreaterAVL(list, AVLBlocksByGame.getRoot(), n);
			break;
		case "BLOCKSEQUALLESS":
			searchEqualLessAVL(list, AVLBlocksByGame.getRoot(), n);
			break;
		}
		return list;
	}

	private void searchEqualAVL(ArrayList<Player> list, NodeAVL<Double,Player> founded) {
		list.add(founded.getValue());
		for(int i=0;i<founded.getSameKeyNodes().size();i++) {
			list.add(founded.getSameKeyNodes().get(i).getValue());
		}
	}

	private void searchLessAVL(ArrayList<Player> list, NodeAVL<Double,Player> node, NodeAVL<Double,Player> nSearched) {
		while(node.getKey()!=null) {
			if(node.getKey().compareTo(nSearched.getKey())<0) {
				inOrderAVLTree(node.getLeft(), list);
				list.add(node.getValue());
				for(int i=0;i<node.getSameKeyNodes().size();i++) {
					list.add(node.getSameKeyNodes().get(i).getValue());
				}
				node=node.getRight();
			}
			else {
				node=node.getLeft();
			}
		}
	}

	private void searchEqualLessAVL(ArrayList<Player> list, NodeAVL<Double,Player> node, NodeAVL<Double,Player> nSearched) {

		while(node.getKey()!=null) {
			if( node.getKey().compareTo(nSearched.getKey())<=0) {

				inOrderAVLTree(node.getLeft(), list);

				list.add(node.getValue());
				for(int i=0;i<node.getSameKeyNodes().size();i++) {
					list.add(node.getSameKeyNodes().get(i).getValue());
				}

				node=node.getRight();
			}else {
				node=node.getLeft();
			}
		}

	}

	private void searchGreaterAVL(ArrayList<Player> list, NodeAVL<Double,Player> node, NodeAVL<Double,Player> nSearched) {

		while(node.getKey()!=null) {
			if( node.getKey().compareTo(nSearched.getKey())>0) {

				list.add(node.getValue());
				for(int i=0;i<node.getSameKeyNodes().size();i++) {
					list.add(node.getSameKeyNodes().get(i).getValue());
				}
				inOrderAVLTree(node.getRight(), list);

				node=node.getLeft();
			}else {
				node=node.getRight();
			}
		}

	}

	private void searchEqualGreaterAVL(ArrayList<Player> list, NodeAVL<Double,Player> node, NodeAVL<Double,Player> nSearched) {

		while(node.getKey()!=null) {
			if(node.getKey().compareTo(nSearched.getKey())>=0 ) {
				list.add(node.getValue());
				for(int i=0;i<node.getSameKeyNodes().size();i++) {
					list.add(node.getSameKeyNodes().get(i).getValue());
				}

				inOrderAVLTree(node.getRight(), list);

				node=node.getLeft();
			}else {
				node=node.getRight();
			}
		}

	}

	public ArrayList<Player> searchPlayersRedBlackTree(String value, String comparison) throws NegativeValueException {
		ArrayList<Player> list=new ArrayList<Player>();
		Double v = Double.parseDouble(value);
		if(v<0) {
			throw new NegativeValueException(v);
		}
		NodeRBT<Double,Player> n= new NodeRBT<>(v,null);
		switch(comparison) {
		case "STEALSEQUAL":
			NodeRBT<Double, Player> founded = rbtSteals.search(rbtSteals.getRoot(),v);
			searchEqualSteals(list, founded);
			break;
		case "STEALSGREATER":
			searchGreaterSteals(list, rbtSteals.getRoot(),n);
			break;
		case "STEALSLESS":

			searchLessSteals(list, rbtSteals.getRoot(),n);
			break;
		case "STEALSEQUALGREATER":

			searchEqualGreaterSteals(list, rbtSteals.getRoot(),n);
			break;
		case "STEALSEQUALLESS":

			searchEqualLessSteals(list, rbtSteals.getRoot(),n);
			break;
		}
		return list;

	}

	private void searchEqualSteals(ArrayList<Player> list, NodeRBT<Double,Player> founded) {
		list.add(founded.getValue());

		for(int i=0;i<founded.getSameKeyNodes().size();i++) {
			list.add(founded.getSameKeyNodes().get(i).getValue());
		}
	}

	private void searchLessSteals(ArrayList<Player> list, NodeRBT<Double,Player> node, NodeRBT<Double,Player> nSearched) {

		while(node!=rbtSteals.getNil()) {
			if( node.compareTo(nSearched)<0) {

				inorderRedBlackTree(node.getLeft(), list);

				list.add(node.getValue());
				for(int i=0;i<node.getSameKeyNodes().size();i++) {
					list.add(node.getSameKeyNodes().get(i).getValue());
				}

				node=node.getRight();
			}else {
				node=node.getLeft();
			}
		}

	}

	private void searchEqualLessSteals(ArrayList<Player> list, NodeRBT<Double,Player> node, NodeRBT<Double,Player> nSearched) {

		while(node!=rbtSteals.getNil()) {
			if( node.compareTo(nSearched)<=0) {

				inorderRedBlackTree(node.getLeft(), list);

				list.add(node.getValue());
				for(int i=0;i<node.getSameKeyNodes().size();i++) {
					list.add(node.getSameKeyNodes().get(i).getValue());
				}

				node=node.getRight();
			}else {
				node=node.getLeft();
			}
		}

	}

	private void searchGreaterSteals(ArrayList<Player> list, NodeRBT<Double,Player> node, NodeRBT<Double,Player> nSearched) {

		while(node!=rbtSteals.getNil()) {
			if( node.compareTo(nSearched)>0) {
				
				list.add(node.getValue());
				for(int i=0;i<node.getSameKeyNodes().size();i++) {
					list.add(node.getSameKeyNodes().get(i).getValue());
				}
				inorderRedBlackTree(node.getRight(), list);

				node=node.getLeft();
			}else {
				node=node.getRight();
			}
		}

	}

	private void searchEqualGreaterSteals(ArrayList<Player> list, NodeRBT<Double,Player> node, NodeRBT<Double,Player> nSearched) {

		while(node!=rbtSteals.getNil()) {
			if(node.compareTo(nSearched)>=0 ) {
				list.add(node.getValue());
				for(int i=0;i<node.getSameKeyNodes().size();i++) {
					list.add(node.getSameKeyNodes().get(i).getValue());
				}

				inorderRedBlackTree(node.getRight(), list);

				node=node.getLeft();
			}else {
				node=node.getRight();
			}
		}

	}

	private void inorderRedBlackTree(NodeRBT<Double,Player> node, ArrayList<Player> players) {
		if(node!=rbtSteals.getNil()) {
			inorderRedBlackTree(node.getLeft(), players);
			players.add(node.getValue());
			for(int i=0;i<node.getSameKeyNodes().size();i++) {
				players.add(node.getSameKeyNodes().get(i).getValue());
			}
			inorderRedBlackTree(node.getRight(), players);
		}
	}

	private void inOrderAVLTree(NodeAVL<Double,Player> node, ArrayList<Player> players) {
		if(node!=null) {
			inOrderAVLTree(node.getLeft(), players);
			players.add(node.getValue());
			for(int i=0;i<node.getSameKeyNodes().size();i++) {
				players.add(node.getSameKeyNodes().get(i).getValue());
			}
			inOrderAVLTree(node.getRight(), players);
		}
	}

	public AVLTree<Double, Player> getAVLPointsByGame() {
		return AVLPointsByGame;
	}

	public AVLTree<Double, Player> getAVLAssists() {
		return AVLAssists;
	}

	public AVLTree<Double, Player> getAVLBlocksByGame() {
		return AVLBlocksByGame;
	}

	public RedBlackTree<Double, Player> getRbtSteals() {
		return rbtSteals;
	}

	public void setRbtSteals(RedBlackTree<Double, Player> rbtSteals) {
		this.rbtSteals = rbtSteals;
	}

	public ArrayList<Player> getPlayersByBounces() {
		return playersByBounces;
	}

	public BSTtree<Double, Player> getABBofPointsByGame() {
		return ABBofPointsByGame;
	}

	public BSTtree<Double, Player> getABBofAssists() {
		return ABBofAssists;
	}

}
