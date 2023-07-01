def complie(){
    if (app_lang=="nodejs") {
        sh 'npm install'
    }

    if (app_lang=="maven") {
        sh 'mvn package'
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
    if (app_lang=="nodejs" || app_lang=="angular")
    {
        sh 'zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile'
    }

}

def Artifactupload(){
    if (app_lang=="nodejs" || app_lang=="angular")
    {
        sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://34.229.79.143:8081/repository/${component}/${component}-${TAG_NAME}.zip'
    }

}
  