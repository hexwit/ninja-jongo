package ninja.jongo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import ninja.utils.NinjaProperties;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.net.UnknownHostException;

/**
 * Author: Aleksandr Savvopulo
 * Date: 17.10.2015
 */
@Singleton
public class JongoProvider implements Provider<Jongo> {
    private MongoClient mongo;
    private DB database;
    private Jongo jongo;

    @Inject
    public JongoProvider(Logger log, NinjaProperties properties) {
        try {
            MongoClientProperties mongoProperties = MongoClientProperties.create(properties);

            this.mongo = new MongoClient(mongoProperties.getMongodbUri());
            this.database = this.mongo.getDB(mongoProperties.getMongodbUri().getDatabase());
            this.jongo = new Jongo(this.database);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Jongo get() {
        return jongo;
    }
}
