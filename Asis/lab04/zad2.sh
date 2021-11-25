# Kacper Zawojski 261692
#!/bin/bash
for PID in $(ls -l /proc | awk '{print $9}' | grep -Eo '[0-9]{1,9}' | sort -n );
    do
        #  PRINT USER
        uid=$(awk '/^Uid:/{print $2}' /proc/$PID/status)
            getent passwd "$uid" | awk -F: '{print "User: " $1}'
        # ---END PRINT USER

        # PRINT PID AND PPID   
        echo "PID:" $PID $(cat /proc/$PID/status | awk ' NR==7 {print "; PPID: " $2 }')
        # ---END PID AND PPID

        #---Print TTY
        TTY=$(cat /proc/$PID/stat | awk '{print $7}')
        echo "TTY: " $TTY
        #--END Print TTY

        # ---Print Stat
        STAT=$(cat /proc/$PID/stat | awk '{print $3}')
        echo "Stat: " $STAT
        # --END PRINT STAT

        # ---Print Start Time
        UTIME=$(cat /proc/$PID/stat | awk '{print $14}')
        STIME=$(cat /proc/$PID/stat | awk '{print $15}')
        # STARTTIME=$(cat /proc/$PID/stat | awk '{print $22}')
        CLK_TCK=$(getconf CLK_TCK)
        TTIME=$((UTIME + STIME))
        TIME=$((TTIME / CLK_TCK))
        echo "Wokr time: "$TIME"s"
        #END ---Print Start Time

        # --MEM use 18
        usedMem=$(awk '{print $1}' /proc/$PID/statm)
        total_memory=$(awk 'NR==1 {print $2}' /proc/meminfo)
        memory_usage=$(echo "scale=2; $usedMem*100/$total_memory" | bc)
        echo "%MEM: "$memory_usage
        # --END MEM use

        # --Count of files
        echo "NUMBER OF OPEN FILES:" `ls /proc/$PID/fd | wc -l`
        # --END Count of files

        # --Print COMMAND
        CML=$(cat /proc/$PID/cmdline )
        echo "CML: $CML" 
        # --END command

        echo "-------------------------------------------------------------------------------------------";
    done