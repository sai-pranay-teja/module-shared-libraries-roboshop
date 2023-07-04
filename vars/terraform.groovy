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
        if(CLEAN=="no")
        {
            stage('init') {
                steps {
                    sh 'terraform init -backend-config env-${ENV}/state.tfvars'
            }
        }
        }

        if(CLEAN=="yes")
        {
            stage('init') {
                steps {
                    cleanWs()
                    sh 'terraform init -backend-config env-${ENV}/state.tfvars'
            }
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


