package fr.justop.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandDiscord implements CommandExecutor {


	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			TextComponent message = new TextComponent("§r§bpour accéder à notre discord.");
			TextComponent messageClicable = new TextComponent("§e§lCLIQUEZ ICI ");
			messageClicable.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.example.com"));

			messageClicable.addExtra(message);

			player.spigot().sendMessage(messageClicable);
		}

		return true;
	}
}
