# JSON Server example

This is just an example of a `SpringBoot` application publishing a very simple REST API,
ready to be deployed in `heroku`

## The tricky parts

### Using JDK 11

In order to deploy the application using `JDK 11`, the `system.properties` file is needed

### The 'binding port' problem

In order to have the API accessible from the URL pointed to by `heroku` on creation of the
application (`heroku create`), the port to bind to by the `SpringBoot` application has to be
dynamically configured in the `application.properties` and `Procfile` files
