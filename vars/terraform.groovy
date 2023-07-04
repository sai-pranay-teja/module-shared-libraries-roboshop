def call() {
pipeline {
    agent any

    options {
        ansiColor('xterm')
    }

    
    parameters {
        string(name: 'ENV', defaultValue: 'dev', description: 'Choose the Environment')
        string(name: 'ACTION', defaultValue: '', description: 'Choose the Action')
        string(name: 'CLEAN', defaultValue: '', description: 'Choose the cleaning action')
    }
    stages {
        stage('init with Clean') {
            when {
                environment name: 'CLEAN', value: 'no'
            }
            
              steps {
                // sh 'terraform init -backend-config env-${ENV}/state.tfvars'
                sh 'terraform init'
            }
            }

        stage('init without clean') {
            when {
                environment name: 'CLEAN', value: 'yes'
            }
            
              steps {
                cleanWs()
                // sh 'terraform init -backend-config env-${ENV}/state.tfvars'
                sh 'terraform init'
            }
            }



        stage('apply/destroy') {
            steps {
                sh 'terraform ${ACTION} -var-file=env-${ENV}/main.tfvars -auto-approve'
            }

        }
    }


}

}








