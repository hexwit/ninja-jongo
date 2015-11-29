package ninja.jongo;

import com.google.inject.MembersInjector;
import com.google.inject.Provider;
import ninja.jongo.annotations.InjectMongoCollection;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.lang.reflect.Field;

/**
 * Injector implementation responsible for
 * setting proper MongoDBCollection instance to
 * the fields, annotated appropriately.
 *
 * Author: Aleksandr Savvopulo
 * Date: 21.10.2015
 */
public class MongoCollectionInjector<T> implements MembersInjector<T> {
    private Provider<JongoProvider> provider;
    private Field field;
    private String collectionName;

    public MongoCollectionInjector(Provider<JongoProvider> provider, Field field) {
        this.provider = provider;
        this.field = field;
        this.field.setAccessible(true);
        this.collectionName = field.getAnnotation(InjectMongoCollection.class).name();
    }

    public void injectMembers(T instance) {
        try {
            String collectionName = this.collectionName;
            Jongo jongo = provider.get().get();
            MongoCollection collection = jongo.getCollection(collectionName);

            field.set(instance, collection);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
