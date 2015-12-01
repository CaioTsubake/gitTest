package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
@Entity
public class TradeModel extends Model {

// All the data from the trade
	
	// The Users involved in the trade
	@Id
	String senderId;
	UserModel receiver;
	
	// The books that were traded
	BookModel bookSent, bookReceived;
	
	// The time of the trade
	Date timeOfTrade;
}
