#!/bin/bash
export LC_NUMERIC="en_US.UTF-8"

while  true
    do
        # cat /proc/diskstats
        printf "\n-------------------------------------------NEW INFO----------------------------------------\n";
        # --Print uptime  in format Day Hour Min Sec--
        totalSecond=$(cat /proc/uptime | awk '{print $1;}');
        
        function dateFormat(){
            SecondInFunction=$( printf "%0.f" $1);

            Days=$(($SecondInFunction / 86400));
            SecondInFunction=$(($SecondInFunction - $Days * 86400)); #subbstract used Seconds

            Hours=$(($SecondInFunction / 3600));
            SecondInFunction=$(($SecondInFunction - $Hours * 3600)); #subbstract used Seconds
            
            Minutes=$(($SecondInFunction / 60));
            SecondInFunction=$(($SecondInFunction - $Minutes * 60)); #subbstract used Seconds

            echo "uptime:" $Days "days" $Hours "hour" $Minutes "min"  $SecondInFunction "sec"
        };
         dateFormat $totalSecond;
        #  --END uptime--

        echo "-------------------------------------------------------------------------------------------";

        # --Print batery level in percentage--
        echo "Battery level:" $(cat /sys/class/power_supply/BAT0/capacity)"%";
        # --END batery level in percentage--

        echo "-------------------------------------------------------------------------------------------";

        # -- system load
        echo  $(cat /proc/loadavg | awk  '{print "Load last 1min:" $1 ;}');
        echo $(cat /proc/loadavg | awk '{print " Load last 5min " $2 ;}');
        echo $(cat /proc/loadavg | awk '{print " Load last 15min " $3 ;}');
        echo $(cat /proc/loadavg | awk '{print " Number of active proccess: " $4 ;}');
        echo $(cat /proc/loadavg | awk '{print " The pid of the moste load proccess: " $5 ;}');
        # --END system load

        echo "-------------------------------------------------------------------------------------------";
        
        # -- Use of memory
        echo $(cat /proc/meminfo | head -n3);
        # -- END of use memory


        # ZAD 2

        # uid=$(awk '/^Uid:/{print $2}' /proc/6873/status)
        # getent passwd "$uid" | awk -F: '{print $1}'
        
        sleep 5s 
    done