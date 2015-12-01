package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class TradeRecordModel extends Model {

	
	//Participants
	@Id
	public String id;
	
	public String senderId;
	public String receiver;
	
	//Books Traded
	public String bookSent;
	public String bookReceived;
	
	//Time of the trade
	public Date timeOfTrade;
	
}
