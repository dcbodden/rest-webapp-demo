This is a demo for a coding exercise. I'll avoid rehashing the requirements as it's already too easy to find other samples with the spec published online.

Build/execute: "gradle bootRun" in the project root.


Highlights here for me:
- This is the first Java I've written in about a year; I figured it would be a good refresher.
- I never used Spring Boot before. Last Java job I had was JBoss with some Spring, but mostly for (badly implemented) IoC stuff.
- This is my first ground-up RESTful service. We were just starting to play with RESTful server side apps when I was doing C# a few years ago, but I worked on visualization in Javascript on the client side mostly.

Problem areas for me:
- I wasted a lot of time on the auto-magical @RequestBody annotation not working. I didn't realize that a default ctor and setter methods were the right way to do that; the tutorials I saw online didn't do much with POST and PUT so I had to dig.
- I lost a lot of time figuring out the request mappings and that yes, you can do GET requests at the same URL, but you have to tell Spring Boot to map via presence of request parameters.
- I could write some basic unit tests for the service, but spent time looking at writing them against the controller. That seems more interesting, but I ran out of time.

General Discussion

With respect to the datastore, it should be obvious this is a toy implementation. I'd probably want to use something like Couchbase to store the documents, and use Couchbase's redundancy to provide resilience. Infinispan would also work, but is overkill. MongoDB is another reasonable choice. The only advantage of doing the in-memory approach here is reduced dev time. I didn't feel like setting up a Couchbase cluster and configuring this app to use it.

Since Spring Boot will generate an executable JAR with embedded webserver, it's super easy to roll this build into a light container with Docker and lauch it as a microservice. Naturally there would need to be some service discovery and log handling added to make it production-friendly, but this would be a much better approach than a heavy VM-based JBoss hosted app, for example.



