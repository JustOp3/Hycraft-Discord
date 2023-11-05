package fr.justop.players;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {
	public static final Map<UUID, LinkProfile> profiles = new HashMap<>();

	public static LinkProfile getProfile(Player player) {
		return profiles.get(player.getUniqueId());
	}

	public static void addToMap(UUID uuid, LinkProfile profile) {
		profiles.put(uuid, profile);
	}

	public static void removeProfile(Player player) {
		profiles.remove(player.getUniqueId());
	}

}
