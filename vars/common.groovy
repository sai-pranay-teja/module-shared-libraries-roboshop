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
    sh 'sonar-scanner -Dsonar.host.url=http://44.197.195.98:9000 -Dsonar.login=admin -Dsonar.password=roboshop -Dsonar.projectKey=${component}'
}