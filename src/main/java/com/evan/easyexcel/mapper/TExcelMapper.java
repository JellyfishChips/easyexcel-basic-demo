package com.evan.easyexcel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.evan.easyexcel.model.TExcel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TExcelMapper extends BaseMapper<TExcel> {

    void insertExcelList(@Param("list") List<TExcel> dataList);
}
