package models;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
public class CommentModel extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public String id;
	
	public UserModel author;
	
	public Date postedAt;
	
	public String content;
	
	public ArrayList<CommentModel> replies;
	
	public CommentModel(UserModel author, String content){
		this.author = author;
		this.content = content;
		this.postedAt = new Date();
		this.replies = new ArrayList<CommentModel>();
	}

}
