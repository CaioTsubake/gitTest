package models;

import play.db.ebean.Model;

public class BookCommentModel extends CommentModel {
	
	BookModel fromBook;

	public BookCommentModel(UserModel author, String content) {
		super(author, content);
	}

}
