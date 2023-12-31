def call() {
pipeline {
    agent any

    options {
        ansiColor('xterm')
    }

    
    parameters {
        string(name: 'APP_VERSION', defaultValue: '', description: 'Choose the App Version')
        string(name: 'COMPONENT', defaultValue: '', description: 'Choose the Component')
        string(name: 'ENV', defaultValue: '', description: 'Choose the Environment')
    }
    stages {
        // stage('Ansible installation'){
        //     steps{
        //         sh 'sudo labauto ansible'
        //     }
        // }
        stage('Clone Application') {
            steps {
                dir('APP'){
                    git branch: 'main', url: "https://github.com/sai-pranay-teja/${COMPONENT}"
                }

                dir('HELM'){
                    git branch: 'main', url: "https://github.com/sai-pranay-teja/roboshop-helm-chart"
                }

                

            }
        }

        stage('Server Deployment') {
            steps {
                sh 'helm upgrade -i ${COMPONENT} HELM -f APP/helm/${ENV}.yaml --set appversion=${APP_VERSION}'

            }

        }
    }

    post{
        always{
            cleanWs()
    }

}

}


}