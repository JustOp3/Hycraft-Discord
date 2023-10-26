package fr.justop.commands;

import fr.justop.Discord;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
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

			TextComponent message = new TextComponent(TextComponent.fromLegacyText(Discord.PREFIX + "§eRejoins notre discord: §b§nhttps://discord.gg/Hycraft-Ages"));
			message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Content[]{(Content) new Text("§6Clique pour ouvrir le lien dans ton navigateur")}));
			message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/fhdGx56mCA"));
			player.spigot().sendMessage((BaseComponent) message);
			return true;
		}

		return false;
	}
}
