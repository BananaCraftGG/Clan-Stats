package de.brownie.clanstats;

import de.brownie.clanstats.config.Config;
import de.brownie.clanstats.games.bedwars.connection.BedWarsConnection;
import de.brownie.clanstats.games.bedwars.stats.BedwarsStats;
import de.brownie.clanstats.games.cookieclicker.connection.CookieClickerConnection;
import de.brownie.clanstats.games.cookieclicker.stats.CookieClickerStats;
import de.brownie.clanstats.games.kbffa.connection.KBFFAConnection;
import de.brownie.clanstats.games.kbffa.stats.KBFFAStats;
import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.clan.commands.subcommands.Stats;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

public class Main extends PAFExtension {
	private ConfigurationCreator bedwarsConfig;
	private ConfigurationCreator kbffaConfig;

	private ConfigurationCreator cookieClickerConfig;

	public void onEnable() {
		try {
			this.bedwarsConfig = new Config(new File(getDataFolder(), "bedwars.yml"), this);
			this.kbffaConfig = new Config(new File(getDataFolder(), "kbffa.yml"), this);
			this.cookieClickerConfig = new Config(new File(getDataFolder(), "cookieClicker.yml"), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BedWarsConnection bedwarsConnection = new BedWarsConnection(this.bedwarsConfig.getString("database.db"), "jdbc:mysql://" + this.bedwarsConfig.getString("database.host") + ":" + this.bedwarsConfig.getInt("database.port"), this.bedwarsConfig.getString("database.user"), this.bedwarsConfig.getString("database.password"), bedwarsConfig.getBoolean("database.ssl"));
		((Stats) ClanCommands.getInstance().getSubCommand(Stats.class)).registerClanStats(new BedwarsStats(bedwarsConnection), this);

		KBFFAConnection kbffaConnection = new KBFFAConnection(this.kbffaConfig.getString("database.db"), "jdbc:mysql://" + this.kbffaConfig.getString("database.host") + ":" + this.kbffaConfig.getInt("database.port"), this.kbffaConfig.getString("database.user"), this.kbffaConfig.getString("database.password"), kbffaConfig.getBoolean("database.ssl"));
		((Stats) ClanCommands.getInstance().getSubCommand(Stats.class)).registerClanStats(new KBFFAStats(kbffaConnection), this);

		CookieClickerConnection cookieClickerConnection = new CookieClickerConnection(this.cookieClickerConfig.getString("database.db"), "jdbc:mysql://" + this.cookieClickerConfig.getString("database.host") + ":" + this.cookieClickerConfig.getInt("database.port"), this.cookieClickerConfig.getString("database.user"), this.cookieClickerConfig.getString("database.password"), cookieClickerConfig.getBoolean("database.ssl"));
		((Stats) ClanCommands.getInstance().getSubCommand(Stats.class)).registerClanStats(new CookieClickerStats(cookieClickerConnection), this);
	}
}