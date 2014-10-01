package com.appdynamics.jrbronet.reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;


public class DBHelper {
	
	Connection conn;
	String tenant;
	
	public void connectDB(String host, String username, String password, String tenant){
		this.tenant=tenant;
		
		String url = "jdbc:mysql://"+host+"/controller";
		try{
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			conn = DriverManager.getConnection (url, username, password);
		}catch (Exception e){
			e.printStackTrace();
		}				
	}
	
	public void disconnectDB(){				
		try{			
			conn.close();
		}catch (Exception e){
			e.printStackTrace();
		}				
	}
	
	public void getUsers(List users){
		try{
			Statement statement = conn.createStatement();
			//ResultSet rs = statement.executeQuery("select * from user");
			ResultSet rs = statement.executeQuery("select * from user,user_account_role_mapping,account_role,account where user.id=user_account_role_mapping.user_id and account_role.id = user_account_role_mapping.account_role_id and account_role.account_id = account.id and account.name = '"+this.tenant+"' ORDER BY user.name, account_role.name ASC;");			
			while ( rs.next() ) {				
	            	User u = new User();
	            	u.account_id = rs.getString("user.account_id");
	            	u.id = rs.getString("id");
	            	u.display_name = rs.getString("user.display_name");
	            	u.name = rs.getString("user.name");
	            	u.email = rs.getString("user.email");
	            	u.id = rs.getString("user.id");
	            	u.roleName = rs.getString("account_role.name");
	            	//users.put(u.id, u);
	            	users.add(u);	
			}
		}catch (Exception e){
			e.printStackTrace();
		}		
	}
	
	public void getRoles(List roles){
		try{
			Statement statement = conn.createStatement();
			//ResultSet rs = statement.executeQuery("select * from user");
			ResultSet rs = statement.executeQuery("select * from account_role, permission where account_role.id = permission.account_role_id ORDER BY account_role.name ASC;");						
			while ( rs.next() ) {				
	            	Role r = new Role();	            		            		            	
	            	r.id = rs.getString("account_role.id");
	            	r.name = rs.getString("account_role.name");
	            	r.permissionName = rs.getString("permission_action");
	            	r.permissioEntity = rs.getString("entity_type");
	            	r.entity_id = rs.getString("entity_id");	            	
	            	roles.add(r);	
			}
		}catch (Exception e){
			e.printStackTrace();
		}		
	}
	
	public void getGroups(List groups){
		try{
			Statement statement = conn.createStatement();
			//ResultSet rs = statement.executeQuery("select * from user");
			ResultSet rs = statement.executeQuery("select * from user_group, user, user_user_group_mapping, user_group_account_role_mapping, account_role where user_group.id = user_user_group_mapping.user_group_id and user_user_group_mapping.user_id = user.id and user_group_account_role_mapping.account_role_id = account_role.id and user_group_account_role_mapping.user_group_id = user_group.id order by user_group.name, user.name ASC;");						
			while ( rs.next() ) {				
	            	Group g = new Group();	            		            		            	
	            	g.id = rs.getString("user_group.id");
	            	g.name = rs.getString("user_group.name");
	            	g.description = rs.getString("user_group.description");
	            	g.roleName = rs.getString("account_role.name");
	            	g.userName = rs.getString("user.name");
	            	groups.add(g);	
			}
		}catch (Exception e){
			e.printStackTrace();
		}		
	}

}
