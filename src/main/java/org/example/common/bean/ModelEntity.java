package org.example.common.bean;

import com.jfinal.plugin.activerecord.Model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class ModelEntity implements Serializable {

    public Model model = null;


    public static ModelEntity n(Model record) {
        return new ModelEntity(record);
    }

    public boolean isNull() {
        return model == null;
    }


    public ModelEntity(Model models) {
        this.model = models;
    }

    public int getInt(String column) {
        return model == null ? null : model.getInt(column);
    }

    public int getInt(String column, int def) {
        return model == null ? def : (model.getInt(column) == null ? def : model.getInt(column));
    }

    public String getStr(String column) {
        return model == null ? null : model.getStr(column);
    }

   /* public String getImgPath(String column) {
        return model == null ? null : UpImage.TransUrl(model.getStr(column));
    }
*/
    public String getStr(String column, String def) {
        return model == null ? def : (model.getStr(column) == null ? def : model.getStr(column));
    }

    public Date getDate(String column) {
        return model == null ? null : model.getDate(column);
    }

    /*public String getDateTime(String column) {
        return model == null ? null : DateUtil.getDateTime((model.getDate(column)));
    }*/

    public Date getDate(String column, Date def) {
        return model == null ? def : (model.getDate(column) == null ? def : model.getDate(column));
    }

    public double getDouble(String column) {
        return model == null ? null : model.getDouble(column);
    }

    public double getDouble(String column, double def) {
        return model == null ? def : (model.getDouble(column) == null ? def : model.getDouble(column));
    }

    /*public String getPrice(String column, double def) {
        double dd = model == null ? def : (model.getDouble(column) == null ? def : model.getDouble(column));
        return String.valueOf(DoubleUtil.saveTwoFormat(dd));
    }*/


    public long getLong(String column) {
        return model == null ? null : model.getLong(column);
    }

    public long getLong(String column, long def) {
        return model == null ? def : (model.getLong(column) == null ? def : model.getLong(column));
    }


    public Time getTime(String column) {
        return model == null ? null : model.getTime(column);
    }

    public boolean getBoolean(String column) {
        return model == null ? null : model.getBoolean(column);
    }

    public boolean getBoolean(String column, boolean def) {
        return model == null ? def : model.getBoolean(column);
    }

    public Time getTime(String column, Time def) {
        return model == null ? def : (model.getTime(column) == null ? def : model.getTime(column));
    }


    public Object get(String column) {
        return model == null ? null : model.get(column);
    }

    public ModelEntity put(String key, Object obj) {
        model.put(key, obj);
        return this;
    }


    public String toJson() {
        return model == null ?"":model.toJson();
    }

}
