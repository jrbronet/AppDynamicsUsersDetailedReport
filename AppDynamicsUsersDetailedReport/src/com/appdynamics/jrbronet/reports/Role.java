package com.appdynamics.jrbronet.reports;

public class Role {
	String id;
	String name;
	String permissionName;
	String permissioEntity;
	String entity_id;

	public String print(){
		return new String("id:"+id+", name:"+name+", permission:"+permissionName+", permissioEntity:"+permissioEntity+", entity_id:"+entity_id);
	}
}
