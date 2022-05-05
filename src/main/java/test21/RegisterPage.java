package test21;

import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class User {
	public int id;
	public String fname;
	public String lname;
	public String pnum;
	public String email;
	public String pass;

	public User(int id, String fname, String lname, String pnum, String email, String pass) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.pnum = pnum;
		this.email = email;
		this.pass = pass;
	}
	
	public User() {
		this.id = -1;
		this.fname = null;
		this.lname = null;
		this.pnum = null;
		this.email = null;
		this.pass = null;
	}
	
}

@Path("/reg_page")
public class RegisterPage {
	
	int count = 0;
	
    @POST
    @Path("/reg_func")
    @Consumes("application/x-www-form-urlencoded")
    public String regFunc(@FormParam("fname") String fname, @FormParam("lname") String lname,
    		@FormParam("pnum") String pnum, @FormParam("email") String email, @FormParam("password") String password) {
    	// add code to add user to database
    	
    	
    	// temporary solution
    	count++;
		saveUsers(new User(count, fname, lname, pnum, email, password));
		
		
		return "User " + count + " added";
  
    }	
    
    public void saveUsers(User u) {
    	// add code to add user to database
    	String result = ""; 
    	
       	
    	result += u.id + " " + u.fname + " " + u.lname + " " 
    			+ u.pnum + " " + u.email + " " + u.pass + " " + "\n"; 
    	
    	
    	
    	try {
        	FileWriter fwr = new FileWriter("userData", true);
        	fwr.write(result);
        	fwr.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
   
    }
    
    
    @GET
    @Path("/show_users")
    @Produces(MediaType.TEXT_PLAIN)
    public String showUsers() {
    	// add code to add user to database
    	String result = ""; 
    
    	
    	try {
        	File fi = new File("userData");
        	Scanner reader = new Scanner(fi);
        	while (reader.hasNextLine()) {
        		result += reader.nextLine();
        	}
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	return result;
   
    }	

}
