package models;

import javax.persistence.*;

import play.db.*;
import play.db.ebean.Model;


@Entity
public class UserModel extends Model  {

	@Id
	public String id;
	
	public String username;
	
	public String password;
	
	public String repeatPassword;
}
