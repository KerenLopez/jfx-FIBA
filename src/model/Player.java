package model;

public class Player {
	
	private String name;
	private Integer age;
	private String team;
	private Integer points;
	private Integer bounces;
	private Integer assists;
	private Integer steals;
	private Integer blocks;
	
	public Player( String name, int age, String team, int points, int bounces, int assists, int steals, int blocks) {
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

	public Integer getAge() {
		return age;
	}

	public String getTeam() {
		return team;
	}

	public Integer getPoints() {
		return points;
	}

	public Integer getBounces() {
		return bounces;
	}

	public Integer getAssists() {
		return assists;
	}

	public Integer getSteals() {
		return steals;
	}

	public Integer getBlocks() {
		return blocks;
	}
}
