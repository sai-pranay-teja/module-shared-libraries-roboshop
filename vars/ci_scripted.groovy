def call(){
    if (!env.sonar_opts) {
        sonar_opts=""
    } 

node {
        
        stage('Check out the repo'){
            sh 'ls -l'
            cleanWs()
            sh 'ls -l'
            git branch: 'main', url: 'https://github.com/sai-pranay-teja/cart'
            sh 'ls -l'

            sh 'env'


            
        }
        
        if (env.BRANCH_NAME!="main"){
            stage('Build/Execute') {
                common.complie()
            }
        }

        if (env.TAG_NAME ==~ ".*"){
            stage('Test Cases') {
                common.testcases()
        }
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

