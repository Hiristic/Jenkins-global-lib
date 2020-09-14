USE=`df -h |grep xvda1 | awk '{ print $5 }' | cut -d'%' -f1`
if [ $USE -gt $MAX_LIMIT ]; then
  echo "Max limit: $MAX_LIMIT% exceeded. Current usage is: $USE%"
  exit 1
fi