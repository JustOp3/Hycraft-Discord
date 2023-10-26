package me.sharkz.ultralinks.utils;

import org.bukkit.ChatColor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

	public static String color(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static List<String> color(List<String> messages) {
		return (List<String>) messages.stream().map(Util::color).collect(Collectors.toList());
	}

	public static String reverseColor(String message) {
		return ChatColor.stripColor(message);
	}

	public static List<String> reverseColor(List<String> messages) {
		return (List<String>) messages.stream().map(Util::reverseColor).collect(Collectors.toList());
	}

	public static Object getPrivateField(Object object, String field) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = object.getClass();
		Field objectField = clazz.getDeclaredField(field);
		objectField.setAccessible(true);
		Object result = objectField.get(object);
		objectField.setAccessible(false);
		return result;
	}
}
