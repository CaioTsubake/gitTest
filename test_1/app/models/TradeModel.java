package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.ebean.Model;
@Entity
public class TradeModel extends Model {

// All the data from the trade
	
	// The Users involved in the trade
	UserModel sender, receiver;
	
	// The books that were traded
	BookModel bookSent, bookReceived;
	
	// The time of the trade
	Date timeOfTrade;
}
