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

}
