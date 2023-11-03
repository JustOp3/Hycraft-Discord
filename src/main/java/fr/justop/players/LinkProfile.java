package fr.justop.players;

import java.util.Objects;

public class LinkProfile {
	private String discordID;
	private int isLinked;
	private String linkToken;

	public LinkProfile(String discordID, int isLinked, String linkToken) {
		this.discordID = discordID;
		this.isLinked = isLinked;
		this.linkToken = linkToken;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LinkProfile that = (LinkProfile) o;
		return isLinked == that.isLinked && Objects.equals(discordID, that.discordID) && Objects.equals(linkToken, that.linkToken);
	}

	@Override
	public int hashCode() {
		return Objects.hash(discordID, isLinked, linkToken);
	}

	@Override
	public String toString() {
		return "LinkProfile{" +
				"discordID='" + discordID + '\'' +
				", isLinked=" + isLinked +
				", linkToken='" + linkToken + '\'' +
				'}';
	}

	public String getDiscordID() {
		return discordID;
	}

	public void setDiscordID(String discordID) {
		this.discordID = discordID;
	}

	public int getIsLinked() {
		return isLinked;
	}

	public void setIsLinked(int isLinked) {
		this.isLinked = isLinked;
	}

	public String getLinkToken() {
		return linkToken;
	}

	public void setLinkToken(String linkToken) {
		this.linkToken = linkToken;
	}


}
