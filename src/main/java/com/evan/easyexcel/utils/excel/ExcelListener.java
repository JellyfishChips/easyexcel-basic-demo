package com.evan.easyexcel.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.evan.easyexcel.model.ImportModel;
import com.evan.easyexcel.model.TExcel;
import com.evan.easyexcel.service.TExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Evan Wang
 * @Description 监听类
 * @Date 2019-09-16
 */
@Slf4j
public class ExcelListener<T> extends AnalysisEventListener {
    //可以通过实例获取该值
    private List<Object> dataList = new ArrayList<>();

    private T excelService;

    public ExcelListener() {
    }

    public ExcelListener(T excelService) {
        this.excelService = excelService;
    }


    @Override
    public void invoke(Object object, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        dataList.add(object);
        //handleBusinessLogic();


        //如数据过大，可以进行定量分批处理
        if(dataList.size()>=200){
            handleBusinessLogic();
            dataList.clear();
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //非必要语句，查看导入的数据
        System.out.println("导入的数据 " + dataList.toString());

        TExcelService service = (TExcelService) excelService;
        List<ImportModel> importModels = castList(dataList, ImportModel.class);
        List<TExcel> tExcels = importModels.stream().map(model -> {
            TExcel tExcel = new TExcel();
            tExcel.setAuthor(model.getAuthor());
            tExcel.setBook(model.getBook());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                tExcel.setDate(sdf.parse(model.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tExcel;
        }).collect(Collectors.toList());
        service.testExcel(tExcels);

        //解析结束销毁不用的资源
        dataList.clear();
    }

    //根据业务自行实现该方法，例如将解析好的dataList存储到数据库中
    private void handleBusinessLogic() {
        TExcelService service = (TExcelService) excelService;
        List<ImportModel> importModels = castList(dataList, ImportModel.class);
        List<TExcel> tExcels = importModels.stream().map(model -> {
            TExcel tExcel = new TExcel();
            tExcel.setAuthor(model.getAuthor());
            tExcel.setBook(model.getBook());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                tExcel.setDate(sdf.parse(model.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tExcel;
        }).collect(Collectors.toList());
        service.testExcel(tExcels);
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public static <T> List<T> castList(Object obj, Class<T> clazz)
    {
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>)
        {
            for (Object o : (List<?>) obj)
            {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }
}
