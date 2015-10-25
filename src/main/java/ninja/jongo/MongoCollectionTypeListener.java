package ninja.jongo;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import ninja.jongo.annotations.InjectMongoCollection;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.lang.reflect.Field;

/**
 * Author: Aleksandr Savvopulo
 * Date: 17.10.2015
 */
public class MongoCollectionTypeListener implements TypeListener {
    @Inject
    public Injector injector;

    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        Class<?> clazz = typeLiteral.getRawType();

        while (clazz != null) {
            for (final Field field : clazz.getDeclaredFields()) {
                if (field.getType() == MongoCollection.class &&
                        field.isAnnotationPresent(InjectMongoCollection.class)) {

                    MongoCollectionInjector membersInjector = new MongoCollectionInjector<I>(field);
                    injector.injectMembers(membersInjector);
                    typeEncounter.register(membersInjector);
                }
            }

            clazz = clazz.getSuperclass();
        }
    }
}