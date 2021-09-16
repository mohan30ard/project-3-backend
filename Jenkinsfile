pipeline {
    agent any 
    stages {
        stage('Compile and Clean') { 
            steps {

                sh "mvn clean compile"
            }
        }
       

        stage('deploy') { 
            steps {
                sh "mvn package"
            }
        }


        stage('Build Docker image'){
            steps {
              
                sh 'docker build -t  9963286630/revature-railways-backend:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Login'){
            
            steps {
                    withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
                    sh "docker login -u 9963286630 -p ${Dockerpwd}"
                }
                
            }                
        }

        stage('Docker Push'){
            steps {
                sh 'docker push 9963286630/revature-railways-backend:${BUILD_NUMBER}'
            }
        }
        
        stage('Docker deploy'){
            steps {
               sh 'docker rm -f 9963286630/revature-railways-backend:${BUILD_NUMBER}-1'
                sh 'docker run -itd -p  9850:9848 9963286630/revature-railways-backend:${BUILD_NUMBER}'
            }
        }

        
        stage('Archving') { 
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}