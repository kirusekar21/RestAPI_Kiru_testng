package pojo;

public class User {
	   private static  User instance;
	   private String user_first_name;
	   private String user_last_name;
	   private String user_contact_number;
	   private String user_email_id;
	   private User_address userAddress;
	   private String user_id;
	   private String endpoint;
	   private int statusCode;
	   
	   public static User getInstance() {
	        if (instance == null) {
	            instance = new User();
	        }
	        return instance;
	    }
	   public String getuser_id() {
		     return user_id;
		}  
	   public void setuser_id(String user_id) {
	         this.user_id=user_id;
	   }  
	   public String getendpoint() {
		     return endpoint;
		}  
	   public void setendpoint(String endpoint) {
	         this.endpoint=endpoint;
	   }  
	   public int getstatusCode() {
		     return statusCode;
		}  
	   public void setstatusCode(int statusCode) {
	         this.statusCode=statusCode;
	   }
	
	   public String getUser_first_name() {
		return user_first_name;
	}
	   public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}
	   public String getUser_last_name() {
		return user_last_name;
	}
	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}
	public String getUser_contact_number() {
		return user_contact_number;
	}
	public void setUser_contact_number(String user_contact_number) {
		this.user_contact_number = user_contact_number;
	}
	public String getUser_email_id() {
		return user_email_id;
	}
	public void setUser_email_id(String user_email_id) {
		this.user_email_id = user_email_id;
	}
	public User_address getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(User_address userAddress) {
		this.userAddress = userAddress;
	}
	
 }
