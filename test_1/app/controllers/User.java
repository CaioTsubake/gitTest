package controllers;


import java.util.List;

import com.avaje.ebean.Ebean;

import models.UserModel;
import play.Logger;
import play.data.Form;
import play.data.Form.Field;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;
import static play.libs.Json.*;


public class User extends Controller{
	
	public static Result index() {
		return ok(user.render("Welcome to Login"));
		
	}
	public static Result reg() {
		return ok(register.render());
	}
	
	public static Result login() {
		UserModel formUser = Form.form(UserModel.class).bindFromRequest().get();
		
		
		UserModel user = Ebean.find(UserModel.class)
				.where()
					.eq("username", formUser.username)
				.findUnique();
		
		
		if(user != null){
			if(formUser.password.equals(user.password)){
				return redirect(routes.Application.index());
			}
		}
		
		return redirect(routes.User.index());
	}
	
	public static Result addUser() {
		UserModel user = Form.form(UserModel.class).bindFromRequest().get();
		user.save();
		return redirect(routes.User.reg());
	}
	
	public static Result getUsers() {
		List<UserModel> users = new Model.Finder(String.class, UserModel.class).all();
		return ok(toJson(users));		
	}

}
