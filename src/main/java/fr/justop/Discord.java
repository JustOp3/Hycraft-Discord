package fr.justop;

import fr.justop.commands.CommandDiscord;
import fr.justop.commands.LinkCommand;
import fr.justop.db.DatabaseConnection;
import fr.justop.db.DatabaseManager;
import fr.justop.players.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mineacademy.fo.plugin.SimplePlugin;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * PluginTemplate is a simple template you can use every time you make
 * a new plugin. This will save you time because you no longer have to
 * recreate the same skeleton and features each time.
 * <p>
 * It uses Foundation for fast and efficient development process.
 */
public final class Discord extends SimplePlugin implements Listener {
	public static final String PREFIX = "§4[§cHycraft-BOT§4] §r";

	private DatabaseConnection dbconnection;

	/**
	 * Automatically perform login ONCE when the plugin starts.
	 */
	@Override
	protected void onPluginStart() {
		this.getLogger().info(PREFIX + "§aLe plugin est activé!");
		this.dbconnection = new DatabaseConnection();

		try {
			Statement statement = this.dbconnection.getConnection().createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS linkprofile (uuid varchar(36) primary key, discordId varchar(18), isLinked tinyint, igName varchar(36), linkToken varchar(8))");
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		onCommands();
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	private void onCommands() {
		this.getCommand("discord").setExecutor(new CommandDiscord());
		this.getCommand("link").setExecutor(new LinkCommand());
	}

	public static Discord getInstance() {
		return getPlugin(Discord.class);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		try {
			DatabaseManager.registerPlayer(player);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		Bukkit.getConsoleSender().sendMessage(ProfileManager.profiles.toString());
		ProfileManager.removeProfile(player);
	}


	/**
	 * An example event that checks if the right clicked entity is a cow, and makes an explosion.
	 * You can write your events to your main class without having to register a listener.
	 *
	 * @param event
	 */
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent event) {
		if (event.getRightClicked().getType() == EntityType.COW)
			event.getRightClicked().getWorld().createExplosion(event.getRightClicked().getLocation(), 5);
	}

	public DatabaseConnection getDbconnection() {
		return dbconnection;
	}

}
