#!/bin/bash
#bash script to stress test AWS load balancer 
#which holds multiple instances of the QuoteServer as a Amazon Machine Image
port=0
quote_num=0
dns="insert dns identifier here"
for number in {1..30}
do
java QuoteClient $dns $quote_num $port_num & 
done
exit 0
