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
        stage('Update the parameters') {
            steps {
                sh 'aws ssm put-parameter --name ${ENV}.${COMPONENT}.app_version --type "String" --value "${APP_VERSION}" --overwrite'
            }
        }

        stage('Server Deployment') {
            steps {
                sh 'aws ec2 describe-instances --filters "Name=tag:Name,Values=${ENV}-${COMPONENT}" --query "Reservations[*].Instances[*].PrivateIpAddress" --output text | xargs > /tmp/private_ips'

                sh 'ansible-playbook -i /tmp/private_ips roboshop-app.yml -e env=${ENV} -e components=${COMPONENT} -e ansible_user=centos -e ansible_password=DevOps321'
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