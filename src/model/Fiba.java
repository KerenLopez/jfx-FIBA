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
import dataStructures.NodeRBT;
import dataStructures.RedBlackTree;
import exceptions.NegativeValueException;

public class Fiba implements Serializable {

	private static final long serialVersionUID = 1;

	private BSTtree<Double, Player> ABBofPointsByGame;
	private BSTtree<Double, Player> ABBofAssists;
	private ArrayList<Player> playersByBounces;
	private RedBlackTree<Double, Player> rbtSteals;

	public final static String FIBA_SAVE_PATH_FILE="data/fiba.ackldo";

	public Fiba() {
		ABBofPointsByGame = new BSTtree<Double, Player>(); 
		ABBofAssists = new BSTtree<Double, Player>();
		playersByBounces = new ArrayList<Player>();
		rbtSteals = new RedBlackTree<Double, Player>();
	}

	public boolean addPlayer(String n, String ag, String t, String p, String bo, String a, String st, String bl) throws NegativeValueException{
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
			rbtSteals.insert(player.getSteals(), player);
			playersByBounces.add(player);
			sortPlayers();
			added=true;
		}
		return added;
	}

	public void deletePlayer(Player player) {
		BSTNode<Double,Player> foundedPP = ABBofPointsByGame.searchNode(player.getPoints());
		BSTNode<Double,Player> foundedPA = ABBofAssists.searchNode(player.getAssists());
		boolean deleted = false;
		if(foundedPP.getValue()==player) {
			rbtSteals.delete(player.getSteals());
			deleted = true;
		}else {
			for(int i=0;i<foundedPP.getSameKeyNodes().size() && !deleted;i++) {
				if(foundedPP.getSameKeyNodes().get(i).getValue()==player) {
					foundedPP.getSameKeyNodes().remove(i);
					deleted=true;
				}
			}
		}
		deleted = false;
		if(foundedPA.getValue()==player) {
			rbtSteals.delete(player.getSteals());
			deleted = true;
		}else {
			for(int i=0;i<foundedPA.getSameKeyNodes().size() && !deleted;i++) {
				if(foundedPA.getSameKeyNodes().get(i).getValue()==player) {
					foundedPA.getSameKeyNodes().remove(i);
					deleted=true;
				}
			}
		}
		ABBofPointsByGame.deleteNodeRecursive(foundedPP);
		ABBofAssists.deleteNodeRecursive(foundedPA);
		playersByBounces.remove(player);

		deletePlayerRedBlackTree(player);
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

	public boolean updatePlayer(Player py, String n, String ag, String t, String p, String bo, String a, String st, String bl) throws NegativeValueException {
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
				BSTNode<Double,Player> foundedPA = ABBofAssists.searchNode(py.getAssists());
				py.setAssists(assists);
				ABBofAssists.deleteNodeRecursive(foundedPA);
				ABBofAssists.insertNode(py.getAssists(),py);
			}
			
			if(py.getPoints()!=points) {
				BSTNode<Double,Player> foundedPP = ABBofPointsByGame.searchNode(py.getPoints());
				py.setPoints(points);
				ABBofPointsByGame.deleteNodeRecursive(foundedPP);
				ABBofPointsByGame.insertNode(py.getPoints(), py);
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
			
			updated=true;	
		}
		return updated;
	}


	public void sortPlayers() {

		Collections.sort(playersByBounces, new Comparator<Player>(){
			@Override
			public int compare(Player player1, Player player2) {
				if(player1.getBounces()>player2.getBounces()){
					return -1;
				}else if(player1.getBounces()>player2.getBounces()){
					return 0;
				}else{
					return 1;
				}
			}
		});
	}

	public ArrayList<Player> searchPlayersLinearly(String value, String comparison){
		ArrayList<Player> listOfPlayers=new ArrayList<>();
		Double v = Double.parseDouble(value);
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

	public ArrayList<Player> searchPlayersABB(String value, String criteria) {
		ArrayList<Player> listOfPlayers=new ArrayList<Player>();
		Double v = Double.parseDouble(value);
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

	public void searchPlayersAVL(String value, String comparison) {
		// TODO Auto-generated method stub

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

	public ArrayList<Player> searchPlayersRedBlackTree(String value, String comparison) {
		ArrayList<Player> list=new ArrayList<Player>();
		Double v = Double.parseDouble(value);
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
