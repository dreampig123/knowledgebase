package org.pegcode.common.utils.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class ExcelUtil {
    private static ThreadLocal<ExcelValueFormatter> valueFormatter = ThreadLocal
            .withInitial(() -> new DateValueFormatter("yyyy-MM-dd"));

    public static void setExcelValueFormatter(ExcelValueFormatter excelValueFormatter) {
        valueFormatter.set(excelValueFormatter);
    }

    public static <E> void writeToExcel(List<E> list, Class<E> clazz, String fileName) throws InvocationTargetException, IllegalAccessException {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        List<Object[]> dataList = new ArrayList<>();
        Field[] fields = getAllFields(clazz);
        Map<String, Method> fieldMethodMap = buildFieldMethodMap(clazz);
        sortMethodMap(fields, fieldMethodMap);
        Map<String, String> fieldTitleMap = buildFieldTitleMap(clazz, fieldMethodMap);
        List<Map.Entry<String, Method>> methodEntrySet = new ArrayList<>(fieldMethodMap.entrySet());
        int addMark = 0;
        int itemSize = fieldTitleMap.size();
        String[] titleArr = new String[itemSize];
        for (E obj : list) {
            Object[] item = new Object[itemSize];
            for (int i = 0; i < methodEntrySet.size(); i++) {
                Map.Entry<String, Method> methodEntry = methodEntrySet.get(i);
                String field = methodEntry.getKey();
                if (addMark < itemSize) {
                    titleArr[addMark] = fieldTitleMap.get(field);
                    addMark++;
                }
                Method method = methodEntry.getValue();
                Object value = formatValue(method, obj, valueFormatter.get());
                if (value != null) {
                    item[i] = value;
                }
            }
            dataList.add(item);
        }
        writeObjectToExcel(dataList, titleArr, fileName);
    }

    private static Object formatValue(Method method, Object obj,
                                      ExcelValueFormatter excelValueFormatter)
            throws InvocationTargetException, IllegalAccessException {
        Object value = method.invoke(obj);
        if (value == null) {
            return null;
        }
        if(excelValueFormatter == null) {
            return value;
        }
        Class<?> returnType = method.getReturnType();
        return excelValueFormatter.formatValue(returnType, value);
    }

    private static <E> Map<String, Method> buildFieldMethodMap(Class<E> clazz) {
        List<Method> getMethods = Arrays.stream(clazz.getMethods())
                .filter(
                        method -> method.getName().startsWith("get") && !method.getName().equals("getClass"))
                .collect(
                        Collectors.toList());
        Map<String, Method> fieldMethodMap = new LinkedHashMap<>();
        for (Method getMethod : getMethods) {
            String m = getMethod.getName().replace("get", "");
            String field = m.substring(0, 1).toLowerCase() + m.substring(1);
            fieldMethodMap.put(field, getMethod);
        }
        return fieldMethodMap;
    }

    public static <E> Field[] getAllFields(Class<E> clazz){
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = (Class<E>) clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    private static <E> Map<String, String> buildFieldTitleMap(Class<E> clazz,
                                                              Map<String, Method> fieldMethodMap) {
        Map<String, String> fieldTitleMap = new LinkedHashMap<>();
        Field[] fields = getAllFields(clazz);
        Arrays.stream(fields).forEach(field -> {
            if (fieldMethodMap.containsKey(field.getName())) {
                ExcelTitle excelTitle = field.getAnnotation(ExcelTitle.class);
                String title = excelTitle == null ? field.getName() : excelTitle.value();
                fieldTitleMap.put(field.getName(), title);
            }
        });
        return fieldTitleMap;
    }

    private static void writeObjectToExcel(List<Object[]> list, String[]
            excelTitle, String fileName) {
        //在内存中创建Excel文件
        Workbook workbook;
        if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else {
            throw new IllegalArgumentException("fileName not legal");
        }
        Sheet sheet = workbook.createSheet();
        //标题行
        Row titleRow = sheet.createRow(0);
        for (int i = 0; i < excelTitle.length; i++) {
            titleRow.createCell(i).setCellValue(excelTitle[i]);
        }
        //创建数据行并写入值
        for (Object[] dataArr : list) {
            int lastRowNum = sheet.getLastRowNum();
            Row dataRow = sheet.createRow(lastRowNum + 1);
            for (int i = 0; i < dataArr.length; i++) {
                Cell cell = dataRow.createCell(i);
                Object cellValue = dataArr[i];
                if(cellValue != null) {
                    setCellValue(cellValue, cell);
                }
            }
        }
        //创建输出流对象
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            log.error("file not found", e);
        }
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            log.error("write to file failed", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ignore) {
                }
            }
        }
    }

    private static void setCellValue(Object cellValue, Cell cell) {
        if (cellValue instanceof Boolean) {
            cell.setCellValue((boolean) cellValue);
        } else if (cellValue instanceof String) {
            cell.setCellValue(cellValue.toString());
        } else if (cellValue instanceof Double || cellValue instanceof Integer
                || cellValue instanceof Long) {
            cell.setCellValue(Double.valueOf(cellValue.toString()));
        } else if (cellValue instanceof Date) {
            cell.setCellValue((Date) cellValue);
        } else if (cellValue instanceof Calendar) {
            cell.setCellValue((Calendar) cellValue);
        } else if (cellValue instanceof RichTextString) {
            cell.setCellValue((RichTextString) cellValue);
        } else {
            cell.setCellValue(cellValue.toString());
        }
    }

    private static void sortMethodMap(Field[] fields, Map<String, Method> fieldMethodMap) {
        Set<String> fieldSet = fieldMethodMap.keySet();
        List<Field> fieldList = Arrays.stream(fields).filter(e -> fieldSet.contains(e.getName()))
                .collect(Collectors.toList());
        fields = fieldList.toArray(new Field[]{});
        Arrays.sort(fields, (o1, o2) -> {
            Order order1 = o1.getAnnotation(Order.class);
            Order order2 = o2.getAnnotation(Order.class);
            if (order1 == null && order2 == null) { //均不含注解时不排序
                return 0;
            }
            if (order1 == null) { //order1 == null && order2 != null 仅有一个含有注解时，默认排到不含注解的后面
                return -1;
            }
            if (order2 == null) { //order1 != null && order2 == null 仅有一个含有注解时，默认排到不含注解的后面
                return 1;
            }
            return order1.value() - order2.value();//均含有注解时，按照注解值从小到大排序
        });
        Map<String, Method> sortedMethodMap = new LinkedHashMap<>();
        Arrays.stream(fields).forEach(e -> {
            String key = e.getName();
            sortedMethodMap.put(key, fieldMethodMap.get(key));
        });
        fieldMethodMap.clear();
        fieldMethodMap.putAll(sortedMethodMap);
    }


    public static void main(String[] args) {
        try {
            List<Student> students = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                students.add(new Student(i, "member" + i, i * 55D, new Date()));
            }
            writeToExcel(students, Student.class, "F:\\test.xlsx");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    public static class Student {
        @Order(1)
        @ExcelTitle("id")
        private Integer id;

        @Order(2)
        @ExcelTitle("姓名")
        private String name;

        @Order(3)
        @ExcelTitle("薪水")
        private Double salary;

        @Order(3)
        @ExcelTitle("生日")
        private Date birthDay;
    }

}
