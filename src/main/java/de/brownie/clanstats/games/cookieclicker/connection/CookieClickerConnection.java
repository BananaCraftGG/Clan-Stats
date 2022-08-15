package de.brownie.clanstats.games.cookieclicker.connection;

import de.brownie.clanstats.games.cookieclicker.data.CookieClickerPlayerData;
import de.simonsator.partyandfriends.communication.sql.SQLCommunication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class CookieClickerConnection extends SQLCommunication {

	public CookieClickerConnection(String pDatabase, String pURL, String pUserName, String pPassword, boolean pSSL) {
		super(pDatabase, pURL, pUserName, pPassword, pSSL);
	}

	public CookieClickerPlayerData getPlayerData(UUID pUUID) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			rs = (stmt = con.createStatement()).executeQuery("select cookies from `" + this.DATABASE + "`." + "cookies WHERE UUID='" + pUUID.toString() + "' LIMIT 1");
			if (rs.next()) {
				int cookies = rs.getInt("Cookies");
				return new CookieClickerPlayerData(cookies);
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