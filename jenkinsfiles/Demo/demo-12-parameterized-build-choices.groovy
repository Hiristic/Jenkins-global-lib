pipeline {
  agent any

  parameters {
    choice(
      description: 'Where are the servers located',
      name: 'Lab_choise',
      choices: ['Li', 'Ki']
    )

    string(
      description: 'Write whatever you want',
      name: 'Lab_comment',
      defaultValue: 'whatever',
      trim: false
    )
  }

  stages {
    stage("What") {
      steps {
        echo "selectedEnvironment: ${params.Lab_comment}"
        echo "Job URL: "+currentBuild.absoluteUrl
      }
    }
  }
}
