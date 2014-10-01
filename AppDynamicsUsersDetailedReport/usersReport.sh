#!/bin/bash

cd "$(dirname "$0")"
QUERY="select user_group.id,user.name as UserName, user_group.name as GroupName,user_group.description,account_role.name as RoleName from user_group, user, user_user_group_mapping, user_group_account_role_mapping, account_role, account where user_group.id = user_user_group_mapping.user_group_id and user_user_group_mapping.user_id = user.id and user_group_account_role_mapping.account_role_id = account_role.id and user_group_account_role_mapping.user_group_id = user_group.id and account.id = account_role.account_id and account.name = '$1' and user.deleted = 0 and user_group.deleted = 0 order by user_group.name, user.name ASC;"
echo "$QUERY" | sudo mysql-ro | sed "s/'/\'/;s/\t/\",\"/g;s/^/\"/;s/$/\"/;s/\n//g" > groups.csv

QUERY="select * from account_role, permission where account_role.id = permission.account_role_id ORDER BY account_role.name ASC;"
echo "$QUERY" | sudo mysql-ro | sed "s/'/\'/;s/\t/\",\"/g;s/^/\"/;s/$/\"/;s/\n//g" > roles.csv

QUERY="select user.id,user.name,account_role.name as RoleName, user.email from user,user_account_role_mapping,account_role,account where user.id=user_account_role_mapping.user_id and account_role.id = user_account_role_mapping.account_role_id and account_role.account_id = account.id and account.name = '$1' and user.deleted = 0 order by user.name, account.name ASC;"
echo "$QUERY" | sudo mysql-ro | sed "s/'/\'/;s/\t/\",\"/g;s/^/\"/;s/$/\"/;s/\n//g" > users.csv

echo "Daily Users Report for $1" > msg.txt

mutt -s "Users Daily Report" -a users.csv roles.csv groups.csv -- jose.bronet@appdynamics.com andrew.guyler@aviva.co.uk < msg.txt

rm -f *.txt *.csv sent
