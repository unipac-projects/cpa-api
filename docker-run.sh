#!/bin/bash

docker run -d \ 
	--name cpa-api
	-p 8083:8083 \
	-e DATABASE_HOST=192.168.15.101 \
	-e DATABASE_PORT=3306 \
	-e DATABASE_NAME=uaijugafd3 \
	-e DATABASE_USER=uaijug \
	-e DATABASE_PASSWORD=admin \
indikoj-api/indikoj:latest

docker run -d -p 8083:8083 -e DATABASE_HOST=192.168.15.101 -e DATABASE_PORT=3306 -e DATABASE_NAME=cpa-api -e DATABASE_USER=cpa-api -e DATABASE_PASSWORD=admin cpa-api-api/cpa-api:latest
