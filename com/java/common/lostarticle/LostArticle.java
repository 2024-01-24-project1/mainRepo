package com.java.common.lostarticle;

// 분실물 클래스
/**
 * 분실물 정보를 저장하는 클래스
 */
public final class LostArticle {
	
	/**
	 * 분실물 이름을 저장하는 멤버 변수
	 */
	private String article;
	/**
	 * 분실물 내용을 저장하는 멤버 변수
	 */
	private String content;
	/**
	 * 분실물 잃어버린 역 이름을 저장하는 변수
	 */
	private String lostStation;
	/**
	 * 분실물 보관중인 역 이름을 저장하는 변수
	 */
	private String findStation;
	
	// 내용이 있는 분실물 객체 생성자
	/**
	 * 내용이 있는 분실물 객체 생성자
	 * @param article 분실물 이름
	 * @param content 내용
	 * @param lostStation 잃어버린 역 이름
	 * @param findStation 보관중인 역 이름
	 */
	public LostArticle(String article, String content, String lostStation, String findStation) {
		this.article = article;
		this.content = content;
		this.lostStation = lostStation;
		this.findStation = findStation;
	}
	
	// 내용이 없는 분실물 객체 생성자
	/**
	 * 내용이 없는 분실물 객체 생성자
	 * @param article 분실물 이름
	 * @param lostStation 잃어버린 역 이름
	 * @param findStation 보관중인 역 이름
	 */
	public LostArticle(String article, String lostStation, String findStation) {
		this.article = article;
		this.content = "";
		this.lostStation = lostStation;
		this.findStation = findStation;
	}
	
	/**
	 * 분실물 이름을 리턴하는 메서드
	 * @return 분실물 이름
	 */
	public String getArticle() {
		return article;
	}
	/**
	 * 분실물 내용을 리턴하는 메서드
	 * @return 분실물 내용
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 분실물 잃어버린 역 이름을 리턴하는 메서드
	 * @return 잃어버린 역 이름
	 */
	public String getLostStation() {
		return lostStation;
	}
	/**
	 * 분실물 보관중인 역 이름을 리턴하는 메서드
	 * @return 보관중인 역 이름
	 */
	public String getFindStation() {
		return findStation;
	}

	@Override
	public String toString() {
		return String.format("[분실물: %s, 내용: %s, 잃어버린역: %s, 보관중인역: %s]"
							, this.article, this.content , this.lostStation, this.findStation);
	}
	
	
}//End of class
