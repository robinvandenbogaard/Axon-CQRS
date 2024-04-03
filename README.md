This tiny monolithic application was created to experiment with Axon's @AggregateMembers and I wanted a external service with async communications to help me weaponize against REST services and all the complexity/boilerplating that brings.

This application contains 4 modules:
 - **nl.robinthedev.app.post.aggregate.*** -- Aggregate for Posts with Comments
 - **nl.robinthedev.app.post.command.*** -- A Rest API to create Posts and Comments and update Comments. This module only fires Commands to Axon.
 - **nl.robinthedev.app.profanity.*** -- A Profanity service that calculates a score for comments. Its a QueryHandler for Async goodness levering Axon's messaging.
 - **nl.robinthedev.app.post.projection.*** -- A Projection service that updates a database with events from the Aggregates and a simple listing Endpoint to list all posts with their messages.

## Build the application image (needed in docker-compose)

```shell
mvn package jib:dockerBuild
```

## Start up docker env

## Run spammer

In folder `src/main/docker`

```shell
docker compose up --build spammer 
```