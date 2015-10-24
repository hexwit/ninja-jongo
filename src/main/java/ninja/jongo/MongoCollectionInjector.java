package ninja.jongo;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;
import ninja.jongo.annotations.InjectMongoCollection;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.lang.reflect.Field;

/**
 * Author: Aleksandr Savvopulo
 * Date: 21.10.2015
 */
public class MongoCollectionInjector<T> implements MembersInjector<T> {
    @Inject
    private Jongo jongo;
    private Field field;
    private String collectionName;

    public MongoCollectionInjector(Field field) {
        this.field = field;
        this.field.setAccessible(true);
        this.collectionName = field.getAnnotation(InjectMongoCollection.class).name();
    }

    public void injectMembers(T instance) {
        try {
            String collectionName = this.collectionName;
            MongoCollection collection = jongo.getCollection(collectionName);

            field.set(instance, collection);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
