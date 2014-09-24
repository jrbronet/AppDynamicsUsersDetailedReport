#!/bin/bash
java -Xms64m -Xmx64m -jar AppDynamicsUSersDetailedReport.jar appdynamics.local:3388 root Password01! aviva1 res.xls
echo Your daily Users Detailed report > msg.txt ; mutt -s "AppDynamics Daily Users Report" -a /tmp/res.xls -- andrew.guyler@aviva.co.uk jose.bronet@appdynamics.com < msg.txt