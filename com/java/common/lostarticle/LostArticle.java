package com.java.common.lostarticle;

// 분실물 클래스
public class LostArticle {
	
	private String article;
	private String content;
	private String lostStation;
	private String findStation;
	
	// 내용이 있는 분실물 객체 생성자
	public LostArticle(String article, String content, String lostStation, String findStation) {
		this.article = article;
		this.content = content;
		this.lostStation = lostStation;
		this.findStation = findStation;
	}
	
	// 내용이 없는 분실물 객체 생성자
	public LostArticle(String article, String lostStation, String findStation) {
		this.article = article;
		this.content = "";
		this.lostStation = lostStation;
		this.findStation = findStation;
	}
	
	public String getArticle() {
		return article;
	}
	public String getContent() {
		return content;
	}
	public String getLostStation() {
		return lostStation;
	}
	public String getFindStation() {
		return findStation;
	}

	@Override
	public String toString() {
		return String.format("[분실물: %s, 내용: %s, 잃어버린역: %s, 보관중인역: %s]"
							, this.article, this.content , this.lostStation, this.findStation);
	}
	
	
}//End of class
