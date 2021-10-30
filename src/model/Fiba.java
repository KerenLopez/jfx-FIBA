package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import dataStructures.BSTNode;
import dataStructures.BSTtree;
import exceptions.NegativeValueException;

public class Fiba {

	private Hashtable<Player, Player> players;
	private BSTtree<Double, Player> ABBofPointsByGame;
	private BSTtree<Double, Player> ABBofAssists;

	public Fiba() {
		players = new Hashtable<Player, Player>();
		ABBofPointsByGame = new BSTtree<Double, Player>(); 
		ABBofAssists = new BSTtree<Double, Player>();
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
			players.put(player, player);
			ABBofPointsByGame.insertNode(player.getPoints(), player);
			ABBofAssists.insertNode(player.getPoints(), player);
			added=true;
		}
		return added;
	}

	public void deletePlayer(Player player) {
		BSTNode<Double,Player> foundedPP = ABBofPointsByGame.searchNode(player.getPoints());
		BSTNode<Double,Player> foundedPA = ABBofAssists.searchNode(player.getAssists());
		boolean founded = false;
		if(foundedPP.getValue()!=player && foundedPA.getValue()!=player) {
			while(!founded) {
				foundedPP = foundedPP.getLeft();
				foundedPA = foundedPA.getLeft();
				Player points = foundedPP.getValue();
				Player assists = foundedPA.getValue();	
				if(points==player && assists==player) {
					founded = true;
				}
			}	
		}
		ABBofPointsByGame.deleteNodeRecursive(foundedPP);
		ABBofAssists.deleteNodeRecursive(foundedPA);
		players.remove(player, player);
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
			py.setBounces(bounces);
			py.setSteals(steals);
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
			updated=true;	
		}
		return updated;
	}

	public ArrayList<Player> returnPlayers(){
		ArrayList<Player> listOfPlayers=new ArrayList<Player>();
		Enumeration<Player> elems = players.elements();
		while (elems.hasMoreElements()) {
			listOfPlayers.add(elems.nextElement());
		}
		return listOfPlayers;

	}

	public void searchPlayersByPoints(String text, String comparison) {
		// TODO Auto-generated method stub
		
	}

	public void searchPlayersByAssists(String text, String comparison) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Player> returnPlayersFounded() {
		// TODO Auto-generated method stub
		return null;
	}

	/*public void importPlayersData(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while(line!=null){
			String[] parts = line.split(";");
			addPlayer(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],parts[6],parts[7]);
			line = br.readLine();
		}
		br.close();
	}*/

}
