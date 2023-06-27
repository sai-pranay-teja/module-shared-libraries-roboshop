def call() {
pipeline {
    agent any

    options {
        ansiColor('xterm')
    }

    
    parameters {
        string(name: 'ENV', defaultValue: 'dev', description: 'Choose the Environment')
    }
    stages {
        stage('init') {
            steps {
                sh 'terraform init -backend-config env-${ENV}/state.tfvars'
            }
        }

        stage('apply') {
            steps {
                sh 'terraform apply -var-file=env-${ENV}/main.tfvars -auto-approve'
            }

        }
    }

}


}