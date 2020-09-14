pipeline {
  agent any
  stages {

    stage('Stage 1') {
      steps {
        script {
          RUN_STAGE_2 = true
          FLAG_A = true
          FLAG_B = false
        }
      }
    }

    stage('Stage 2') {
      when {
        expression { RUN_STAGE_2 }
        anyOf {
          expression { FLAG_A }
          expression { FLAG_B }
        }
        //expression { FLAG_A || FLAG_B} 
        //beforeAgent true
      }
      steps {
        script {
          echo 'Stage 2'
        }
      }
    }

    stage('Stage3') {
      when {
         //expression { currentBuild.result == 'SUCCESS' } //Wont work verdict SUCCESS is only set at the end
        expression {!currentBuild.result}
        //expression {!currentBuild.result && RUN_STAGE_2} 
      } 
      steps {
        script {
          echo 'Stage 3'
        }
      }
    }
  }
}
