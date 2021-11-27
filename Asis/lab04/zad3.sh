#!/bin/bash

mkdir -p cats
cat=`curl --silent https://api.thecatapi.com/v1/images/search | jq -r '.[] | .url'`
extension=${cat##*.}
curl --silent $cat > "./cats/randomcat.${extension}"
catimg -r 2 "./cats/randomcat.${extension}"
echo `curl --silent http://api.icndb.com/jokes/random | jq -r '.value | .joke '`
rm -r ./cats