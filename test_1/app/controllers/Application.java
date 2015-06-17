package controllers;


import static play.libs.Json.toJson;

import java.util.Date;
import java.util.List;

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
        return ok(views.html.index.render("Booksearch"));
    }
    
    public static Result logout(){
    	loggedUser = null;
    	session().clear();
    	 return redirect(routes.Application.index());
    }
    
    public static Result postComment(){
    	
		CommentModel comment = Form.form(CommentModel.class).bindFromRequest().get();
		comment.author = Application.loggedUser;
		Logger.info(ACCEPT);
		Logger.info(loggedUser.username);
		comment.postedAt = new Date();
		
		if(!comment.content.isEmpty()){
			comment.save();
//			return redirect(routes.User.show(loggedUser.id));
			return ok();
		}
		return null;
	}
    
    public static Result getComments(){
		List<CommentModel> comments = new Model.Finder(String.class, CommentModel.class).all();
		return ok(toJson(comments));	
    }
}
