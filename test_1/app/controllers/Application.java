package controllers;


import static play.libs.Json.toJson;

import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;

import models.CommentModel;
import models.UserModel;
import play.Logger;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;


public class Application extends Controller {
    
	public static UserModel loggedUser;
	
	public static void setUser(UserModel loggedUser){
		Application.loggedUser = loggedUser;
	}
	
    public static Result index() {
    	testUser();
        return ok(views.html.index.render("Booksearch"));
    }
    
    public static Result logout(){
    	loggedUser = null;
    	session().clear();
    	 return redirect(routes.Application.index());
    }
    
    public static Result postComment(){
    	
		CommentModel comment = Form.form(CommentModel.class).bindFromRequest().get();
		Logger.info("Logged user at posting",session("signedId"));
		comment.postedAt = new Date();
	

		String id = session("signedId");
		
		UserModel savedUser = Ebean.find(UserModel.class)
				.where()
					.eq("id",id)
				.findUnique();
		comment.author = savedUser;
		
		Logger.debug("Comment before posting " + comment.toString());
		
		if(!comment.content.isEmpty()){
			comment.save();
			return redirect(routes.User.show(id));
		}
		return null;
	}
    
    public static Result getComments(){
		@SuppressWarnings("unchecked")
		List<CommentModel> comments = new Model.Finder(String.class, CommentModel.class).all();
		return ok(toJson(comments));	
    }
    
    public static void testUser(){
    	if(loggedUser == null){
    		Logger.debug("user is null");
    		Logger.debug(session().toString());
    	}
    	
    	else{
    		Logger.debug(loggedUser.toString());
    	}
    }
}
