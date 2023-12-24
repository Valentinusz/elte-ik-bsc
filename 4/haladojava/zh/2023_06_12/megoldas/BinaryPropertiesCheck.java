import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import org.apache.commons.lang3.ClassUtils;

public class BinaryPropertiesCheck {
    private static <T> boolean checkProperty(BinaryPropertyType type, Method method, T v1, T v2, T v3) throws InvocationTargetException, IllegalAccessException {
        switch (type) {
            case ASSOCIATIVE, COMMUTATIVE -> {
                return method.invoke(null, method.invoke(null, v1, v2), v3) == method.invoke(null, v1, method.invoke(null, v2, v3));
            }
            case NONASSOCIATIVE, NONCOMMUTATIVE -> {
                return method.invoke(null, method.invoke(null, v1, v2), v3) != method.invoke(null, v1, method.invoke(null, v2, v3));
            }
        }
        return false;
    }

    public static <T> boolean checkBinaryProperties(Class<?> clazz, T v1, T v2, T v3) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(BinaryProperty.class) || method.isAnnotationPresent(BinaryProperties.class))
                .filter(method -> method.getParameterCount() == 2)
                .filter(method -> {
                    Class<?> returnType = method.getReturnType();
                    return Arrays.stream(method.getParameters()).allMatch(parameter -> ClassUtils.isAssignable(returnType, parameter.getType(), true));
                })
                .allMatch(method -> {
                    try {
                        if (method.isAnnotationPresent(BinaryProperty.class)) {
                            return checkProperty(method.getAnnotation(BinaryProperty.class).value(), method, v1, v2, v3);
                        } else if (method.isAnnotationPresent(BinaryProperties.class)) {
                            return Arrays.stream(method.getAnnotation(BinaryProperties.class).value())
                                    .allMatch(binaryProperty -> {
                                        try {
                                            return checkProperty(binaryProperty.value(), method, v1, v2, v3);
                                        } catch (InvocationTargetException | IllegalAccessException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                });
    }
}
