package com.evan.easyexcel.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_excel")
public class TExcel implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("`date`")
    private Date date;

    @TableField("author")
    private String author;

    @TableField("book")
    private String book;

    private static final long serialVersionUID = 1L;
}