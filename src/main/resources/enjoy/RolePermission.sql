#namespace("rolePermission")
    #sql("getByParam")
        select * from role_permission
        #for(x : param)
        #(for.first ? "where": "and") #(x.key) #para(x.value)
        #end
    #end

    #sql("deleteNeedDeleteList")
        delete from role_permission where role = #para(0) and permission #para(1, "not in")
    #end

#end