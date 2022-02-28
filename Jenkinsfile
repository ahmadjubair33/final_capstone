pipeline {
   agent any
//    tools {
//        maven 'maven'
//        jdk 'jdk8'
//    }
   environment {
      dockerhub=credentials('dockerhub')
   }
   stages{
       stage("clean"){
           
         steps
            {
                sh 'mvn clean'
            }
       }
       stage("test"){
           
         steps
            {
                sh 'mvn test'
            }

   }
   stage("packaging"){
         
         steps
            {
                sh 'mvn package -DskipTests'
            }
   }
   stage('build image')
        {
         
            steps{
                // sh 'echo $dockerhub_USR | xargs echo'
                sh 'docker build -t capstone:1.01 .'
            }
        } 

        stage('pushing to dockerhub')
        {
          
            steps{
                sh 'docker tag capstone:1.01 ahmad33/starter-kit:1.01 '
                sh 'docker login -u $dockerhub_USR -p $dockerhub_PSW'

                sh 'docker push ahmad33/starter-kit:1.01'
            }
        }
        stage('deploy')
        {
            
            steps{
                script{
                 kubernetesDeploy configs: '**/deployment.yml', kubeConfig: [path: ''], kubeconfigId: 'kubeconfig', secretName: '', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
                }
            }
        }
}
}

