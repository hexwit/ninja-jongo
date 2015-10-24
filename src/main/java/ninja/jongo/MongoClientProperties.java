package ninja.jongo;

import com.mongodb.MongoClientURI;
import ninja.utils.NinjaProperties;

/**
 * Ninja configuration transformer.
 * Reading ninja properties and converting it
 * to mongo specific configuration.
 *
 * Author: Aleksandr Savvopulo
 * Date: 17.10.2015
 */
public final class MongoClientProperties {
    private static final String MONGODB_URI = "mongodb.uri";
    private MongoClientURI mongoUri;

    private MongoClientProperties() {

    }

    /**
     * Factory method for creating mongo configuration instance.
     *
     * @param ninjaProperties read from application.conf
     * @return instance of mongo configuration
     */
    public static MongoClientProperties create(NinjaProperties ninjaProperties) {
        MongoClientProperties clientProperties = new MongoClientProperties();

        clientProperties.mongoUri = new MongoClientURI(ninjaProperties.getOrDie(MONGODB_URI));

        return clientProperties;
    }

    /**
     * Mongo connection URI.
     * @see <a href="https://docs.mongodb.org/manual/reference/connection-string">Full URI specification</a>
     * @return connection URI
     */
    public MongoClientURI getMongodbUri() {
        return mongoUri;
    }
}
