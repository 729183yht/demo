package cn.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 字符串转日期
* */
public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        if(s==null){

        }
        DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
        try {
            //字符串转日期

            return df.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException("转换出错");
        }


    }
}
