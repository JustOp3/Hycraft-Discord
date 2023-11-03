package fr.justop.db;

import fr.justop.Discord;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private Connection connection;

	public DatabaseConnection() {
		this.connect();
	}

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://135.181.79.174:3306/s4_linkdb", "u4_gllouhFY6D", "U=.zj1Rz!cQial7TVOY^vdCe");

			if (!(this.connection == null))
				Bukkit.getConsoleSender().sendMessage(Discord.PREFIX + "§aConnection réussie à la DB");
		} catch (SQLException | ClassNotFoundException exeption) {
			exeption.printStackTrace();
		}
	}

	public void close() throws SQLException {
		if (this.connection != null && !this.connection.isClosed()) {
			this.connection.close();
			Bukkit.getConsoleSender().sendMessage(Discord.PREFIX + "§aLa connection de la Database à bien été interrompue!");
		}
	}

	public Connection getConnection() throws SQLException {
		if (this.connection != null && !this.connection.isClosed()) {
			return this.connection;
		}
		this.connect();
		return this.connection;
	}
}
