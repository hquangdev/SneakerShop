package AdminEntity;

public class Admins {

    private int id;

    private String email;
    private String username;
    private String password;
    private String address;
    private String phone;
    private Integer role;

    // Constructors
    public Admins() {
        super();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}



    
}
