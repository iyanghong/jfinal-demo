package org.example.jfinal.filter;


import com.alibaba.druid.filter.FilterAdapter;
import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.sql.SQLUtils;
import com.jfinal.log.Log;
import com.jfinal.log.Log4jLog;
import org.example.jfinal.system.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DruidSqlLogFilter extends FilterAdapter {
    private boolean formatSQL = false;
    public DruidSqlLogFilter() {
    }

    public DruidSqlLogFilter(boolean formatSQL) {
        this.formatSQL = formatSQL;
    }

    @Override
    public void statement_close(FilterChain chain, StatementProxy statement) throws SQLException {
        super.statement_close(chain, statement);
        String sql = statement.getBatchSql();

        // 不输出无意义的内容
        if (sql.isEmpty()) return;

        int parametersSize = statement.getParametersSize();
        if (parametersSize > 0) {
            List<Object> parameters = new ArrayList<>(parametersSize);

            for (int i = 0; i < parametersSize; ++i) {
                JdbcParameter jdbcParam = statement.getParameter(i);
                parameters.add(jdbcParam != null ? jdbcParam.getValue() : null);
            }
            // 这里可以使用数据库类型常量
            String dbType = statement.getConnectionProxy().getDirectDataSource().getDbType();
            if (formatSQL) {
                try {
                    sql = SQLUtils.format(sql, dbType, parameters, SQLUtils.DEFAULT_LCASE_FORMAT_OPTION);
                }catch (Exception exception){}

            }
        }

        // 打印sal
        printSQL(sql);
    }

    public void printSQL(String sql) {
        String sb = "DruidSqlLogFilter ---------- " + new Date().toString() + " ---------------\n"
                + sql.trim()
                + "\n--------------------------------------------------------------------------------\n";
        System.out.println(sb);
    }
}