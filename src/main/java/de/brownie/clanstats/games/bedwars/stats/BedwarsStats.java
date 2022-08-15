package de.brownie.clanstats.games.bedwars.stats;

import de.brownie.clanstats.games.bedwars.data.BedwarsPlayerData;
import de.brownie.clanstats.games.bedwars.connection.BedWarsConnection;
import de.brownie.clanstats.utils.ChatUtils;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanStat;

import java.util.ArrayList;
import java.util.List;

public class BedwarsStats implements ClanStat {
	private BedWarsConnection connection;

	public BedwarsStats(BedWarsConnection connection) {
		this.connection = connection;
	}

	public void stats(OnlinePAFPlayer pSender, Clan pClan) {
		List<PAFPlayer> players = pClan.getAllPlayers();
		List<BedwarsPlayerData> bedwarsPlayerData = new ArrayList();
		for (PAFPlayer player : players) {
			BedwarsPlayerData data = this.connection.getPlayerData(player.getUniqueId());
			if (data != null)
				bedwarsPlayerData.add(data);
		}
		int wins = 0;
		int deaths = 0;
		int losses = 0;
		int destroyedBeds = 0;
		int games = 0;
		int kills = 0;
		double kds = 0.00D;
		for (BedwarsPlayerData data : bedwarsPlayerData) {
			wins += data.wins;
			losses += data.losses;
			deaths += data.deaths;
			destroyedBeds += data.destroyedBeds;
			games += data.games;
			kds += data.kd;
			kills += data.kills;
		}
		kds /= bedwarsPlayerData.size();
		if (kds != kds)
			kds = 0.00D;
		ChatUtils.sendMessagePAF(pSender, String.format("&7The clan has played &a%s &7games.", games));
		ChatUtils.sendMessagePAF(pSender, String.format("&7The clan has won &a%s &7games.", wins));
		ChatUtils.sendMessagePAF(pSender, String.format("&7The clan has lost &a%s &7games.", losses));
		ChatUtils.sendMessagePAF(pSender, String.format("&7The clan has destroyed &a%s &7beds.", destroyedBeds));
		ChatUtils.sendMessagePAF(pSender, String.format("&7The members of the clan have killed &a%s &7people.", kills));
		ChatUtils.sendMessagePAF(pSender, String.format("&7The members of the clan have died &a%s &7times.", deaths));
		ChatUtils.sendMessagePAF(pSender, String.format("&7The average K/D of the clan is &a%s&7.", kds));
	}

	public String getName() {
		return "BedWars";
	}
}
