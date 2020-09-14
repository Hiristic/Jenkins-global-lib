//This is how to handle faults in stages and on pipeline level
pipeline {
  agent {label 'master'}
  stages {

    stage('error') {
      steps {
        script {
          echo "Build started: "+RUN_DISPLAY_URL
          try{
            sh 'exit 0'
          } catch (ex) {
            echo "Exception trace: "+ex
            currentBuild.description = "Try failed"
            error("I am an error message")
          }
          warnError('Script failed!') {
            sh 'exit 0'
          }

        }
      }
    }

    stage('catchError Fail stage only') {
      steps {
        script {
          catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE', message: 'Something failed'){
            sh 'exit 0'
            //assert "aj" == "failed" : "Assert message"
            sh 'ls'

            echo "env.STAGENAME: "+env.STAGE_NAME
            echo "Aj"
            sh 'pwd'
          }
        }
      }
    }
    stage('catchError exception') {
      steps {
        script {
          catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE', message: 'Something failed', catchInterruptions: true){
            echo "in Stage "+env.STAGE_NAME
            //throw new Exception('Is exception cought by catchErrror')
          }
        }
      }
    }
    stage('catchError Flow Interrupt exception') {
      steps {
        script {
          catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE', message: 'Something failed', catchInterruptions: true){
            echo "in Stage "+env.STAGE_NAME
            //timeout(time: 1, unit: 'SECONDS') {
            //  sleep 5
            //}
            //throw new Exception('Is exception cought by catchErrror')
          }
        }
      }
    }
    stage('Throw exception') {
      steps {
        script {
          try{
             timeout(time: 1, unit: 'SECONDS') {
              sleep 5
             }
            //throw new Exception('I dont wont to do...')
          }catch(e){
           // if(e.getMessage().contains('I dont wont')){
           //   echo 'He dont want to'
              //throw e
           // }
            echo "Exception I want "+e.getMessage()
          }
        }
      }
    }
    stage('Post stages') {
      steps {
        script {
          currentBuild.result = 'ABORTED'
        }
      }
      post {
        always {script {echo 'stage.always'}}
        failure {script {echo 'stage.failure'}}
        aborted {script {echo 'stage.aborted'}}
     }
   }
  }
  post {
    always {script {echo 'post.always'}}
    success {script {echo 'post.success'}}
    changed {script {echo 'post.changed'}}
    aborted {script {echo 'post.aborted'}}
    failure {script {echo 'post.failure'}}
  }
}