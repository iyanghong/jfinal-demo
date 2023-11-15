package org.example;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;
import org.example.jfinal.filter.DruidSqlLogFilter;
import org.example.jfinal.interceptor.AuthInterceptor;
import org.example.jfinal.interceptor.ExceptionInterceptor;
import org.example.jfinal.system.model._MappingKit;
import org.example.jfinal.system.route.SystemRoutes;

public class AppConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {
        // 开启对 jfinal web 项目组件 Controller、Interceptor、Validator 的注入
        constants.setInjectDependency(true);

        // 开启对超类的注入。不开启时可以在超类中通过 Aop.get(...) 进行注入
        constants.setInjectSuperClass(true);

        constants.setResolveJsonRequest(true);
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add(new SystemRoutes());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin dp = new DruidPlugin("jdbc:mysql://localhost:3306/jfinal-test?useUnicode=true&characterEncoding=utf-8", "root", "Ts962464");
        dp.addFilter(new DruidSqlLogFilter(true));
        plugins.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
//        arp.setShowSql(true);
//        arp.setDevMode(true);
        arp.addSqlTemplate("enjoy/UserSqlTemplate.sql");
        arp.addSqlTemplate("enjoy/SystemConfig.sql");
        plugins.add(arp);
        _MappingKit.mapping(arp);


        // 注入redis
        RedisPlugin redisPlugin = new RedisPlugin("default", "127.0.0.1");
        plugins.add(redisPlugin);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {
        //验证权限
        interceptors.add(new AuthInterceptor());
        // 全局异常拦截
        interceptors.add(new ExceptionInterceptor());
    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
