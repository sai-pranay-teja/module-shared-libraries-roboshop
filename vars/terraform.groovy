def call() {
pipeline {
    agent any

    options {
        ansiColor('xterm')
    }

    
    parameters {
        string(name: 'ENV', defaultValue: 'dev', description: 'Choose the Environment')
        string(name: 'ACTION', defaultValue: '', description: 'Choose the Action')
    }
    stages {
        stage('init') {
            steps {
                sh 'terraform init -backend-config env-${ENV}/state.tfvars'
            }
        }

        stage('apply') {
            steps {
                sh 'terraform ${ACTION} -var-file=env-${ENV}/main.tfvars -auto-approve'
            }

        }
    }

}


}