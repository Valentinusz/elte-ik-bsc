package classdumper;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClassDumper {
    public static String dump(Class<?> cls) {
        String modifiers = Modifier.toString(cls.getModifiers());
        String className = cls.getSimpleName();

        Optional<String> superClass = Optional.ofNullable(cls.getSuperclass())
                                              .map(Class::getName);

        String interfaces = Arrays.stream(cls.getInterfaces())
                                  .map(Class::getName)
                                  .collect(Collectors.joining(", "));

        String fields = Arrays.stream(cls.getDeclaredFields())
                              .map(field -> "    " + Modifier.toString(field.getModifiers()) + " " + field.getType().getName() + " " + field.getName() + ";")
                              .collect(Collectors.joining("\r\n"));

        String methods = Arrays.stream(cls.getDeclaredMethods())
                               .map(method -> "    " + Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getSimpleName() + " " + method.getName() + "(...)" + " { /* method body */ }")
                               .collect(Collectors.joining("\r\n"));

        return  (modifiers.isEmpty() ? "" : modifiers + " ") +
                (cls.isInterface() ? " " : "class ") +
                className + (superClass.map(s -> " extends " + s).orElse("")) +
                (interfaces.isEmpty() ? "" : " implements " + interfaces) +
                " {\r\n" + fields + "\r\n\r\n" + methods + "\r\n}";
    }
}
