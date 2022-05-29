# Hatchways Assessment

Backend Assessment - Blog Posts

## Testing deployed backend

The backend is deployed on Heroku at `https://test-walnut.herokuapp.com`. The API can tested like below:

```bash
$ curl 'https://test-walnut.herokuapp.com/api/posts?tags=tech&sortBy=likes&direction=desc'
```

**Note**: please give upto ten seconds to get the first response, as the apps deployed on Heroku free tier goes to sleep after about an hour of inactivity.

## How to build and run

1. The following are required to build and run this project:

   - JDK version 11.0.11 or above
   - Maven version 3.6.3 or above (if not using maven wrapper)

2. Run the command from project's root to launch the application. This will launch the application on `8080` port.

   ```bash
   $ mvn clean spring-boot:run
   ```

3. The API can be accessed using the following URL:

   ```bash
   $ curl 'http://localhost:8080/api/posts?tags=tech&sortBy=likes&direction=desc'
   ```

4. The units tests can be executed using:

   ```bash
   $ mvn test
   ```
