

# Mysql 插入语句避免主键重复的方案

# 方案一：ignore

插入时检索主键列表，如存在相同主键记录，不更改原纪录，只插入新的记录。

```sql
INSERT IGNORE INTO
```

ignore关键字所修饰的SQL语句执行后，在遇到主键冲突时会返回一个0，代表并没有插入此条数据。如果主键是由后台生成的（如uuid），我们可以通过判断这个返回值是否为0来判断主键是否有冲突，从而重新生成新的主键key。

这是此ignore关键字比较常用的一种用法。

# 方案二：replace

插入时如发现主键已存在，则替换原记录，即先删除原记录，后insert新记录。

```sql
REPLACE INTO
```

# 方案三：on duplicate key update

插入时如果发现主键已存在，则执行UPDATE更新操作

```sql
INSERT INTO ... ON DUPLICATE KEY UPDATE
```

举例：

```sql
mysql> insert into device values (1,'readonly','yang') ON DUPLICATE KEY UPDATE status ='drain';



Query OK, 2 rows affected (0.00 sec)
```

翻译过来就是：插入一条数据（1,'readonly','yang'），当有重复的主键KEY，那就更新一下status吧。

