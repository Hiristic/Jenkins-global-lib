// docs: https://jenkins.io/doc/pipeline/steps/credentials-binding/

pipeline {
  agent any
  stages {

    stage('Username and Password') {
      steps {
        script {
          withCredentials([
            usernamePassword(credentialsId: 'server_login',
              usernameVariable: 'username',
              passwordVariable: 'password')
          ]) {
            echo 'Credentials are accessable but not visible:'
            echo 'username = ' + username 
            echo 'password = ' + password
          }
        }
      }
    }

    stage('Environment credentials') {
      environment {server_cred = credentials('server_login')}
      steps {
        script {
          echo 'Credentials are accessable but not visible:'
          echo 'username = ' + env.server_cred_usr
          echo 'password = ' + env.server_cred_psw

          //host_ip = "3.124.189.223"
          //comand = "ls -la"
          //def sh_script = "sshpass -p " + env.server_cred_psw + " ssh " + env.server_cred_usr + "@" + address + " -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null " + command
          //output = sh(returnStdout: true, script: sh_script)
        }
      }
    }
  }
}
