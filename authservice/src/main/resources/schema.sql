
-- h2 (testing)

CREATE TABLE `t_user` (
   `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '用户ID',
   `name` varchar(45) NOT NULL DEFAULT '' COMMENT '用户名',
   `enabled` boolean NOT NULL DEFAULT TRUE COMMENT '激活状态',
   `telephone` varchar(15) NOT NULL COMMENT '电话号码',
   `password` varchar(64) NOT NULL DEFAULT '' COMMENT '用户密码',
   `email` varchar(64) NOT NULL DEFAULT '' COMMENT '用户邮箱',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
--    UNIQUE KEY `uniq_name` (`name`)
 );

CREATE TABLE `t_role`(
                         `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                         `name` varchar(255) NOT NULL ,
                         `nameZh` varchar(255) NOT NULL ,
                         `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
                         `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`)
--                          UNIQUE KEY `uniq_name` (`name`),
--                          UNIQUE KEY `uniq_nameZh`(`nameZh`)
);
CREATE TABLE `t_r_u`(
    `r_id` bigint(20) NOT NULL COMMENT 'role_id',
    `u_id` bigint(20) NOT NULL COMMENT 'user_id',
    `comment` varchar(255) NULL COMMENT 'Comment' ,
    primary key (`r_id`,`u_id`),
    foreign key (`r_id`) references `t_role`(`id`),
    foreign key (`u_id`) references `t_user`(`id`)

);
 --mysql
--  CREATE TABLE `users` (
--    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
--    `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
--    `password` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户密码',
--    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
--    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--    PRIMARY KEY (`id`),
--    UNIQUE KEY `uniq_name` (`name`)
--  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci