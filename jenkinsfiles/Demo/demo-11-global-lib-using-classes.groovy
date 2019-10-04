//Get patch set version from global lib
//@Library('global-lib@develop') _
//jsl = library('global-lib@develop')

//Get patch set from external lib
jsl = library (
  identifier: 'lib_name@develop', 
  retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/Hiristic/Jenkins-global-lib.git', 
    traits: [
      [$class: 'jenkins.plugins.git.traits.BranchDiscoveryTrait'], 
      [$class: 'DiscoverOtherRefsTrait', ref: '+refs/changes/*']]]))

def build = jsl.com.jenkins.Build.new(this)

pipeline {
  agent any
  stages {

    stage('Init') {
      steps {
        script {
          build.setBuildDescription(
            title: "#${env.BUILD_NUMBER} My build title.",
            description: 'My build description.'
          )
        }
      }
    }

    stage('Build') {
      steps {
        script {
          echo 'Building...'
        }
      }
    }

    stage('Unit Test') {
      steps {
        script {
          echo 'Unit Testing...'
        }
      }
    }

    stage('Deploy') {
      steps {
        script {
          echo 'Deploying...'
        }
      }
    }
  }
}
