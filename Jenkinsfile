pipeline {
    agent any   // Run on any Jenkins node

    stages {
        stage('Checkout') {
            steps {
                // Pull code from GitHub (job SCM config handles repo URL)
                checkout scm
            }
        }

        stage('Start Selenium Grid') {
            steps {
                // Start Selenium Hub + Nodes in background
                sh 'docker-compose -f docker-compose.yml up -d'
            }
        }

        stage('Wait for Selenium Hub') {
            steps {
                // Ensure Hub is ready before running tests
                sh '''
                  echo "Waiting for Selenium Hub to be ready..."
                  until curl -s http://localhost:4444/wd/hub/status | grep -q '"ready":true'; do
                    sleep 2
                  done
                '''
            }
        }

        stage('Run Tests on Grid') {
            steps {
                // Run tests against Selenium Grid
                sh 'mvn clean test -Dremote=true -Dbrowser=chrome -Dremote.url=http://localhost:4444/wd/hub'
            }
        }
    }

    post {
        always {
            // Stop Selenium Grid containers
            sh 'docker-compose -f docker-compose.yml down -v'

            // Publish TestNG results (Jenkins reads them as JUnit XML)
            junit 'target/surefire-reports/*.xml'
        }
    }
}