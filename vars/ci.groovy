def call(){

pipeline {
    agent any
    stages {
        stage('Build/Execute') {
            steps {
                script{
                    common.complie()
                }
            }
        }
    }
}
}