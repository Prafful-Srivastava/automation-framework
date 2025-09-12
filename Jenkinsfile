pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Check Docker') {
            steps {
                echo "Checking Docker & docker-compose availability..."
                sh 'docker --version'
                sh 'docker-compose --version'
            }
        }

        stage('List Workspace') {
            steps {
                echo "Listing files in Jenkins workspace..."
                sh 'ls -l'
            }
        }

        stage('Start Selenium Grid') {
            steps {
                echo "Starting Selenium Grid using docker-compose..."
                sh 'docker-compose -f docker-compose.yml up -d'
            }
        }

        stage('Wait for Selenium Hub') {
            steps {
                script {
                    timeout(time: 60, unit: 'SECONDS') {
                        sh '''
                          echo "Waiting for Selenium Hub..."
                          until curl -s http://localhost:4444/status | tee hub_status.json | grep -q '"ready":true'; do
                            echo "Current Hub status:"
                            cat hub_status.json
                            sleep 2
                          done
                          echo "Selenium Hub is ready!"
                        '''
                    }
                }
            }
        }

        stage('Run Tests on Grid') {
            steps {
                echo "Running Maven tests against Selenium Grid..."
                sh 'mvn clean test -Dremote=true -Dbrowser=chrome -Dremote.url=http://localhost:4444/wd/hub'
            }
        }
    }

    post {
        always {
            echo "Cleaning up Selenium Grid containers..."
            sh 'docker-compose -f docker-compose.yml down -v'

            echo "Publishing TestNG (JUnit XML) results..."
            junit 'target/surefire-reports/*.xml'
        }
    }
}
