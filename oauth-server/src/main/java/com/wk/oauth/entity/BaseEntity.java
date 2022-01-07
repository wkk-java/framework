package com.wk.oauth.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8388417013613884411L;

    private Timestamp createTime;

    private Timestamp updateTime;

    private int createBy;

    private int updateBy;
}
