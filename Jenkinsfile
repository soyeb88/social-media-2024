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
                bat 'docker build -t soyeb88/social-media-2024-v1:0.0.1 .'
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
                	kubeconfig(caCertificate: '''apiVersion: v1
clusters:
- cluster:
    certificate-authority: C:\\Users\\soyeb\\.minikube\\ca.crt
    extensions:
    - extension:
        last-update: Sun, 22 Sep 2024 04:27:24 +06
        provider: minikube.sigs.k8s.io
        version: v1.33.1
      name: cluster_info
    server: https://127.0.0.1:51795
  name: minikube
contexts:
- context:
    cluster: minikube
    extensions:
    - extension:
        last-update: Sun, 22 Sep 2024 04:27:24 +06
        provider: minikube.sigs.k8s.io
        version: v1.33.1
      name: context_info
    namespace: default
    user: minikube
  name: minikube
current-context: minikube
kind: Config
preferences: {}
users:
- name: minikube
  user:
    client-certificate: C:\\Users\\soyeb\\.minikube\\profiles\\minikube\\client.crt
    client-key: C:\\Users\\soyeb\\.minikube\\profiles\\minikube\\client.key''', credentialsId: 'kubeconfig-pwd', serverUrl: '') {
    // some block
}
                }
            }
        }
    }
}