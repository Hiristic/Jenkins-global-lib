pipeline {
  agent any
  stages {
    stage('Shell commands') {
      steps {
        script {
            //Run single shell cmd
            sh "ls -la"
            
            //Different shells
            sh "servername='server'"
            try {
                sh "echo $servername"
            }catch (Exception e) {
                echo "Failed because it is executed in different shells"+e
            }

            //Running multiple shell commands in same shell
            sh '''
                servername='server'
                echo $servername
            '''

            //Run shell cmd and return output as a txt string
            output = sh(returnStdout: true, script: "ls -la")
            echo "Output is: "+output

            //Parse output
            if(output.contains(".")){
                echo "Output "+output+" contains ."
            }

            //return error codes
            status = sh(returnStatus: true, script: "ls -la")

            //Run shell script from repo
            git "https://github.com/Hiristic/Jenkins-global-lib.git"
            dir('shell'){
                sh "chmod +x usage.sh"
                sh "./usage.sh"
            }      
        }
      }
    }
  }
}
