package com.java.member.employee;

public class article {

	String type; // 품목
	String content; // 내용
	String discoverLocation;// 발견위치
	String storeLocation; // 저장위치
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return String.format("%s | %s\n", type, storeLocation);
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDiscoverLocation() {
		return discoverLocation;
	}

	public void setDiscoverLocation(String discoverLocation) {
		this.discoverLocation = discoverLocation;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	public article() {
		super();
	}
	
	public article(String type, String content, String discoverLocation, String storeLocation) {
		super();
		this.type = type;
		this.content = content;
		this.discoverLocation = discoverLocation;
		this.storeLocation = storeLocation;
	}


	
	
}
