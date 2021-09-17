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
              
                sh 'docker build -t  9963286630/revature-railways-backend .'
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
                sh 'docker push 9963286630/revature-railways-backend'
            }
        }
        
        stage('docker stop container') {
         steps {
            sh 'docker ps -f name=9963286630/revature-railways-backend -q | xargs --no-run-if-empty docker container stop'
            sh 'docker container ls -a -fname=9963286630/revature-railways-backend -q | xargs -r docker container rm'
         }
       }
        stage('Docker deploy'){
            steps {
	
	
              
                sh 'docker run -itd -p  9090:9848 9963286630/revature-railways-backend'
            }
        }

        
        stage('Archving') { 
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}