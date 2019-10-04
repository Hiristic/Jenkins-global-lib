//This is an example of how to write scripted pipeline syntax
node {
echo "Job URL: "+currentBuild.absoluteUrl
  stage('Stage 1') {
    echo 'Stage 1'
  }

  stage('Stage 2') {
    echo 'Stage 2'
  }

}
