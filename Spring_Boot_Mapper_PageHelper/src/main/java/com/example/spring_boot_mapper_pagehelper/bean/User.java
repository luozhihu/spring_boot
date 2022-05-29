package com.example.spring_boot_mapper_pagehelper.bean;

import java.util.Date;
import javax.persistence.*;

/**
 * 表名：T_USER
*/
@Table(name = "T_USER")
public class User {

    //@Id 标注用于声明一个实体类的属性映射为数据库的主键列。该属性通常置于属性声明语句之前，可与声明语句同行，也可写在单独行上。
    @Id
    @Column(name = "ID")
    //因为这里数据库试用的是Oracle，其没有主键自动自增的功能，
    // 这里先将@GeneratedValue(strategy = GenerationType.IDENTITY)去掉，
    // 主键的生成下面会介绍到。生成的主键是BigDecimal类型的，我们将其改为Long类型。
    //@GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定。默认情况下，JPA 自动选择一个最适合底层数据库的主键生成策略：SqlServer对应identity，MySQL 对应 auto increment。
    //在javax.persistence.GenerationType中定义了以下几种可供选择的策略：
    //–IDENTITY：采用数据库ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
    //–AUTO： JPA自动选择合适的策略，是默认选项；
    //–SEQUENCE：通过序列产生主键，通过@SequenceGenerator 注解指定序列名，MySql不支持这种方式
    //–TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWD")
    private String passwd;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "STATUS")
    private String status;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    /**
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}