package de.brownie.clanstats.games.cookieclicker.stats;

import de.brownie.clanstats.games.cookieclicker.connection.CookieClickerConnection;
import de.brownie.clanstats.games.cookieclicker.data.CookieClickerPlayerData;
import de.brownie.clanstats.utils.ChatUtils;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanStat;

import java.util.ArrayList;
import java.util.List;

public class CookieClickerStats implements ClanStat {
	private CookieClickerConnection connection;

	public CookieClickerStats(CookieClickerConnection connection) {
		this.connection = connection;
	}

	public void stats(OnlinePAFPlayer pSender, Clan pClan) {
		List<PAFPlayer> players = pClan.getAllPlayers();
		List<CookieClickerPlayerData> cookieClickerPlayerData = new ArrayList();
		for (PAFPlayer player : players) {
			CookieClickerPlayerData data = this.connection.getPlayerData(player.getUniqueId());
			if (data != null)
				cookieClickerPlayerData.add(data);
		}
		int cookies = 0;
		int average = 0;
		for (CookieClickerPlayerData data : cookieClickerPlayerData) {
			cookies += data.cookies;
			average = cookies / cookieClickerPlayerData.size();
		}

		ChatUtils.sendMessagePAF(pSender, String.format("&7The members of the clan have &a%s &7cookies.", cookies));
		ChatUtils.sendMessagePAF(pSender, String.format("&7The average amount of cookies of the clan are &a%s&7.", average));
	}

	public String getName() {
		return "CookieClicker";
	}
}
