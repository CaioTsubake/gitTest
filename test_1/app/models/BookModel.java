package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
public class BookModel extends Model {

	@Id
	public String id;
	
	public String title;
	
	public String ISBN;
	
	public List<CommentModel> bookComments;
}
