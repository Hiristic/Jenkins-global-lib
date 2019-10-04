//When overriding only the default lib
@Library('global-lib@develop') _

//When using multiple global libraries
jsl = library (
  identifier: 'lib_name@develop', 
  retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/Hiristic/Jenkins-global-lib.git', 
    traits: [
      [$class: 'jenkins.plugins.git.traits.BranchDiscoveryTrait'], 
      [$class: 'DiscoverOtherRefsTrait', ref: '+refs/changes/*']]]))


pipeline {
  agent any
  stages {
    stage('Where is Jenkins Shared Library cloned') {
      steps {
          echo "See job: "+currentBuild.absoluteUrl
        script {
          sh 'ls -la ../workspace@libs/global-lib'
          sh 'ls -la ../workspace@libs/jenkins-shared-library'
        }
      }
    }
  }
}

