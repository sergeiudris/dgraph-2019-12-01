
* dgraph (or any db) is a powerful tool for data access and querying
* but it is not a serach engine
* and it does not allow for bloodflow - all the events that happen and actually make system go
* for events, kafka is an option, as it persists messages
* the system functions and flows and db is the store at the basis, where processed data goes
* to succed with the system, make engineering experience engaging and benefitial (not detrimental), 
  a clear design is needed around events, monitoring, services, deployment
* with such a system, you can use a number of databased for end data access

---
# 'requests'

* i don't like requests, i feel streams of events w/ socket connection are more useful for an interactive system
* if ui generates event (say query):
  - it should pass through middleware, colecting needed permissions etc.
  - it get's into a broadcast-only netral queue-proxy and gets sent into the mq
  - it gets read by a service, permissions are evaluated (from db)
  - a next event, containing the original or ref, gets sent
  - another service queries data ... and fail
  does not work, because no way to send data back with this approach

* request are needed. it is a standard. a plugin may make a request
  it is better to abstract, so that they were added to event stream
* if ui generates event (say query) 2:
  - ui middleware
  - socket data and request responces should be joined into one
  - in code, requests/queries/events are indistinguashabe - it is an event
  - ui captures only events
  - ui engine makes requests when event is about getting data
  - or ui engine sends events through socket
  - socket message and request response are the same, processed the same
  - the request is needed to get data
  - socket is needed to register events and live updates

  - but hen again, wy not socket for all data ? plugins can make requests, because langage allows it
  - ah, know it: services might be added to answer questions 
  - but then again: a service might get a request but use socket to get answer form the system 
    and then send response to pending request
  unclear

* should allow both res and sockets, but use messaging standard - one unifying "language" of the system
* if ui generates event (say query) 3:
  - request is amde and data applied to state
  - events can be synchronous (like sagas)
  - when socket, when request ?
  - event groups, groups of groups etc. - get only last response
  - event group is like a procedure with a resulting event
  - parallel event processing (like Promise.all)
  - cancelling
  - progress, status
  - replaying from _ to _
  - searching events
  - 
  - build system, emit events into kafka, stream into dgraph, show in ui the graph of events in the system

* if ui generates event (say query) 4:
  - 
