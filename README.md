This tiny monolithic application was created to experiment with Axon's @AggregateMembers and I wanted a external service with async communications to help me weaponize against REST services and all the complexity/boilerplating that brings.

This application contains 4 modules:
 -> Aggregate for Posts with Comments
 -> A Rest API to create Posts and Comments and update Comments. This module only fires Commands to Axon.
 -> A Profanity service that calculates a score for comments. Its a QueryHandler for Async goodness levering Axon's messaging.
 -> A Projection service that updates a database with events from the Aggregates and a simple listing Endpoint to list all posts with their messages.
