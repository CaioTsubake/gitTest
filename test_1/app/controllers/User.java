package controllers;


import java.util.List;
import models.UserModel;
import play.data.Form;
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

	public static Result addUser() {
		UserModel user = Form.form(UserModel.class).bindFromRequest().get();
		user.save();
		System.out.print("caio");
		System.out.print(user);
		//return ok();
		return redirect(routes.User.reg());
	}
	
	public static Result getUsers() {
		List<UserModel> users = new Model.Finder(String.class, UserModel.class).all();
		return ok(toJson(users));		
	}

}
