package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class BookList extends Model{
	@Id
	public String id;
	
	public String bookName;
	
	public String bookId;
	
	public String ownerId;
	
	public void setbookId(String bookId){
		this.bookId = bookId;
	}
	
	public void setBookName(String bookName){
		this.bookName = bookName;
	}
	
	public void setOwnerId(String ownerId){
		this.ownerId = ownerId;
	}
}
