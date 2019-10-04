//All methods in repository are replaced with patch set version
@Library('global-lib@develop') _

//Get patch set version as an external library
jsl = library('global-lib@develop')

/*
//Get patch set from external lib
jsl = library (
  identifier: 'lib_name@refs/changes/35/5961035/3', 
  retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/Hiristic/Jenkins-global-lib.git', 
    traits: [
      [$class: 'jenkins.plugins.git.traits.BranchDiscoveryTrait'], 
      [$class: 'DiscoverOtherRefsTrait', ref: '+refs/changes/*']]]))
*/


subSteps([jsl: jsl])
  .build(
    command: 'My build command'
  )
  .unitTest(
    command: 'My unit test command'
  )
  .deploy(
    command: 'My deploy command'
  )
