package model;

import java.util.ArrayList;

import dataStructures.BSTtree;
import exceptions.NegativeValueException;

public class Fiba {

	private ArrayList<Player> players;
	//private ArrayList<Player> playersByBounces;
	private BSTtree<Double, Player> ABBofPointsByGame;
	private BSTtree<Double, Player> ABBofAssists;

	public Fiba() {
		players = new ArrayList<Player>();
		//playersByBounces=new ArrayList<Player>();
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
		Player founded = searchPlayer(n);
		if(correct && founded==null) {
			Player player = new Player(n, age, t, points, bounces, assists, steals, blocks);
			players.add(player);
			ABBofPointsByGame.insertNode(player.getPoints(), player);
			ABBofAssists.insertNode(player.getPoints(), player);
			added=true;
		}
		return added;
	}

	private Player searchPlayer(String name) {
		Player p = null;
		for(int k=0;k<players.size();k++) {
			if(players.get(k).getName().equalsIgnoreCase(name)) {
				p = players.get(k);
			}
		}
		return p;
	}

	public void deletePlayer(Player player) {
		ABBofPointsByGame.deleteNode(player.getPoints());
		ABBofAssists.deleteNode(player.getAssists());
		int i = players.indexOf(player);
		players.remove(i);
	}
	
	public boolean updatePlayer(Player py, String n, String ag, String t, String p, String bo, String a, String st, String bl) throws NegativeValueException {
		Player player = searchPlayer(n);
		boolean updated=false;
		boolean findPlayer = false;
		if(py!= player) {
			if(player!=null) {
				findPlayer =true;
			}
		}
		if(!findPlayer) {
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
				py.setAssists(assists);
				py.setBlocks(blocks);
				py.setBounces(bounces);
				py.setPoints(points);
				py.setSteals(steals);
				py.setTeam(t);
				updated=true;	
			}
		}
		return updated;
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

	public ArrayList<Player> getPlayers() {
		return players;
	}

}
