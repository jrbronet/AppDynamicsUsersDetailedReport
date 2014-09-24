by Jose R Bronet

Users, Groups and Roles Reporting tool that generates an excel sheet to be sent to customers 
Ready for multi-tenant controllers
Built for 3.9 controllers schema


Usage: java -jar mysqlhostname:mysqlport mysqluser mysqlpassword customer1 res.xls
Example usage: appdynamics.local:3388 root Password customer1 res.xls

mutt command for sending emails to customers
echo Your daily Users Detailed report > msg.txt ; mutt -s "Test Email" -a /tmp/res.xls -- jrbronet@gmail.com jose.bronet@appdynamics.com < msg.txt
