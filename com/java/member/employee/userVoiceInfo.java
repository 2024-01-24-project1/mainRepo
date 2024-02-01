package com.java.member.employee;

public class userVoiceInfo {
		
	private String id;
	private String title;
	private String general;
	private String read;
		
	public userVoiceInfo(String id, String title, String general, String read) {
	
		this.id = id;
		this.title = title;
		this.general = general;
		this.read = read;
		
	}

	@Override
	public String toString() {
		return String.format("%s | %s | %s\n", id, title, read);
	}

	public String getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGeneral() {
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
	}

	public String getRead() {
		return read;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRead(String read) {
		this.read = read;
	}
	
	
	
}

