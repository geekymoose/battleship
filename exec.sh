#!/bin/bash
#
# Compile and then, execute battleship 
# Compiled files (.class) are saved in folder yygenerated



#*******************************************************************************
# Variables and Constants
#*******************************************************************************
PWD_EXEC=`pwd`
DOSS_GENERATED="jjgenerated/"
DOSS_BUILD=$DOSS_GENERATED"build/"
DOSS_UML=$DOSS_GENERATED"uml/"



#*******************************************************************************
# Tools functions
#*******************************************************************************
# Check if last process failed 
# Stop program if error
function checkError(){
    if [[ $? -ne 0 ]];then
        echo "Error"
        exit 1
    fi
}

# Load UML
function loadUML(){
	cd $DOSS_UML
	dia -t jpg ../../documents/uml/dia/*.dia
	cd $PWD_EXEC
}

# Create the generated folder where .class and other data are sent
function createArbo(){
	if [[ -e $DOSS_GENERATED ]];then
	    rm -r $DOSS_GENERATED ; checkError
	fi
	mkdir $DOSS_GENERATED ; checkError
	mkdir $DOSS_BUILD ; checkError
	mkdir $DOSS_UML ; checkError
}

function createJavaDoc(){
	javadoc -Xdoclint:syntax,reference,missing,accessibility -d $DOSS_GENERATED"javadoc" -sourcepath src/ -subpackages com
}

# Execute
function execute(){
	javac -d $DOSS_BUILD -sourcepath src src/com/battleship/main/Main.java
	java -cp $DOSS_BUILD com.battleship.main.Main
}




createArbo
loadUML
createJavaDoc
execute



