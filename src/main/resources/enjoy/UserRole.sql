#namespace("userRole")
    #sql("getByParam")
        select * from user_role
        #for(x : param)
        #(for.first ? "where": "and") #(x.key) #para(x.value)
        #end
    #end

    #sql("deleteNeedDeleteList")
        delete from user_role where user = #para(0) and role #para(1, "not in")
    #end
#end