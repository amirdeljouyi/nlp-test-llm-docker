docker stop llmsuite-1 llmsuite-2 llmsuite-3 llmsuite-4 llmsuite-5
docker stop llmsuite-8-1 llmsuite-8-2 llmsuite-8-3 llmsuite-8-4 llmsuite-8-5
docker stop llmsuite-17-1 llmsuite-17-2 llmsuite-17-3 llmsuite-17-4 llmsuite-17-5
docker system prune -f &
sleep 5
docker image rm llmsuite-experiment-image
docker image rm llmsuite-experiment-image-8