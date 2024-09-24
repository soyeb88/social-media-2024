pipeline{
    agent any
    tools{
        maven 'maven_3_9_8'
    }
    stages{
        stage('Build Maven'){
            steps{
                git branch: 'ahmed-v1', url: 'https://github.com/soyeb88/social-media-2014.git'
                //bat 'mvn package -Dmaven.test.skip'
                bat 'mvn clean install'
                //bat 'xcopy /Y C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\socialmedia-devops-automation\\target\\social-media-2024-v1.jar C:\\Users\\soyeb\\Documents\\social-media-2024\\Host'
            }
        }
        stage('Build Docker Image'){
            steps{
                bat 'docker build -t soyeb88/social-media-2024-v1:latest .'
            }
        }
        stage('Push Docker Hub'){
            steps{
                script{
                    bat 'docker login -u soyeb88 -p Dhaka_866'
                    bat 'docker push soyeb88/social-media-2024-v1:0.0.1'
                }
            }
        }
        stage('Deploy to K8s'){
            steps{
                script{
                	withKubeConfig(credentialsId: 'jenkins-secret', namespace: 'jenkins', restrictKubeConfigAccess: false, serverUrl: 'https://127.0.0.1:51148') {
    					bat 'kubectl apply -f social-media-2024-v1-api.yaml'
					}
                }
            }
        }
    }
}