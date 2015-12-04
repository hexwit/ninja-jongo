package com.hexwit.ninja.jongo;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import org.jongo.Jongo;

/**
 * NinjaJongoModule provides a bridge to Jongo library,
 * for querying MongoDB.
 *
 * Author: Aleksandr Savvopulo
 * Date: 17.10.2015
 */
public class NinjaJongoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Jongo.class).toProvider(JongoProvider.class);

        MongoCollectionTypeListener listener = new MongoCollectionTypeListener(getProvider(JongoProvider.class));

        bindListener(Matchers.any(), listener);
    }
}
