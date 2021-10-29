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

	public double getPoints() {
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

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public void setBounces(double bounces) {
		this.bounces = bounces;
	}

	public void setAssists(double assists) {
		this.assists = assists;
	}

	public void setSteals(double steals) {
		this.steals = steals;
	}

	public void setBlocks(double blocks) {
		this.blocks = blocks;
	}
	
}
