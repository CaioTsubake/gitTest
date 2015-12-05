package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class FolloweeModel extends Model{
	@Id
	public String id;
	
	public String username;
	
	public String userId;
	
	public String ownerId;

	public void setId(String id) {
		this.userId = id;
	}
	
}
