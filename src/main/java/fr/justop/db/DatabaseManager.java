package fr.justop.db;

import fr.justop.Discord;
import fr.justop.players.LinkProfile;
import fr.justop.players.ProfileManager;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
	public static void savePlayer(Player player) throws SQLException {

		LinkProfile profile = ProfileManager.getProfile(player);
		String discordId = profile.getDiscordID();
		int isLinked = profile.getIsLinked();
		String linkToken = profile.getLinkToken();

		Connection connection = Discord.getInstance().getDbconnection().getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM linkprofile WHERE uuid=?");
		preparedStatement.setString(1, player.getUniqueId().toString());
		ResultSet result = preparedStatement.executeQuery();

		if (result.next()) {
			PreparedStatement updatestatement = connection.prepareStatement("UPDATE linkprofile SET discordId = ?, isLinked = ?, igName = ?, linkToken = ? WHERE uuid =?");
			updatestatement.setString(1, discordId);
			updatestatement.setInt(2, isLinked);
			updatestatement.setString(3, player.getName());
			updatestatement.setString(4, linkToken);
			updatestatement.setString(5, player.getUniqueId().toString());

			updatestatement.executeUpdate();

		} else {
			PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO linkprofile (uuid, discordId, isLinked, igName, linkToken) VALUES (?, ?, ?, ?, ?)");
			insertStatement.setString(1, player.getUniqueId().toString());
			insertStatement.setString(2, profile.getDiscordID());
			insertStatement.setInt(3, profile.getIsLinked());
			insertStatement.setString(4, player.getName());
			insertStatement.setString(5, profile.getLinkToken());

			insertStatement.executeUpdate();
		}

	}

	public static void registerPlayer(Player player) throws SQLException {

		Connection connection = Discord.getInstance().getDbconnection().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM linkprofile WHERE uuid=?");
		preparedStatement.setString(1, player.getUniqueId().toString());
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			String discordId = resultSet.getString("discordId");
			int isLinked = resultSet.getInt("isLinked");
			String linkToken = resultSet.getString("linkToken");

			LinkProfile profile = new LinkProfile(discordId, isLinked, linkToken);
			ProfileManager.addToMap(player.getUniqueId(), profile);

		} else {

			LinkProfile profile = new LinkProfile("??????????????????", 0, "????????");
			ProfileManager.addToMap(player.getUniqueId(), profile);
		}
	}
}
