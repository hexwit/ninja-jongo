package com.hexwit.ninja.jongo;

import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import com.hexwit.ninja.jongo.annotations.InjectMongoCollection;
import org.jongo.MongoCollection;

import java.lang.reflect.Field;

/**
 * Type listener registering mongo collection
 * injector for the fields, who has @InjectMongoCollection
 * annotation.
 *
 * Author: Aleksandr Savvopulo
 * Date: 17.10.2015
 */
public class MongoCollectionTypeListener implements TypeListener {
    private Provider<JongoProvider> provider;

    public MongoCollectionTypeListener(Provider<JongoProvider> provider) {
        this.provider = provider;
    }

    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        Class<?> clazz = typeLiteral.getRawType();

        while (clazz != null) {
            for (final Field field : clazz.getDeclaredFields()) {
                if (field.getType() == MongoCollection.class &&
                        field.isAnnotationPresent(InjectMongoCollection.class)) {

                    MongoCollectionInjector membersInjector = new MongoCollectionInjector<I>(provider, field);
                    typeEncounter.register(membersInjector);
                }
            }

            clazz = clazz.getSuperclass();
        }
    }
}
