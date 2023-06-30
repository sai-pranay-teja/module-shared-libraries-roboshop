def call(){

node {

        stage('Build/Execute') {
            common.complie()
        }

        stage('Test Cases') {
            common.testcases()
        }

        stage('Code Quality') {
            common.codequality()
        }
//     post{
//         failure{
//             mail body: "${component} pipeline - has failed \n ${BUILD_URL}", from: 'algonox1.1@gmail.com', subject: "${component} pipeline - has failed", to: "algonox1.1@gmail.com"
//     }

// }
}
}

