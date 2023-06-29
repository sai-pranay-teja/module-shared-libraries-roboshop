def call(){

pipeline {
    agent any
    stages {
        stage('Build/Execute') {
            steps {
                sh 'exit 1'
                script{
                    common.complie()
                }
            }
        }

        stage('Code Quality') {
            steps {
                script{
                    common.codequality()
                }
            }
        }
    }
    post{
        failure{
            mail body: '${component} pipeline - has failed /n ${BUILD_URL}', from: 'algonox1.1@gmail.com', subject: '${component} pipeline - has failed', to: 'algonox1.1@gmail.com'
    }

}
}
}