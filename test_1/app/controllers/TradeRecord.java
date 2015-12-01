package controllers;

import static play.libs.Json.toJson;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.CommentModel;
import models.TradeRecordModel;
import models.UserModel;
import play.api.mvc.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.tradeHistory;

public class TradeRecord extends Controller {

	public static Result registerTrade(){
		TradeRecordModel comment = Form.form(TradeRecordModel.class).bindFromRequest().get();
		comment.save();
		return redirect(routes.Application.tradeHistory());
	}
	
	public static Result getTrades(){
		String id = session("signedId");
		
		List<TradeRecordModel> records = Ebean.find(TradeRecordModel.class)
			.where()
			.eq("senderId", id)
		.findList();
		
		return ok(toJson(records));	
	}
	
	
}
