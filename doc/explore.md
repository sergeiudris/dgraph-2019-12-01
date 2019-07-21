
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



# existing tech

* browser vs desktop
  - desktop has access to file system and OS, but ui (including terminal, file management)
    should operate on data within the system. Everyhing is containers or within containers. 
    From actual OS we need repository and docker
* editor
  - files in the repo represent the system. changing files means changing description(declaration) of the system
  - every engineer wants to chagne code/symbols and see the change in the system
  - the idea of editor for editing files is not enough: what is needed is an inteeface that exposes
    files and the running system as a whole. code should be part of the system
  - it's harder when it is about hardware
* versioning (code)
  - the system might have e.g. 1gb of files that are code, and even more dependencies (like linux)
  - so it should be an option to include these files into a running system
  - so it can be engineered while runnign
  - git and branching logic etc. may be part of the system, but you might want to just edit code locally as usual
  - the key is the ability to have repl to all aprts of the running ssytem, ability to query the sytem and events



# concept appendix 1

* should be about records, comments, whole picture, easy git-like version navigation, commits, diffs
* at the core - desgining, recording, planning, tracking


# questioning information system

* should not 
  - prove
  - fit
  - please
  - be a crotch
* should be
  - product
  - for end-user
  - whole
  - simple 
  - solution
  - smart

* file == project == namespace
  - dealing with records requires complete editor experience (complex)

* possible data domains:
  - records
  - git
  - records & git
  - documents (files)
  - records & text inforamtion
  

* ?import what kind of data? -> system

* if no files:
  - issues (again, confluence/jira are basically text editors)

  comes down to text-editor vs no-text-editor

* if no text editor:
  - systems eventually require TE
  - otherwise data->answer systems
  - no TE + records system = failure
  - text editing cases:
    - terminal
    - anything/anywhere in ui
    - keyboard is text editing
  - only forms scenario:
    - issues system (primitive)

* data->answer system, data domains:
  - git data/management (like kraken - not good at all, bad solution/whole-new-layer ratio
