
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Source code checked out by Jenkins SCM configuration'
            }
        }

        stage('Verify Java and Maven') {
            steps {
                sh '''
                    java -version
                    mvn -version
                '''
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Package Application') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    docker build -t campus-equipment-booking:set-f .
                '''
            }
        }
    }
}
