def call(){

pipeline {
    agent any
    stages {
        stage('Example/Ex') {
            steps {
                echo 'Hello World'
            }
        }
    }
}
}