#!/bin/bash
    tableWriteDif=(0 0 0 0 0 0 0 0 0 0)
    tableReadDif=(0 0 0 0 0 0 0 0 0 0)
    writeDi=0;
    readOption=0;
    currentBigestDifWrite=0;
    currentBigestDifRead=0;
    writeDiv=0;
    writeDivOut=0;
    readDiv=0;
    readDivOut=0;
    writeSign="";
    readSign="";
    lastWrite=$(cat /proc/diskstats | awk '{s+=$4} END {print s ;}');
    lastRead=$(cat /proc/diskstats | awk '{s+=$8} END {print s ;}');
    sleep 1s;
    
while true
do
    clear
    actualReads=$(cat /proc/diskstats | awk '{s+=$4} END {print s ;}');
    actualWrite=$(cat /proc/diskstats | awk '{s+=$8} END {print s ;}');

    for ((i=9 ; i > 0; i--))
    do
    if [ $i -gt 0 ]
        then
        tableWriteDif[i]=${tableWriteDif[$((i-1))]}
        tableReadDif[i]=${tableReadDif[$((i-1))]}
    fi
    done
    tableWriteDif[0]=$(($actualWrite - ${lastWrite}))
    lastWrite=$actualWrite;

    tableReadDif[0]=$(($actualReads - ${lastRead}))
    lastRead=$actualReads;

    max=${tableWriteDif[0]}
        for n in "${tableWriteDif[@]}" ; do
            ((n > max)) && max=$n
        done
    currentBigestDifWrite=$max

    max=${tableReadDif[0]}
        for n in "${tableReadDif[@]}" ; do
            ((n > max)) && max=$n
        done
    currentBigestDifRead=$max

    if [ $currentBigestDifWrite -lt 1000 ];
        then
        writeSign="B";
        writeDiv=10;
        writeDivOut=1;
    elif [ $currentBigestDifWrite -lt 1000000 ];
        then
        writeSign="KB";
        writeDiv=10000;
        writeDivOut=1000;
    elif [ $currentBigestDifWrite -lt 1000000000 ];
        then
        writeSign="MB";
        writeDiv=10000000;
        writeDivOut=1000000;
    else
        writeSign="GB";
        writeDiv=10000000000;
        writeDivOut=1000000000;

    fi


    echo "Current Write Speed" $((${tableWriteDif[0]}/$writeDivOut)) $writeSign
    for x in $(seq 0 9)
    do
        if [ $((${tableWriteDif[$x]} / $writeDiv)) -gt 90 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableWriteDif[$x]}/$writeDiv)) -gt 80 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableWriteDif[$x]}/$writeDiv)) -gt 70 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableWriteDif[$x]}/$writeDiv)) -gt 60 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableWriteDif[$x]}/$writeDiv)) -gt 50 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableWriteDif[$x]}/$writeDiv)) -gt 40 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableWriteDif[$x]}/$writeDiv)) -gt 30 ]
        then
        printf '\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableWriteDif[$x]}/$writeDiv)) -gt 20 ]
        then
        printf '\u2500\u2500\u2500\n'
        elif [ $((${tableWriteDif[$x]}/$writeDiv)) -gt 10 ]
        then
        printf '\u2500\u2500\n'
        elif [ $((${tableWriteDif[$x]}/$writeDiv)) -gt 0 ]
        then
        printf '\u2500\n'
        else
        printf '.\n'
        fi
    done
    echo "---------^ 1000" $writeSign

    printf "\n"

    if [ $currentBigestDifRead -lt 1000 ];
        then
        readSign="B";
        readDiv=10;
        readDivOut=1;
    elif [ $currentBigestDifRead -lt 1000000 ];
        then
        readSign="KB";
        readDiv=10000;
        readDivOut=1000;

    elif [ $currentBigestDifRead -lt 1000000000 ];
        then
        readSign="MB";
        readDiv=10000000;
        readDivOut=1000000;

    else
        readSign="GB";
        readDiv=10000000000;
        readDivOut=1000000000;
    fi
    
    echo "Current Read Speed" $((${tableReadDif[0]}/$readDivOut)) $readSign
    for x in $(seq 0 9)
    do
        if [ $((${tableReadDif[$x]} / $readDiv)) -gt 90 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableReadDif[$x]}/$readDiv)) -gt 80 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableReadDif[$x]}/$readDiv)) -gt 70 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableReadDif[$x]}/$readDiv)) -gt 60 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableReadDif[$x]}/$readDiv)) -gt 50 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableReadDif[$x]}/$readDiv)) -gt 40 ]
        then
        printf '\u2500\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableReadDif[$x]}/$readDiv)) -gt 30 ]
        then
        printf '\u2500\u2500\u2500\u2500\n'
        elif [ $((${tableReadDif[$x]}/$readDiv)) -gt 20 ]
        then
        printf '\u2500\u2500\u2500\n'
        elif [ $((${tableReadDif[$x]}/$readDiv)) -gt 10 ]
        then
        printf '\u2500\u2500\n'
        elif [ $((${tableReadDif[$x]}/$readDiv)) -gt 0 ]
        then
        printf '\u2500\n'
        else
        printf '.\n'
        fi
    done
    echo "--------- ^1000" $readSign

    printf "\n"


        # Get the first line with aggregate of all CPUs 
        cpu_now=($(head -n1 /proc/stat)) 
        # Get all columns but skip the first (which is the "cpu" string) 
        cpu_sum="${cpu_now[@]:1}" 
        # Replace the column seperator (space) with + 
        cpu_sum=$((${cpu_sum// /+})) 
        # Get the delta between two reads 
        cpu_delta=$((cpu_sum - cpu_last_sum)) 
        # Get the idle time Delta 
        cpu_idle=$((cpu_now[4]- cpu_last[4])) 
        # Calc time spent working 
        cpu_used=$((cpu_delta - cpu_idle)) 
        # Calc percentage 
        cpu_usage=$((100 * cpu_used / cpu_delta)) 
        
        # Keep this as last for our next read 
        cpu_last=("${cpu_now[@]}") 
        cpu_last_sum=$cpu_sum 
  
        echo "CPU usage at $cpu_usage%" 
        
        for ((i=0; i < $cpu_usage; i++))
        do
            printf "|"
        done
         for ((i=0; i < 99-$cpu_usage ; i++))
        do
            printf "."
        done
        echo ""
        echo "--------------------------------------------------------------------------------------------------^ 100%"

sleep 1s
done