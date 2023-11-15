-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`                 int                                                     NOT NULL AUTO_INCREMENT COMMENT '用户自增id',
    `uuid`               varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户id',
    `service`            varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户来源服务',
    `account`            varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '账户',
    `email`              varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `phone`              varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
    `password`           varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
    `status`             tinyint                                                 NOT NULL DEFAULT 0 COMMENT '账号状态\r\n0-未激活\r\n1-正常\r\n2-密码冻结\r\n3-违规\r\n4-注销',
    `header`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
    `disk`               varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '磁盘id',
    `nickname`           varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
    `gender`             tinyint                                                 NOT NULL DEFAULT 0 COMMENT '性别\r\n0-未知\r\n1-男\r\n2-女',
    `signature`          varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
    `login_ip`           varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
    `last_login_time`    datetime NULL DEFAULT NULL COMMENT '最后登陆时间',
    `password_error_num` tinyint                                                 NOT NULL DEFAULT 0 COMMENT '密码错误次数,最大为五',
    `online_time`        tinyint                                                 NOT NULL DEFAULT 3 COMMENT '登录时常,最大12',
    `score`              int                                                     NOT NULL DEFAULT 0 COMMENT '用户积分',
    `address`            int NULL DEFAULT 0 COMMENT '用户所在城市,默认是未知',
    `created_at`         datetime                                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`         datetime                                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `ACCOUNT_IDX`(`account` ASC) USING BTREE COMMENT '账号索引',
    UNIQUE INDEX `UUID_IDX`(`uuid` ASC) USING BTREE COMMENT 'id索引',
    UNIQUE INDEX `EMAIL_IDX`(`email` ASC) USING BTREE COMMENT '邮箱索引',
    INDEX                `PHONE_IDX`(`phone` ASC) USING BTREE COMMENT '手机号索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;
