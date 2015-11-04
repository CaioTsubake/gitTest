package controllers;

import static play.libs.Json.toJson;

import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;

import models.UserCommentModel;
import models.UserModel;
import play.Logger;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.helper.form;


public class UserComment extends Controller{

	public static Result getUserComments(int id) {
		
		List<UserCommentModel> comments = Ebean.find(UserCommentModel.class)
			.where()
			.eq("userPageId", id)
		.findList();
		return ok(toJson(comments));
		    
	}
	
	public static Result postUserComment(int userPageId) {
		Logger.debug("user comment");
		UserCommentModel comment = Form.form(UserCommentModel.class).bindFromRequest().get();
		comment.postedAt = new Date();
		comment.userPageId = userPageId;
		
		String id = session("signedId");
		
		UserModel savedUser = Ebean.find(UserModel.class)
			.where()
			.eq("id",id)
		.findUnique();
		
		comment.author = savedUser;
		
		if(comment.content.isEmpty()){
			return noContent();
		}
		
		else {
			Ebean.find(UserCommentModel.class)
				.where()
				.eq("id", userPageId)
			.findList();
			
			String pageId = Integer.toString(userPageId);
			
			comment.save();
			return redirect(routes.User.show(pageId));	
		}
		
	}
}
