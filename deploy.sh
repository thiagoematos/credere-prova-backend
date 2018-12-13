gradle clean
gradle build
heroku login registry.heroku.com
heroku container:push web
heroku container:release web
