# Kacper Zawojski 261692
#!/bin/bash
export LC_NUMERIC="en_US.UTF-8"

while  true
    do
        clear
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
        print "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"
        sleep 1s 
    done