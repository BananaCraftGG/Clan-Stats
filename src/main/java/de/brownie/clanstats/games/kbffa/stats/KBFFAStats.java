package de.brownie.clanstats.games.kbffa.stats;

import de.brownie.clanstats.games.kbffa.connection.KBFFAConnection;
import de.brownie.clanstats.games.kbffa.data.KBFFAPlayerData;
import de.brownie.clanstats.utils.ChatUtils;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanStat;

import java.util.ArrayList;
import java.util.List;

public class KBFFAStats implements ClanStat {
	private KBFFAConnection connection;

	public KBFFAStats(KBFFAConnection connection) {
		this.connection = connection;
	}

	public void stats(OnlinePAFPlayer pSender, Clan pClan) {
		List<PAFPlayer> players = pClan.getAllPlayers();
		List<KBFFAPlayerData> KBFFAPlayerData = new ArrayList();
		for (PAFPlayer player : players) {
			KBFFAPlayerData data = this.connection.getPlayerData(player.getUniqueId());
			if (data != null)
				KBFFAPlayerData.add(data);
		}
		int deaths = 0;
		int kills = 0;
		double kds = 0.00D;
		for (KBFFAPlayerData data : KBFFAPlayerData) {
			deaths += data.deaths;
			kds += data.kd;
			kills += data.kills;
		}
		kds /= KBFFAPlayerData.size();
		if (kds != kds)
			kds = 0.00D;
		ChatUtils.sendMessagePAF(pSender, String.format("&7The members of the clan have killed &a%s &7people.", kills));
		ChatUtils.sendMessagePAF(pSender, String.format("&7The members of the clan have died &a%s &7times.", deaths));
		ChatUtils.sendMessagePAF(pSender, String.format("&7The average K/D of the clan is &a%s&7.", kds));
	}

	public String getName() {
		return "KnockBackFFA";
	}
}
