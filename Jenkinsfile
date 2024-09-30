pipeline {
    agent any

    tools{
        maven 'Maven'
        jdk 'jdk21'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/arkohaddoebenezer/health_information_system.git'
            }
        }

        stage('Build with Maven') {
            steps {
                script {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    sh './mvnw test'
                }
            }
        }

        stage('Build and Deploy docker image') {
            steps {
                script {
                    sh 'docker-compose up -d --build'
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}