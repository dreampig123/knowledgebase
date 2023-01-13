package org.pegcode.common.utils.excel;

/**
 * 自定义的接口ExcelValueFormatter，
 * 它用来实现将不同类型的java属性映射到不同的excel单元格格式。
 * 由于ExcelValueFormatter是个接口，所以你可以实现它，自定义不同的映射策略。
 */
public interface ExcelValueFormatter {

    Object formatValue(Class<?> returnType, Object value);
}
