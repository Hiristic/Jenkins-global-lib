def py_image

timeout(time: 30, unit: 'MINUTES') {
    node ('slave') {
        stage('Checkout') {
            cleanWs()
            git "https://github.com/Hiristic/Jenkins-global-lib.git"
        }
           
        stage('Build image') {
            dir("python"){
                py_image = docker.build("py_image", ".")
            }
            // To be used when Docker image is not built locally
            // d_image = docker.image('py_image')
            echo "Docker image ID: " + py_image.id
        }

        stage('Start container') {
        // Like run except running inside container. Also stops and removes the container as soon as its body exits.
            script {
                echo "On server"
                sh 'python3 --version'
                py_image.inside() {
                    stage ('Run py script') {
                        echo "Inside container"
                        sh 'python3 --version'
                    }
                }
            }
        }
    }
}
