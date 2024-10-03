pipeline{
    agent any
    tools{
        maven 'maven_3_9_8'
    }
    environment {
        KUBECONFIG = 'C:\\Users\\soyeb\\.kube\\config'
        MINIKUBE_HOME = 'C:\\Users\\soyeb\\.minikube'
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
                bat 'docker system prune -f'
            }
        }
        stage('Push Docker Hub'){
            steps{
                script{
                    bat 'docker login -u soyeb88 -p Dhaka_866'
                    bat 'docker push soyeb88/social-media-2024-v1:latest'
                }
            }
        }
        stage('Deploy to K8s'){
            steps{
                script{
					withEnv(["--kubeconfig=${KUBECONFIG}"]) {
                        // Run your container in Minikube
                        bat 'kubectl config set-context --current --namespace=jenkins'
                        bat 'kubectl delete pods -l app=app-api'
                        bat 'kubectl apply -f social-media-2024-v1-api.yaml'
                        //bat 'minikube image load soyeb88/social-media-2024-v1-frontend:0.0.2'
                    }
                }
            }
        }
    }
}