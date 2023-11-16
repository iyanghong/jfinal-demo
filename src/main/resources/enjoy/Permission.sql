#namespace("Permission")
    #sql("getByParam")
        select * from permission
        #for(x : param)
        #(for.first ? "where": "and") #(x.key) #para(x.value)
        #end
    #end
#end