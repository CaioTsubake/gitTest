package models;

import javax.persistence.Entity;

import play.db.ebean.Model;

@Entity
public class BookCommentModel extends CommentModel {
	
	public int bookPageId;

	

}
