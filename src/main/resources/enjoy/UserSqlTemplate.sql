

#sql("login")
    select * from user
    #for(x : param)
        #(for.first ? "where": "and") #(x.key) #para(x.value)
    #end
    ;
#end


#sql("getUserRoles")
    select r.* from user_role ur left join role r on ur.role = r.uuid where ur.user = #para(0);
#end

#sql("getUserPermission")
    select p.* from permission p right join role_permission rp on p.uuid = rp.permission
    where rp.role in (select role from user_role where user = #para(0));
#end