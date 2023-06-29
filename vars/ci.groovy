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
        mail bcc: '', body: '${component} - has failed /n', cc: '', from: 'algonox1.1@gmail.com', replyTo: '', subject: '${component} - has failed', to: 'algonox1.1@gmail.com'
    }

}
}