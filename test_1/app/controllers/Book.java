package controllers;

import static play.libs.Json.toJson;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.BookModel;
import models.CommentModel;
import models.UserModel;
import play.Logger;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Book extends Controller{

	public static Result registerPage(){
		return ok(registerBook.render());
	}
	
	public static Result addBook() {
		BookModel newBook =  Form.form(BookModel.class).bindFromRequest().get();
		newBook.save();
		return ok();
		
	}
	
	public static Result getBooks() {
		List<BookModel> books = Ebean.find(BookModel.class).findList();
		return ok(toJson(books));		
	}
	
	public static Result showBook(String id) {
		String session = session("signed");
		
		if(session != null){
			UserModel thisUser = Ebean.find(UserModel.class)
					.where()
						.eq("id",id)
					.findUnique();
			
			BookModel currentBook = Ebean.find(BookModel.class)
					.where()
						.eq("id", id)
					.findUnique();
			if(currentBook != null){
				return ok(views.html.book.render("This is the page for " + currentBook.title));
			}
			
			else {
				return ok(index.render("This book does not exist."));
			}
			
			
		}
		else {
			return unauthorized("You have to be signed in to see this page.");
		}
	}
	
	
}
