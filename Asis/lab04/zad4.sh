 #!/bin/bash

# monitor.sh - Monitors a web page for changes
# sends an email notification if the file change

URL=$1

for (( ; ; )); do
    mv new.html old.html 2> /dev/null
    curl $URL -L --compressed -s > new.html
    DIFF_OUTPUT="$(diff new.html old.html)"
    if [ "0" != "${#DIFF_OUTPUT}" ]; then
        xmessage "Changes are avilable"
        sleep $2
    fi
done