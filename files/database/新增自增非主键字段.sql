





无自增字段的表，添加一个非主键的自增字段

ALTER TABLE xj_tysf_user
ADD COLUMN uid  bigint NOT NULL AUTO_INCREMENT AFTER row_id,
ADD INDEX uid (uid) ;



// 测试pwd md5（q2222222） =  DDB5DAE482528387954E1A3BC7AF2CA3

菜单id： 15- 25







