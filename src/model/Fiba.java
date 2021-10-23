package model;

import java.util.ArrayList;

import exceptions.NegativeValueException;

public class Fiba {

	public ArrayList<Player> getPlayers() {
		return null;
	}

	public void addPlayer(String n, String ag, String t, String p, String bo, String a, String st, String bl) throws NegativeValueException{
		String name = n;
		Integer age = Integer.parseInt(ag);
		String team = t;
		Integer points = Integer.parseInt(t);
		Integer bounces = Integer.parseInt(bo);
		Integer assists = Integer.parseInt(a);
		Integer steals = Integer.parseInt(st);
		Integer blocks = Integer.parseInt(bl);
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
		boolean founded = searchPlayer(name, age, team, points, bounces, assists, steals, blocks);
		if(correct && founded==false) {
			Player p = new Player(name, age, team, points, bounces, assists, steals, blocks);
		}
	}

	public void importPlayersData(String fileName) throws IOException{
        	BufferedReader br = new BufferedReader(new FileReader(fileName));
        	String line = br.readLine();
        	while(line!=null){
            		String[] parts = line.split(";");
            		addPlayer(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],parts[6],parts[7]);
            		line = br.readLine();
        	}
        	br.close();
    	}

}
