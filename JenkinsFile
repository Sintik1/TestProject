pipeline {
    agent any
    environment {
        SELENIUM_REMOTE_URL = 'http://selenium:4444/wd/hub'
        BASE_URI = 'http://frontend-service:9090'
        CI = 'true'
        TELEGRAM_BOT_TOKEN = '8263907755:AAG6nR_3bkV6ZEjD2Mhu3AdVT0kVYZsxsE0'
        TELEGRAM_CHAT_ID = '6284947582'
        EMAIL_TO = 'vsentyakov@yandex.ru'
        EMAIL_FROM = 'jenkins@example.com'
    }
    triggers {
        // Можно выбрать один из вариантов
        githubPush()
        pollSCM('H/5 * * * *')
        cron('H 11 * * *')
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from Git...'
                checkout scm
            }
        }
        stage('Test') {
            steps {
                echo 'Setting up Maven and running tests...'
                script {
                    sh '''
                    # Весь скрипт установки Maven и тестов
                    '''
                }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
                success {
                    echo '✅ All tests passed!'
                }
                failure {
                    echo '❌ Tests failed! Check console output for details.'
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline finished.'
            // тут ваш код по уведомлениям
            script {
                // логика формирования сообщения и отправки
            }
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs above.'
        }
    }
}