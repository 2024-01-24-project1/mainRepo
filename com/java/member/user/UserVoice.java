package com.java.member.user;

public class UserVoice {
	private String id;
	private String title;
	private String content;
	private String isRead;
	
	public UserVoice(String id, String title, String content, String isRead) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.isRead = isRead;
	}
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getIsRead() {
		return isRead;
	}

	@Override
	public String toString() {
		return String.format("UserVoice [아이디: %s, 제목: %s\r\n내용:%s\r\n읽음/안읽음:%s]\r\n"
							, this.id, this.title, this.content, this.isRead);
	}
	
	
}//End of class
