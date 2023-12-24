package com.example.news.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;

@UtilityClass
public class BeanUtils {
    @SneakyThrows
    public void nonNullPropertiesCopy(Object source, Object destination){
        Class<?> clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            if((field.getName().matches("news|comments"))) {
                continue;
            }

            Object value = field.get(source);

            if(value != null) field.set(destination, value);
        }
    }
}
