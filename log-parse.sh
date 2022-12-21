#This part calculates the success rate of all http requests
statusCodes=$(grep 'Response' D:\\repos\\P0-Template-Trevor\\logs\\rollingFile.log | cut -c 12-14)
totalRequests=0
badRequests=0
for code in $statusCodes
do
	((totalRequests++))
	if [ $code -eq 500 ]
	then 
		((badRequests++))
	fi
done
requestSuccessRate=$(echo "scale=2; ($totalRequests-$badRequests)/$totalRequests*100" | bc)
echo The current success rate for all HTTP requests is $requestSuccessRate%.
#This part does calculations with the latency
latencyValues=$(grep 'Response' D:\\repos\\P0-Template-Trevor\\logs\\rollingFile.log | cut -d "." -f 1 | cut -d "," -f 2 | cut -c 17-)
valuesSum=0
valuesCount=0
highestResponseTime=0
for value in $latencyValues
do
	valuesSum=$((valuesSum+value))
	((valuesCount++))
	if [ $value -gt $highestResponseTime ]
	then
		highestResponseTime=$value
	fi
done
averageResponseTime=$(echo "scale=0; $valuesSum/$valuesCount" | bc)
echo The average response time was $averageResponseTime ms, and the highest response time was $highestResponseTime ms.
