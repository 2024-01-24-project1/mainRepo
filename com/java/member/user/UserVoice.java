package com.java.member.user;

/**
 * 민원 정보를 저장하는 클래스
 */
public final class UserVoice {
	/**
	 * 아이디를 저장하는 멤버 변수
	 */
	private String id;
	/**
	 * 민원 제목을 저장하는 멤버 변수
	 */
	private String title;
	/**
	 * 민원 내용을 저장하는 멤버 변수
	 */
	private String content;
	/**
	 * 민원의 읽음 유무를 확인하는 메서드
	 */
	private String isRead;
	
	/**
	 * 민원 등록시 사용되는 객체 생성자
	 * @param id 아이디
	 * @param title 제목
	 * @param content 내용
	 */
	public UserVoice(String id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.isRead = "안읽음";
	}
	/**
	 * 파일 읽고 쓰기 할때 사용되는 객체 생성자
	 * @param id 아이디
	 * @param title 제목
	 * @param content 내용
	 * @param isRead 읽음 유무
	 */
	public UserVoice(String id, String title, String content, String isRead) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.isRead = isRead;
	}
	/**
	 * 아이디를 리턴하는 메서드
	 * @return 아이디
	 */
	public String getId() {
		return id;
	}
	/**
	 * 제목을 리턴하는 메서드
	 * @return 제목
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 민원 내용을 리턴하는 메서드
	 * @return 민원 내용
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 읽음 유무를 리턴하는 메서드
	 * @return 읽음 or 안읽음
	 */
	public String getIsRead() {
		return isRead;
	}
	
	/**
	 * 읽음 유무를 설정하는 메서드
	 * @param isRead 읽음 유무
	 */
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return String.format("UserVoice [아이디: %s, 제목: %s\r\n내용:%s\r\n읽음/안읽음:%s]\r\n"
							, this.id, this.title, this.content, this.isRead);
	}
	
	
}//End of class
