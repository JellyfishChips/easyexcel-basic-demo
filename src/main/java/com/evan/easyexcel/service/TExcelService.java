package com.evan.easyexcel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.evan.easyexcel.mapper.TExcelMapper;
import java.util.List;
import com.evan.easyexcel.model.TExcel;
@Service
@Slf4j
public class TExcelService {

    @Resource
    private TExcelMapper tExcelMapper;

    public void testExcel(List<TExcel> list) {
        log.info("data of service:{}", list.toString());
        //tExcelMapper.insert(list.get(0));
        tExcelMapper.insertExcelList(list);
    }

}
