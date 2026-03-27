pipeline {
    agent any

    tools {
        maven 'Maven'          // Nom de l’installation Maven dans Jenkins
        jdk 'java'             // Nom de l’installation JDK dans Jenkins
    }

    environment {
        TOMCAT_CREDS = credentials('tomcat-credentials')
        TOMCAT_URL = 'http://localhost:8081/manager/text'
        APP_PATH = '/hello-timer'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-credentials',
                    url: 'https://github.com/Scroll-7/hello-timer.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.war', fingerprint: true
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    def warFile = 'target/hello-timer.war'
                    sh """
                        curl -u ${TOMCAT_CREDS_USR}:${TOMCAT_CREDS_PSW} \
                             -T ${warFile} \
                             "${TOMCAT_URL}/deploy?path=${APP_PATH}&update=true"
                    """
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo '✅ Build and deployment successful!'
        }
        failure {
            echo '❌ Build or deployment failed.'
        }
    }
}
