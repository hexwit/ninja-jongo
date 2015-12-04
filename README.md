# MongoDB module for Ninja framework

## Overview

This module filling the gap between Ninja framework and MongoDB. In a few simple steps 
you can integrate MongoDB support into your application. 
Beside this you get automatic object mapping provided by Jongo and Jackson libraries.

## Setup

Add module dependency to you pom.xml: 

```xml
<dependency>
    <groupId>com.hexwit.ninja.jongo</groupId>
    <artifactId>ninja-jongo-module</artifactId>
    <version>1.0.0</version>
</dependency>
```

Install the module in your conf.Module:

```java
protected void configure() {
    // This installs the NinjaModule and handles the lifecycle
    install(new NinjaJongoModule());
}
```

## Configuration

Add MongoDB connection url to your application.conf file like:

```
mongodb.uri=mongodb://localhost:27017/dbname 
```

See [connection string URI format](https://docs.mongodb.org/manual/reference/connection-string) on the MongoDB official site.

## Usage

To get access to mongo's collections you need just ask for injection, and provide collection name:

```java
@Singleton
public class UserDAO {
    @InjectMongoCollection(name = "users")
    MongoCollection users;

    public User getById(String userId) {
        return users.findOne("{_id: #}", new ObjectId(userId)).as(User.class);
    }
    
    public boolean isEmailRegistered(String email) {
        return users.count("{email: #}", email) > 0;
    }
}
```

## References

 * Ninja: [http://www.ninjaframework.org](http://www.ninjaframework.org)
 * Jongo: [http://jongo.org](http://jongo.org)
 * MongoDB: [https://www.mongodb.org](https://www.mongodb.org)
