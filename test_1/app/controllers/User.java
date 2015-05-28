package controllers;


import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;



public class User extends Controller{
	
	public static Result index() {
		return ok(user.render());
		
	}

	

}
