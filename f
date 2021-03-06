#!/bin/bash


dc(){
   docker-compose -f docker/dgraph.yml -f docker/dc.yml "$@"
}

up(){
    dc up -d --build
}

down(){
    dc down 
}

term(){
   dc exec $1 bash -c "bash;"
}

fetch_movies(){
    wget "https://github.com/dgraph-io/tutorial/blob/master/resources/1million.rdf.gz?raw=true" -O 1million.rdf.gz -q
}

load_movies(){
    dc exec server bash -c "cd /opt/app; dgraph live -r 1million.rdf.gz --zero zero:5080 -c 1"
}

alter_schema(){
   curl localhost:8080/alter -XPOST -d '
        director.film: uid @reverse .
        genre: uid @reverse .
        initial_release_date: dateTime @index(year) .
        name: string @index(term) @lang .
    '  | python -m json.tool | less
}

"$@"