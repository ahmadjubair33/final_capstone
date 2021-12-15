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
                sh 'mvn clean test'
            }

   }
   stage("packaging"){
         when{
                branch "production"
                }  
         steps
            {
                sh 'mvn package'
            }
   }
   stage('build image')
        {
         when{
                branch "production"
                }
            steps{
                sh 'echo $dockerhub_USR | xargs echo'
                sh 'docker build -t final-caps:1.01 .'
            }
        } 

        stage('pushing to dockerhub')
        {
          when{
                branch "production"
                }
            steps{
                sh 'docker tag final-caps:1.01 ahmad33/user-management:1.01 '
                sh 'docker login -u $dockerhub_USR -p $dockerhub_PSW'

                sh 'docker push ahmad33/user-management:1.01'
            }
        }
}
}
