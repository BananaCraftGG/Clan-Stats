package de.brownie.clanstats.games.bedwars.connection;

import de.brownie.clanstats.games.bedwars.data.BedwarsPlayerData;
import de.simonsator.partyandfriends.communication.sql.SQLCommunication;
import de.simonsator.partyandfriendsgui.communication.BungeecordCommunication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class BedWarsConnection extends SQLCommunication {

	public BedWarsConnection(String pDatabase, String pURL, String pUserName, String pPassword, boolean pSSL) {
		super(pDatabase, pURL, pUserName, pPassword, pSSL);
	}

	public BedwarsPlayerData getPlayerData(UUID pUUID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select * from `" + this.DATABASE + "`." + "bedwars WHERE UUID='" + pUUID.toString() + "' LIMIT 1");
			if (rs.next()) {
				int kills = rs.getInt("KILLS");
				int deaths = rs.getInt("DEATHS");
				String kd = String.format("%.2f", (double) kills / deaths);
				return new BedwarsPlayerData(rs.getInt("WINS"), rs.getInt("LOSSES"), rs.getInt("PLAYED"), rs.getInt("BED"), Double.parseDouble(kd), deaths, kills);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, stmt);
		}
		return null;
	}

}