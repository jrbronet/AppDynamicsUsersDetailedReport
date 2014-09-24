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
		
		DBHelper dbh = new DBHelper();
		dbh.connectDB(args[0], args[1], args[2],args[3]);
		dbh.getUsers(users);
		dbh.getRoles(roles);
		dbh.disconnectDB();
		
		ExcelManager em = new ExcelManager();
		em.buildReport(args[4], users, roles);
	}

}
