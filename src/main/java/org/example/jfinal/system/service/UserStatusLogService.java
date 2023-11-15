package org.example.jfinal.system.service;

import org.example.jfinal.system.model.UserStatusLog;

import java.util.Date;

public interface UserStatusLogService {
    public void logUserStatusChange(String userUuid, Integer changeStatus, Date duration, String remake);
    public UserStatusLog abnormalStatusIsOver(String userUuid);
}
