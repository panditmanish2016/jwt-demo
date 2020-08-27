package com.jwt.demo.validator.util;




import java.lang.reflect.Field;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GetFieldValueUtil {
	public Object getFieldValue(Object object, String fieldName) {
		Class<?> clazz = object.getClass();
		Field accessibleField = null;
		Object response = null;
		try {
			accessibleField = clazz.getDeclaredField(fieldName);
			accessibleField.setAccessible(true);
			response = accessibleField.get(object);
		} catch (Exception e) {
			// Look for super class field list
			try {
				accessibleField = clazz.getSuperclass().getDeclaredField(fieldName);
				accessibleField.setAccessible(true);
				response = accessibleField.get(object);
			} catch (Exception e1) {
				try {
					accessibleField = clazz.getSuperclass().getSuperclass().getDeclaredField(fieldName);
					accessibleField.setAccessible(true);
					response = accessibleField.get(object);
				} catch (Exception e2) {
					/* getting the value from nested instance field (only level 1) */
					String splitField[] = fieldName.split("\\.");
					try {
						accessibleField = clazz.getDeclaredField(splitField[0]);
						accessibleField.setAccessible(true);
						Object temp = accessibleField.get(object);
						accessibleField = temp.getClass().getDeclaredField(splitField[1]);
						accessibleField.setAccessible(true);
						response = accessibleField.get(temp);

					} catch (Exception e3) {
						response = null;
						e2.printStackTrace();
					}
				}
			}
		}
		return response;
	}
}

