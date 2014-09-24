package com.appdynamics.jrbronet.reports;

public class User {

		String account_id;
		String id;
		String name;
		String display_name;
		String email;
		String roleName;

		public String print(){
			return new String ("account_id:"+account_id+", id:"+id+", name:"+name+", display_name:"+display_name+", email:"+email+", roleName:"+roleName);
		}
}
