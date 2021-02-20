
create table oauth_client_details (
client_id VARCHAR(256) PRIMARY KEY,
resource_ids VARCHAR(256),
client_secret VARCHAR(256),
scope VARCHAR(256),
authorized_grant_types VARCHAR(256),
web_server_redirect_uri VARCHAR(256),
authorities VARCHAR(256),
access_token_validity INTEGER,
refresh_token_validity INTEGER,
additional_information VARCHAR(4096),
autoapprove VARCHAR(256)
);

create table oauth_client_token (
token_id VARCHAR(256),
token longblob,
authentication_id VARCHAR(256) PRIMARY KEY,
user_name VARCHAR(256),
client_id VARCHAR(256)
);

create table oauth_access_token (
token_id VARCHAR(256),
token longblob,
authentication_id VARCHAR(256) PRIMARY KEY,
user_name VARCHAR(256),
client_id VARCHAR(256),
authentication longblob,
refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
token_id VARCHAR(256),
token longblob,
authentication longblob
);

create table oauth_code (
code VARCHAR(256), authentication longblob
);

create table oauth_approvals (
userId VARCHAR(256),
clientId VARCHAR(256),
scope VARCHAR(256),
status VARCHAR(10),
expiresAt datetime,
lastModifiedAt datetime
);


create table ClientDetails (
appId VARCHAR(256) PRIMARY KEY,
resourceIds VARCHAR(256),
appSecret VARCHAR(256),
scope VARCHAR(256),
grantTypes VARCHAR(256),
redirectUrl VARCHAR(256),
authorities VARCHAR(256),
access_token_validity INTEGER,
refresh_token_validity INTEGER,
additionalInformation VARCHAR(4096),
autoApproveScopes VARCHAR(256)
);



在oauth_client_details表添加数据：

 INSERT INTO `oauth_client_details` VALUES ('net5ijy', NULL, '123456', 'all,read,write', 'authorization_code,refresh_token,password', NULL, 'ROLE_TRUSTED_CLIENT', 7200, 7200, NULL, NULL);
 INSERT INTO `oauth_client_details` VALUES ('tencent', NULL, '123456', 'all,read,write', 'authorization_code,refresh_code', NULL, 'ROLE_TRUSTED_CLIENT', 3600, 3600, NULL, NULL);



-- 用户 角色 关系
CREATE TABLE `user` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`username`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`phone`  varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`email`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_time`  datetime NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1;
 

 



















