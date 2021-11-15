Sposób I
Sudo apt install rename
> rename 'y/A-Z/a-z/' *

Sposób II
for file in *;
    do 
        if ![-d "$(file)"];
        then
        mv - - "$file" "`echo $file | tr 'A-Z' 'a-z' `";
        fi
    done


Sposób III
Wykorzystująć ,,
for file in *
do
    mv - - "$file" "{$file,,}"
done    