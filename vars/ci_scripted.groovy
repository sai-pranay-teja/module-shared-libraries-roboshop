def call(){
    if (!env.sonar_opts) {
        sonar_opts=""
    } 
    if (env.TAG_NAME ==~ ".*") {
        env.OG_TAG="true"
    }else{
        env.OG_TAG="false"
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

        if (env.OG_TAG != "true" && env.BRANCH_NAME!="main"){
            stage('Test Cases') {
                common.testcases()
        }
        }

        if (env.BRANCH_NAME==~"PR.*"){
            stage('Code Quality') {
                common.codequality()
        }

        }


//     post{
//         failure{
//             mail body: "${component} pipeline - has failed \n ${BUILD_URL}", from: 'algonox1.1@gmail.com', subject: "${component} pipeline - has failed", to: "algonox1.1@gmail.com"
//     }

// }
}
}

