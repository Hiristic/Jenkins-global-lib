#!/usr/bin/env groovy
pipeline {
  agent any
  stages {
    stage('Stage 1') {
      steps {
        echo "Job URL: "+currentBuild.absoluteUrl
        script {
          sh 'df -h'
          echo 'Stage 1'
        }
      }
    }

    stage('Stage 2') {
      steps {
        script {
          echo 'Stage 2'
          }
        }
      }
    }
  }
