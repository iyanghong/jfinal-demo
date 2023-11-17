#namespace("userRole")
    #sql("getByParam")
        select * from user_role
        #for(x : param)
        #(for.first ? "where": "and") #(x.key) #para(x.value)
        #end
    #end

    #sql("deleteNeedDeleteList")
        delete from user_role where
        user = #para(0)
        and role not in (select uuid from role where instr(#para(1),uuid) > 0)
    #end
#end