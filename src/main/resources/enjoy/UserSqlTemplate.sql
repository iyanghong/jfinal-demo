
#namespace("user")
    #sql("getByParam")
        select * from user
        #for(x : param)
            #(for.first ? "where": "and") #(x.key) #para(x.value)
        #end

    #end

    #sql("update")
        update user
            #for(x : param)
            #(for.first ? "set": "") #(x.key) = #para(x.value)
            #end
        where uuid = #para(userUuid)
        ;
    #end


    #sql("getUserRoles")
        select r.* from user_role ur left join role r on ur.role = r.uuid where ur.user = #para(0);
    #end

    #sql("getUserRolesAdmin")
    SELECT
        r.*,if( ur.id is null,'0','1') as checked
    FROM
        role r left join user_role ur on r.uuid = ur.role and ur.user = #para(0);
    #end

    #sql("getUserPermission")
        select p.* from permission p right join role_permission rp on p.uuid = rp.permission
        where rp.role in (select role from user_role where user = #para(0));
        ;
    #end

    #sql("abnormalStatusIsOver")
        select * from user_status_log where user = #para(0) and duration > #para(1)
        ;
    #end
#end