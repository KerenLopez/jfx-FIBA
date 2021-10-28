package model;

public class Player {
	
	private String name;
	private int age;
	private String team;
	private double points;
	private double bounces;
	private double assists;
	private double steals;
	private double blocks;
	
	public Player( String name, int age, String team, double points, double bounces, double assists, double steals, double blocks) {
		this.name = name;
		this.age = age;
		this.team = team;
		this.points = points;
		this.bounces = bounces;
		this.assists = assists;
		this.steals = steals;
		this.blocks = blocks;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getTeam() {
		return team;
	}

	public double getPodoubles() {
		return points;
	}

	public double getBounces() {
		return bounces;
	}

	public double getAssists() {
		return assists;
	}

	public double getSteals() {
		return steals;
	}

	public double getBlocks() {
		return blocks;
	}
}
