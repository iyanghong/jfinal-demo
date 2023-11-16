#namespace("permissionGroup")
    #sql("getByParam")
        select * from permission_group
        #for(x : param)
        #(for.first ? "where": "and") #(x.key) #para(x.value)
        #end
    #end
#end