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
    sh c dc exec $1 bash -c "bash;"
}

"$@"