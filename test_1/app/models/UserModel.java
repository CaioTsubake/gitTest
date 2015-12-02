package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Required;

import play.db.*;
import play.db.ebean.Model;


@Entity
public class UserModel extends Model  {

	@Id
	public String id;
	
	public String username;
	
	public String password;
	
	public String repeatPassword;
	
	public List<CommentModel> userComments;
	
	@OneToOne
	public FollowingListModel userFollowingList;
	
	@ManyToMany
	public List<FollowingListModel> userFollowing = new ArrayList<FollowingListModel>();
	
	
	
	
	
}
