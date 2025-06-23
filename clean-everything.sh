docker stop llmsuite-1 llmsuite-2 llmsuite-3 llmsuite-4 llmsuite-5
docker system prune -f &
sleep 5
docker image rm llmsuite-experiment-image