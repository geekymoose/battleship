#!/bin/bash

# Creation : 18 March 2015
# Generate the .jpg files from .dia shematics

cd jpg

if [ $? -eq 0 ];then
	dia -t jpg ../dia/*.dia 
fi
