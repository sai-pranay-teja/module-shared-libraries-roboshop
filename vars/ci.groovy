def call(){

pipeline {
    agent any
    stages {
        stage('Build/Execute') {
            steps {
                sh 'env'
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
}

post{
    failure{
        mail bcc: '', body: '${component} pipeline - has failed /n ${BUILD_URL}', cc: '', from: 'algonox1.1@gmail.com', replyTo: '', subject: '${component} pipeline - has failed', to: 'algonox1.1@gmail.com'
    }

}
}