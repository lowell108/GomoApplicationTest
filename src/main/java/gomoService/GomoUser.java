package gomoService;

public class GomoUser {

	private int id;
	private String first_name;
	private String last_name;
	
	public GomoUser() {
		super();
	}
	

	/**
	 * This constructor is used to pull back the entire record including id from the
	 * Db
	 * 
	 * @param id
	 * @param first_name
	 * @param last_name
	 */
	public GomoUser(int id, String first_name, String last_name) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	/**
	 * This constructor is used to create a user record to insert into the Db
	 * 
	 * @param first_name
	 * @param last_name
	 */
	public GomoUser(String first_name, String last_name) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	
	

}
