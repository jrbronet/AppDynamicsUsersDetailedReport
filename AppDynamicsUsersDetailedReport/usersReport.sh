
echo "$QUERY" | sudo mysql-ro | sed "s/'/\'/;s/\t/\",\"/g;s/^/\"/;s/$/\"/;s/\n//g" > groups.csv

QUERY="select account_role.id, account_role.name, account_role.description, permission.entity_type, permission.entity_id, permission.permission_action from account_role, permission, account where account.name='$1' and account.id = account_role.account_id and account_role.id = permission.account_role_id and permission.allowed = 1 ORDER BY account_role.name ASC;"
echo "$QUERY" | sudo mysql-ro | sed "s/'/\'/;s/\t/\",\"/g;s/^/\"/;s/$/\"/;s/\n//g" > roles.csv

QUERY="select user.id,user.name,account_role.name as RoleName, user.email from user,user_account_role_mapping,account_role,account where user.id=user_account_role_mapping.user_id and account_role.id = user_account_role_mapping.account_role_id and account_role.account_id = account.id and account.name = '$1' and user.deleted = 0 order by user.name, account.name ASC;"
echo "$QUERY" | sudo mysql-ro | sed "s/'/\'/;s/\t/\",\"/g;s/^/\"/;s/$/\"/;s/\n//g" > users.csv

echo "Daily Users Report for $1" > msg.txt

shift
EMAILS=""
while [[ $# > 0 ]] ; do
        EMAILS="$EMAILS $1"
        shift
done

mutt -s "AppDynamics Users Daily Report" -a users.csv roles.csv groups.csv -- $EMAILS < msg.txt

rm -f msg.txt users.csv groups.csv roles.csv