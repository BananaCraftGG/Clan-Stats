package de.brownie.clanstats.games.kbffa.connection;

import de.brownie.clanstats.games.kbffa.data.KBFFAPlayerData;
import de.simonsator.partyandfriends.communication.sql.SQLCommunication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class KBFFAConnection extends SQLCommunication {

	public KBFFAConnection(String pDatabase, String pURL, String pUserName, String pPassword, boolean pSSL) {
		super(pDatabase, pURL, pUserName, pPassword, pSSL);
	}

	public KBFFAPlayerData getPlayerData(UUID pUUID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select * from `" + this.DATABASE + "`." + "stats WHERE UUID='" + pUUID.toString() + "' LIMIT 1");
			if (rs.next()) {
				int kills = rs.getInt("KILLS");
				int deaths = rs.getInt("DEATHS");
				String kd = String.format("%.2f", (double) kills / deaths);
				System.out.println(kd);
				return new KBFFAPlayerData(kills, deaths, Double.parseDouble(kd));
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