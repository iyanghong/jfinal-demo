package org.example.jfinal.system.service;

import java.util.List;

public interface UserRoleService {
    void save(String userUuid, List<String> roles);
}
