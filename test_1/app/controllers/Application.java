package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
    
    public static Result index() {
        return ok(views.html.index.render("Booksearch"));
    }
    
    public static Result logout(){
    	session().clear();
    	 ok("Bye");
    	 return redirect(routes.Application.index());
    }
}
