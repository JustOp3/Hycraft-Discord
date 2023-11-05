package fr.justop.commands;

import fr.justop.Discord;
import fr.justop.db.DatabaseManager;
import fr.justop.players.LinkProfile;
import fr.justop.players.ProfileManager;
import fr.justop.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class LinkCommand implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
		if (!(commandSender instanceof Player)) return false;

		Player player = (Player) commandSender;

		if (!(args.length > 0)) {
			String token = Util.generateToken(8);
			player.sendMessage(Discord.PREFIX + "§aTon token a été généré avec succès !");
			player.sendMessage(Discord.PREFIX + "§aUtilise §b/link §l" + token + "§r §asur notre discord pour lier ton compte.");
			player.sendMessage(Discord.PREFIX + "§aUtilise §b/discord §apour accéder à notre discord.");

			int isLinked = ProfileManager.getProfile(player).getIsLinked();
			String discordId = ProfileManager.getProfile(player).getDiscordID();

			ProfileManager.addToMap(player.getUniqueId(), new LinkProfile(discordId, isLinked, token));

			try {
				DatabaseManager.savePlayer(player);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			return true;
		}

		return false;
	}
}
