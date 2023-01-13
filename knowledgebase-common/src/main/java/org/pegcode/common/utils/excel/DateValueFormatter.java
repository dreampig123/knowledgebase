package org.pegcode.common.utils.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.pegcode.common.utils.DateUtil;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@AllArgsConstructor
public class DateValueFormatter implements ExcelValueFormatter {

    private String dateFormat;

    @Override
    public Object formatValue(Class<?> returnType, Object value) {
        if (returnType.equals(Date.class)) {
            return DateTimeFormatter.ofPattern(dateFormat)
                    .format(DateUtil.dateToLocalDateTime((Date) value));
        } else {
            return value;
        }
    }


}
