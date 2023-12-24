import java.lang.annotation.*;

@Repeatable(BinaryProperties.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BinaryProperty {
    BinaryPropertyType value();

}


