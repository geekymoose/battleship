#!/bin/bash
# Date :    May 10, 2015
# Author :  Constantin MASSON
#
# WHAT IS IT FOR
# Manage battleship project compilation / execution / javadoc / jar
# By default, this script compile only
#
# SETUP SCRIPT
# Set permission : chmod 755 exec.sh
# launch script  : ./exec.sh (use -h to display help; it explain how to use it)
#
#
# HELP
# use -h parameter for further information


#*******************************************************************************
# Variables and Constants
#*******************************************************************************
SCRIPT_NAME="${0##*/}"
PWD_EXEC=`pwd`

DOSS_GENERATED="jjgenerated/"
DOSS_BUILD=$DOSS_GENERATED"build/"
DOSS_JAVADOC=$DOSS_GENERATED"javadoc/"
DOSS_DIST=$DOSS_GENERATED"dist/"
DOSS_UML=$DOSS_GENERATED"uml/"

declare param_h #help
declare param_o #javadoc
declare param_j #jar
declare param_x #execute
declare param_u #create UML
declare param_s #also start server
declare param_d #clean repository from tmp files


#*******************************************************************************
# Tools functions
#*******************************************************************************
# Start program 
# Main function
function main(){
    processOptions "$@"
    argstart=$?
    listArguments=${@:$argstart}
    execute
}

# Execute program 
function execute(){
    echo -e "\n********** Start exec script **********"
    echo -e "\n *** Create folder $DOSS_GENERATED"
    create_folder       ; check_error #create folder where to place files

    echo -e "\n *** Build project files in $DOSS_BUILD"
    create_build_class  ; check_error #Create build file : .class

    if [[ ! -z $param_j ]];then
        echo -e "\n *** Create jar file"
        create_jar      ; check_error
    fi
    if [[ ! -z $param_o ]];then 
        echo -e "\n *** Generate JavaDoc"
        create_JavaDoc  ;check_error
    fi
    if [[ ! -z $param_x ]];then
        echo -e "\n *** Execute project"
        execute_project
    fi

    if [[ ! -z $param_s ]];then
        execute_server
    fi
}


# Get the option from the parameters
# Has to be called at the beginning
# @param @ every parameter (Must be given!!)
# @return number of the last option: OPTIND (could be used to get arguments)
function processOptions(){
    while getopts "hjoxuds" optname; do
        case "$optname" in
            "d") clean_repository ;;
            "h") displayHelpAndExit ;;
            "j") param_j=1 ;;
            "o") param_o=1 ;;
            "x") param_x=1 ;;
            "s") param_s=1 ;;
            "u") echo " WARNING : -u is not used anymore!";displayErrorAndExit;;
            "?") displayErrorAndExit 101 $OPTARG ;;
            ":") displayErrorAndExit 102 $OPTARG ;;
            *)   displayErrorAndExit 103 ;;
        esac
    done
    return $OPTIND
}


#*******************************************************************************
# Generate functions
#*******************************************************************************
# Load UML
function create_UML(){
    cd $DOSS_UML
    dia -t jpg ../../documents/uml/dia/*.dia
    cd $PWD_EXEC
}

# Create the generated folder where .class and other data are sent
# If this folder already exists, it will be deleted
function create_folder(){
    if [[ -e $DOSS_GENERATED ]];then
        rm -r $DOSS_GENERATED ; check_error
    fi
    mkdir $DOSS_GENERATED ; check_error
    mkdir $DOSS_BUILD ; check_error
    mkdir $DOSS_UML ; check_error
    mkdir $DOSS_DIST ; check_error
}

# Create javadoc
function create_JavaDoc(){
    option="-Xdoclint:syntax,reference,missing,accessibility"
    javadoc $option -d $DOSS_JAVADOC -sourcepath src/ -subpackages com
}

# Build project, create all .class in build folder
function create_build_class(){
    javac -d $DOSS_BUILD -sourcepath src src/com/battleship/main/Main.java
}

# Execute
function execute_project(){
    java -cp $DOSS_BUILD com.battleship.main.Main &
}

function execute_server(){
    cd server
    ./launchServer.sh &
	cd ..
}

#create manifest file
function create_manifest(){
    echo "Manifest-Version: 1.0" >  "manifest"
    echo "Main-Class: com.battleship.main.Main" >>  "manifest"
    #echo "Class-Path: $DOSS_BUILD"  >>  "manifest"
    echo -e "\n\n\n" >>  "manifest"
}

# Create jar file 
function create_jar(){
    cd $DOSS_BUILD
    create_manifest
    jar -cfm battleship.jar "manifest" com/*
	cd ../..
	cp $DOSS_BUILD/battleship.jar .
}


#*******************************************************************************
# Other Functions
#*******************************************************************************
# Display the script help and exit the script
# @return 1 success value
function displayHelpAndExit {
    #Name
    echo -e "\nNAME"
    echo -e "\t$SCRIPT_NAME - Compile and execute Battleship project"
    
    #SYNOPSIS
    echo -e "\nSYNOPSIS"
    echo -e "\t$SCRIPT_NAME [OPTION]..."
    
    #DESCRIPTION
    echo -e "\nDESCRIPTION"
    echo -e "\tCompile battleship project."
    echo -e "\tAlso give tools for generate javadoc, execute or create jar file"

    #OPTIONS
    echo -e "\nOPTIONS"
    echo -e "\t-h       Display help"
    echo -e "\t-x       Execute"
    echo -e "\t-u       Generate UML"
    echo -e "\t-j       Generate jar file"
    echo -e "\t-o       Generate javadoc"
    echo -e "\t-s       launch server window"
    
    #AUTHOR
    echo -e "\nAUTHOR"
    echo -e "\tWritten by Constantin MASSON <constantin.grinda@gmail.com>\n"
    exit $EXIT_SUCCESS
}


# Display the error message and exit the program
# It could append with bad option etc
# Note that if no $error are given, $1 will be equal ""
# @return 0 fail value
function displayErrorAndExit(){
    case "$1" in
        "101") echo " * $SCRIPT_NAME: invalide option -- '$2'" ;;
        "103") echo " * $SCRIPT_NAME: unkown error" ;;
        "104") echo " * $SCRIPT_NAME: error while executing" ;;
        *);;
    esac
    echo "Try '$SCRIPT_NAME -h' for more information."
    exit $EXIT_FAILURE
}


# Check if last process failed 
# Stop program if error
function check_error(){
    if [[ $? -ne 0 ]];then
        echo "Error"
        exit 1
    fi
}

# Clean repository, means all temporary files will be deleted : 
# generated file, manifest file, build files and jar files
function clean_repository(){
    rm -r $DOSS_GENERATED ; check_error
    rm battleship.jar   ; check_error
    rm manifest         ; check_error
    exit $EXIT_FAILURE
}


# CALL MAIN FUNCTION HERE
main "$@"



