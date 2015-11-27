package controllers;

import static play.libs.Json.toJson;

import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;

import models.BookCommentModel;
import models.UserModel;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;

public class BookComment extends Controller{
	
	public static Result getBookComments(int bookPageId){
		
		List<BookCommentModel> comments = Ebean.find(BookCommentModel.class)
			.where()
			.eq("bookPageId", bookPageId)
		.findList();
		return ok(toJson(comments));
	}
	
	public static Result postBookComment(int bookPageId){
		BookCommentModel comment = Form.form(BookCommentModel.class).bindFromRequest().get();
		comment.postedAt = new Date();
		comment.bookPageId = bookPageId;
		
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
			
			String pageId = Integer.toString(bookPageId);
			
			Ebean.find(BookCommentModel.class)
				.where()
				.eq("id", pageId)
			.findList();
			
			
			
			comment.save();
			return redirect(routes.Book.showBook(pageId));
		}
		
		
	}

}
