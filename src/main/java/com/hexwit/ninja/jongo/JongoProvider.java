package com.hexwit.ninja.jongo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import ninja.utils.NinjaProperties;
import org.jongo.Jongo;

import java.net.UnknownHostException;

/**
 * Provider exposes access to the mongo client,
 * and other related entities.
 *
 * Author: Aleksandr Savvopulo
 * Date: 17.10.2015
 */
@Singleton
public class JongoProvider implements Provider<Jongo> {
    private MongoClient mongo;
    private DB database;
    private Jongo jongo;

    @Inject
    public JongoProvider(NinjaProperties properties) {
        try {
            MongoClientProperties mongoProperties = MongoClientProperties.create(properties);

            this.mongo = new MongoClient(mongoProperties.getMongodbUri());
            this.database = this.mongo.getDB(mongoProperties.getMongodbUri().getDatabase());
            this.jongo = new Jongo(this.database);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public Jongo get() {
        return jongo;
    }
}
