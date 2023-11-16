#namespace("role")
    #sql("getByParam")
        select * from role
        #for(x : param)
        #(for.first ? "where": "and") #(x.key) #para(x.value)
        #end
    #end

    #sql("getUserRole")
        select r.* from role r inner join user_role ur on ur.role = r.uuid where ur.user = #para(0)
    #end
#end