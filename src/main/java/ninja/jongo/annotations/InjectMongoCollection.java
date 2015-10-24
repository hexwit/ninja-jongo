package ninja.jongo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that injects a MongoDB collection with name specified.
 *
 * Author: Aleksandr Savvopulo
 * Date: 17.10.2015
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectMongoCollection {
    /**
     * Name of the collection you would like to have injected.
     *
     * @return a name defined
     */
    String name();
}
