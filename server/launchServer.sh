#!/bin/bash 
# 
# Start Battleship sever



# Create build folder
create_build_folder(){
	if [[ -e "build" ]];then 
		rm -r build
	fi
	mkdir build
}

# Create jar file (Create manifest before)
function create_jar(){
    echo "Manifest-Version: 1.0" >  "manifest.mf"
    echo "Main-Class: serverbattleship.ServerBattleship" >>  "manifest.mf"
    echo "Class-Path: build/"  >>  "manifest.mf"
	jar -cfm battleshipServer.jar "manifest.mf" build/*
}

# compile and execute
function comp_and_execute(){
	javac -d build -sourcepath src src/serverbattleship/ServerBattleship.java
	java -cp build/ serverbattleship.ServerBattleship &
}


create_build_folder
comp_and_execute
create_jar