pipeline {
  agent {label 'master'}
  stages {
    stage('On master') {
      steps {
        script {
          echo "Running on master"
          sh 'pwd'
          echo "Created workspaces: "+BUILD_URL+"ws/"
        }
      }
    }
    stage("On slave") {
      agent {label 'slave'}
      steps {
        script {
          echo "Running on slave"
          sh 'pwd'
          sh 'touch aj'
        }
      }
    }
    stage("On slave custom unique workspace") {
      agent {label {label 'slave'
             customWorkspace "${env.JOB_NAME}/${env.BUILD_NUMBER}/Custom"}}
      steps {
        script {
          echo "Running on slave with custom workspace"
          sh 'pwd'
        }
      }
    }
  }
}
