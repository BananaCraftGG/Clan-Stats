package de.brownie.clanstats.games.bedwars.data;


public class BedwarsPlayerData {
	public final int wins;
	public final int losses;
	public final int games;
	public final int destroyedBeds;
	public final double kd;
	public final int deaths;
	public final int kills;

	public BedwarsPlayerData(int wins, int losses, int games, int destroyedBeds, double kd, int deaths, int kills) {
		this.wins = wins;
		this.losses = losses;
		this.games = games;
		this.destroyedBeds = destroyedBeds;
		this.kd = kd;
		this.deaths = deaths;
		this.kills = kills;
	}
}