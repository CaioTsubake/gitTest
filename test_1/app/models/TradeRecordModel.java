package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class TradeRecordModel extends Model {

	private static final long serialVersionUID = 1L;

	//Participants
	@Id
	public String  senderId;
	UserModel receiver;
	
	//Books Traded
	BookModel bookSent;
	BookModel bookReceived;
	
	//Time of the trade
	Date timeOfTrade;
	
}
