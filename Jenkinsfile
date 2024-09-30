pipeline {
    agent any

    environment {
        GITHUB_REPO = 'https://github.com/arkohaddoebenezer/health_information_system.git'
        DOCKERHUB_REPO = 'earkohaddo/jenkins'
        IMAGE_NAME = 'health_information_system'
        IMAGE_TAG = "v1.${env.BUILD_NUMBER}"
    }

    tools{
        maven 'Maven'
        jdk 'JDK'
    }

    stages {
        stage('Checkout') {
            steps {
                cleanWs()
                git branch: 'jenkins', url: "${GITHUB_REPO}"
            }
        }

        stage('Build') {
            steps {
               bat "mvn clean install"
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${IMAGE_NAME}-${IMAGE_TAG}")
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                script {
                    bat 'docker login'
                    bat "docker push ${DOCKERHUB_REPO}:${IMAGE_NAME}-${IMAGE_TAG}"
                }
            }
        }
    }

    post {
        always {
            bat 'docker logout'
}