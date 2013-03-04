package model;

public class User {

	private Long id;
	
	private String username;
	
	// 用来记录版本号，不需要提供getter和setter，防止用户自行修改
	@SuppressWarnings("unused")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
