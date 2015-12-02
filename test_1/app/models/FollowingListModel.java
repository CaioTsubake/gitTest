package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
public class FollowingListModel extends Model{

	@Id
	public int id;
	
	@ManyToMany
	@JoinTable(name="followingList_following")
	public List<UserModel> followingList = new ArrayList<UserModel>(); 
}
