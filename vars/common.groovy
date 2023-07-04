def complie(){
    if (app_lang=="nodejs") {
        sh 'npm install'
    }

    if (app_lang=="maven") {
        sh 'mvn package; mv target/${component}-1.0.jar ${component}.jar'
    }
}

// def codequality(){
//     sh 'sonar-scanner -Dsonar.host.url=http://172.31.87.135:9000 -Dsonar.login=admin -Dsonar.password=roboshop -Dsonar.projectKey=$(component)'
// }

def codequality(){
    sh 'sonar-scanner -Dsonar.host.url=http://dev-sonarqube.practise-devops.online:9000 -Dsonar.login=admin -Dsonar.password=roboshop -Dsonar.projectKey=${component} ${sonar_opts}'
}

def testcases(){
    sh 'echo OK'
}

def prepareAtrtifacts(){
    // sh 'echo ${TAG_NAME}>VERSION'
    // if (app_lang=="maven")
    // {
    //     sh 'zip -r ${component}-${TAG_NAME}.zip ${component}.jar schema VERSION'

    // }
    // else{
    //     sh 'zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile'

    // }
    sh 'docker build -t 581798224530.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME} .'

}

def Artifactupload(){
    // sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://dev-nexus.practise-devops.online:8081/repository/${component}/${component}-${TAG_NAME}.zip'
    sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 581798224530.dkr.ecr.us-east-1.amazonaws.com'
    sh 'docker push 581798224530.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME}'
}
  