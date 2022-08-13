package de.brownie.clanstats.games.kbffa.data;


public class KBFFAPlayerData {
	public final double kd;
	public final int deaths;
	public final int kills;

	public KBFFAPlayerData(int kills, int deaths, double kd) {
		this.kills = kills;
		this.kd = kd;
		this.deaths = deaths;
	}
}