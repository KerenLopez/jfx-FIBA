package model;

import java.util.ArrayList;

import exceptions.NegativeValueException;

public class Fiba {

	public ArrayList<Player> getPlayers() {
		return null;
	}

	public void addPlayer(String n, String ag, String t, String p, String bo, String a, String st, String bl) {
		String name = n;
		Integer age = Integer.parseInt(ag);
		String team = t;
		Integer points = Integer.parseInt(t);
		Integer bounces = Integer.parseInt(bo);
		Integer assists = Integer.parseInt(a);
		Integer steals = Integer.parseInt(st);
		Integer blocks = Integer.parseInt(bl);
		boolean founded = searchPlayer(name, age, team, points, bounces, assists, steals, blocks);
	}

}
