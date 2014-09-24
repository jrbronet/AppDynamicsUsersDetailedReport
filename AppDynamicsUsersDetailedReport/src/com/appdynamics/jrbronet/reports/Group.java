package com.appdynamics.jrbronet.reports;

public class Group {

		String id;
		String name;
		String description;
		String userName;
		String roleName;

		public String print(){
			return new String ("id:"+id+", name:"+name+", description:"+description);
		}
}
