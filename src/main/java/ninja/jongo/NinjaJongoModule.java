package ninja.jongo;

import com.google.inject.AbstractModule;
import com.google.inject.Binding;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.matcher.Matcher;
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

        MongoCollectionTypeListener listener = new MongoCollectionTypeListener();
        requestInjection(listener);

        bindListener(Matchers.any(), listener);
    }
}
