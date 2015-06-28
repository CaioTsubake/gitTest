package controllers;

import play.mvc.*;
import views.html.*;

public class Book extends Controller{

	public static Result registerPage(){
		return ok(registerBook.render());
	}
}
