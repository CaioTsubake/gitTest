package controllers;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.avaje.ebean.Ebean;

import models.BookList;
import models.BookModel;
import models.FolloweeModel;
import models.FollowingListModel;
import models.UserModel;
import play.Logger;
import play.data.Form;
import play.data.Form.Field;
import play.db.ebean.Model;
import play.mvc.*;
import sun.net.RegisteredDomain;
import views.html.*;
import static play.libs.Json.*;


public class User extends Controller{
	
	public static Result index() {
		return ok(login.render("Welcome to Login"));
		
	}
	public static Result reg() {
		return ok(register.render());
	}
	
	public static Result followingList(String id){
		
		UserModel thisUser = Ebean.find(UserModel.class)
			.where()
				.eq("id",id)
			.findUnique();
		
		return ok(followingList.render("Following List",thisUser));
	}
	
	public static Result login() {
		UserModel formUser = Form.form(UserModel.class).bindFromRequest().get();
		
		UserModel user = Ebean.find(UserModel.class)
				.where()
					.eq("username", formUser.username)
				.findUnique();
		
		// If there is a user with that username on the database.
		if(user != null){
			// Check if the password is the correct one
			if(PasswordChecker.check(user, formUser.password)){
				session("signed",user.username);
				session("signedId",user.id);
				Application.loggedUser = user;
								return redirect(routes.User.show(user.id));
			}
		}
		// An error occurred while trying to log in.
		return redirect(routes.User.index());
	}
	
	public static Result addUser() {
		UserModel user = Form.form(UserModel.class).bindFromRequest().get();
		// If the password don't match the repeated one.
		if(!user.password.equals(user.repeatPassword)){
			return redirect(routes.User.reg());
		}
		// Saves the user on the database and sends to the login page.
		user.save();
		return redirect(routes.User.login());
	}
	
	public static Result getUsers() {
		List<UserModel> users = Ebean.find(UserModel.class).findList();
		return ok(toJson(users));		
	}
	
	public static Result show(String id){
		String session = session("signed");
		
		if(session != null){
			UserModel thisUser = Ebean.find(UserModel.class)
					.where()
						.eq("id",id)
					.findUnique();
			if(thisUser != null){
				return ok(views.html.user.render("This is your user page",thisUser));
			}
			
			else {
				return ok(index.render("User does not exist."));
			}
			
			
		}
		else {
			return unauthorized("You have to be signed in to see this page.");
		}
	}
	
	public static Result getSignedUser(){
		return ok();
	}
	
	public static Result addFollower(String pageUserId){
		
		String id = session("signedId");
		
		UserModel signedUser = Ebean.find(UserModel.class)
				.where()
					.eq("id",id)
				.findUnique();
		
		UserModel pageUser = Ebean.find(UserModel.class)
			.where()
				.eq("id", pageUserId)
			.findUnique();

		List<FolloweeModel> userList = Ebean.find(FolloweeModel.class)
			.where()
				.eq("ownerId", signedUser.id)
			.findList();
		
		
		// Save a new Followee with the page user's info and signed user ownerId
		
		FolloweeModel following = new FolloweeModel();
		following.ownerId = signedUser.id;
		following.username = pageUser.username;
		following.userId = pageUser.id;
		
		if(checkContainsUser(userList,following)){
			return noContent();
		}
		
		following.save();
		
		return redirect(routes.User.show(pageUserId));
	}
	
	public static Result getFollower(String pageUserId){
		UserModel pageUser = Ebean.find(UserModel.class)
				.where()
					.eq("id", pageUserId)
				.findUnique();
		// Find a list of all the followee that have this owner ID
		
		List<FolloweeModel> following = Ebean.find(FolloweeModel.class)
			.where()
				.eq("ownerId", pageUserId)
			.findList();
		
		return ok(toJson(following));
	}
	
	public static Result addBook(String bookId){
		String id = session("signedId");
		
		UserModel signedUser = Ebean.find(UserModel.class)
				.where()
					.eq("id",id)
				.findUnique();
		
		BookModel book = Ebean.find(BookModel.class)
			.where()
				.eq("id", bookId)
			.findUnique();
		
		List<BookList> userList = Ebean.find(BookList.class)
			.where()
				.eq("ownerId", signedUser.id)
			.findList();
		
		
		
		// Save a new bookList with the book information and with signed user's owner ID
		BookList volume = new BookList();
		volume.setbookId(book.id);
		volume.setBookName(book.title);
		volume.setOwnerId(signedUser.id);
		
		if(checkContainsBook(userList, volume)){
			return noContent();
					
		}
		
		volume.save();
		return redirect(routes.Book.showBook(bookId));
	}
	
	public static Result getBooks(String pageUserId){
		List<BookList> listing = Ebean.find(BookList.class)
			.where()
				.eq("ownerId", pageUserId)
			.findList();
		return ok(toJson(listing));
	}
	
	public static Result bookListing(String id){
		
		UserModel thisUser = Ebean.find(UserModel.class)
			.where()
				.eq("id",id)
			.findUnique();
		
		return ok(bookLibrary.render("Book Library",thisUser));
	}
	
	private static boolean checkContainsUser(List<FolloweeModel> list, FolloweeModel user){
		if(list.isEmpty()){
			return false;
		}
		boolean match = false;
		Iterator it = list.iterator();
		
		while(it.hasNext() && match == false){
			FolloweeModel entry = (FolloweeModel) it.next();
			if(entry.username.equalsIgnoreCase(user.username)){
				match = true;
			}
		}
		if(match == true ){
			return true;
		}
		else {
			return false;
		}
	}
	
	private static boolean checkContainsBook(List<BookList> list, BookList book){
		if(list.isEmpty()){
			return false;
		}
		boolean match = false;
		Iterator it = list.iterator();
		
		while(it.hasNext() && match == false){
			BookList entry = (BookList) it.next();
			if(entry.bookName.equalsIgnoreCase(book.bookName)){
				match = true;
			}
		}
		if(match == true ){
			return true;
		}
		else {
			return false;
		}
	}
	
}
