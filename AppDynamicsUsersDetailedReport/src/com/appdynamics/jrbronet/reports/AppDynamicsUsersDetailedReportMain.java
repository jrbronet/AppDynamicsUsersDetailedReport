package com.appdynamics.jrbronet.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// call mail or mutt to send the email and attachment.
// mutt -s "Test mail" -a /tmp/dbagent-3.9.0.0.zip  jose.bronet@appdynamics.com < ./msg.txt

public class AppDynamicsUsersDetailedReportMain {

	public static void main(String[] args) {
		ArrayList users = new ArrayList();
		ArrayList roles = new ArrayList();
		ArrayList groups = new ArrayList();
		
		DBHelper dbh = new DBHelper();
		System.out.println("Connecting to DB");
		dbh.connectDB(args[0], args[1], args[2],args[3]);
		System.out.println("Conneced to DB");
		System.out.println("Getting users info");
		dbh.getUsers(users);
		System.out.println("Getting roles info");
		dbh.getRoles(roles);
		System.out.println("Getting groups info");
		dbh.getGroups(groups);
		System.out.println("Disconnecting from DB");
		dbh.disconnectDB();
		System.out.println("Disconnected from DB");
		
		System.out.println("Creating report "+args[4]);
		ExcelManager em = new ExcelManager();
		em.buildReport(args[4], users, roles, groups);
		System.out.println("Report "+args[4]+ " created and ready to be sent");
		System.out.println("Done");
	}

}
