package test21;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

@Path("/login_page")
public class LoginPage {

    @POST
    @Path("login_func/")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    public String loginFunc(@FormParam("email") String email, @FormParam("password") String password) {
        if(checkCred()) {
        	return "Logged in as: " + email;
        } else {
        	return "Failed!";
        }
    }
    
    private boolean checkCred() {
    	
    	// authentication code
    	
    	// placeholder for now
    	return true;
    }
}
