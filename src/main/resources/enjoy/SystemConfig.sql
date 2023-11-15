
#namespace("systemConfig")
    #sql("getByCode")
        select * from system_config where code = #para(0)
    #end
    #sql("getByParam")
        select * from system_config
        #for(x : param)
        #(for.first ? "where": "and") #(x.key) #para(x.value)
        #end
    #end
#end