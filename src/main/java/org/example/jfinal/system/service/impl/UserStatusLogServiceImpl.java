package org.example.jfinal.system.service.impl;

import com.jfinal.plugin.activerecord.Db;
import org.example.common.exception.BaseException;
import org.example.common.exception.ConstantException;
import org.example.common.service.BaseService;
import org.example.jfinal.system.model.User;
import org.example.jfinal.system.model.UserStatusLog;
import org.example.jfinal.system.service.UserStatusLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;

public class UserStatusLogServiceImpl extends BaseService<UserStatusLog> implements UserStatusLogService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public void logUserStatusChange(String userId, Integer changeStatus, Date duration, String remark) {
        User user = new User().findById(userId);
        Integer currentStatus = user.getStatus();
        if (!changeStatus.equals(currentStatus)) {

            UserStatusLog userStatusLog = new UserStatusLog();
            userStatusLog.setUuid(UUID.randomUUID().toString());
            userStatusLog.setUser(user.getUuid());
            userStatusLog.setCurrentStatus(user.getStatus());
            userStatusLog.setDuration(duration);
            userStatusLog.setChangeStatus(changeStatus);
            userStatusLog.setRemark(remark);
            userStatusLog.save();
            user.setStatus(changeStatus);
            user.update();
        }
        log.info("用户状态记录, 用户状态修改: user={}, currentStatus={}, changeStatus={}", userId, currentStatus, changeStatus);
    }

    public UserStatusLog abnormalStatusIsOver(String userUuid) {
        return new UserStatusLog().findFirst(Db.template("user.abnormalStatusIsOver", userUuid, new Date()).getSqlPara());
    }
}
