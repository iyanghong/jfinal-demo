package org.example.common.service;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import org.example.common.exception.BaseException;
import org.example.common.exception.ConstantException;

import java.util.ArrayList;
import java.util.List;

public class BaseService<M extends Model<M>> {

    /**
     * 根据参数获取所有数据列表
     *
     * @param model          model
     * @param templateSqlKey sql 的key
     * @param param          参数
     * @return
     */
    public List<M> getAllByParam(M model, String templateSqlKey, Kv param) {
        return model.find(Db.template(templateSqlKey, Kv.of("param", param)).getSqlPara());
    }


    /**
     * 根据参数获取列表
     *
     * @param model          model
     * @param pageNumber     页码
     * @param pageSize       页大小
     * @param templateSqlKey sql 的key
     * @param param          参数
     * @return
     */
    public Page<M> getListByParam(M model, Integer pageNumber, Integer pageSize, String templateSqlKey, Kv param) {
        return model.paginate(pageNumber, pageSize, Db.template(templateSqlKey, Kv.of("param", param)).getSqlPara());
    }

    /**
     * 根据参数获取单个
     *
     * @param model          model
     * @param templateSqlKey sql 的key
     * @param param          参数
     * @return
     */
    public M getFirstByParam(M model, String templateSqlKey, Kv param) {
        return model.findFirst(Db.template(templateSqlKey, Kv.of("param", param)).getSqlPara());
    }

    /**
     * 根据uuid获取
     *
     * @param model
     * @param templateSqlKey
     * @param uuid
     * @return
     */
    public M getFirstByUuid(M model, String templateSqlKey, String uuid) {
        return model.findFirst(Db.template(templateSqlKey, Kv.of("param", Kv.of("uuid = ", uuid))).getSqlPara());
    }

    /**
     * 根据参数删除
     *
     * @param model          model
     * @param templateSqlKey sql 的key
     * @param param          参数
     * @return
     */
    public boolean deleteByParam(M model, String templateSqlKey, Kv param) {
        M entity = getFirstByParam(model, templateSqlKey, param);
        return entity.delete();
    }

}
