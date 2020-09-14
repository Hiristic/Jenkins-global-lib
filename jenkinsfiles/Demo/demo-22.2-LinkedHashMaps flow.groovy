def types = ["demo-00-base-structure",
             "demo-01-stages-declarative-style",
             "demo-02-stages-scripted-style",
             "demo-05-try-catch-finaly"]
def tests = [:]
pipeline {
  agent any

  stages {
    stage('Running tests') {
      steps {
          script {
            echo "Build started: "+RUN_DISPLAY_URL
            tests['Stages'] = {
              stage('initial stages') {
                echo "This job was added manually"
                build "demo-00-base-structure"
              }
            }
            types.each{
              tests[it] = {
                stage(it) {
                  echo "This job was added from a list"
                  build it
                }
              }
            }
            parallel tests
          }
        }
      }
    }
  }

