package models;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

import org.jboss.logging.FormatWith;
import org.joda.convert.FromString;

import play.db.ebean.Model;

@Entity
public class CommentModel extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommentModel() {
		
	}
	
	public CommentModel(UserModel author, String content){
		this.author = author;
		this.content = content;
		this.postedAt = new Date();
		this.replies = new ArrayList<CommentModel>();
	}

	
	@Id
	public String id;
	@ManyToOne
	public UserModel author;
	
	
	public Date postedAt;
	
	public String content;
	
	public ArrayList<CommentModel> replies;
	
	
}
