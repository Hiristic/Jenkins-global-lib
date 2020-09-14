//This is how to handle faults in stages and on pipeline level
pipeline {
  agent {label 'master'}
  triggers {
    // On Gerrit patchset creation
    //gerrit customUrl: '', gerritProjects: [[branches: [[compareType: 'REG_EXP', pattern: '.*']], compareType: 'PLAIN', disableStrictForbiddenFileVerification: false, pattern: '.*']], serverName: 'gerrit', triggerOnEvents: [patchsetCreated()]

    // On changes in Git repo. Can be used if Gerrit is not available or due to lazyness
    //pollSCM '* * * * *'

    //Every minute
    //cron{"* * * * *"}
    
    //Conditional cron
    //cron(JENKINS_URL.contains("jenkins-staging") ? "@daily" : "")
    //cron(JENKINS_URL.contains("jenkins-production") ? "H 0-7 * * *" : "")


    // After others jobs are done
     upstream 'demo-00-base-structure'
  }

  stages {
    stage('On master') {
      steps {
        script {
          cleanWs()
          echo "Build started: "+RUN_DISPLAY_URL
          echo "Running"
        }
      }
    }
  }
}