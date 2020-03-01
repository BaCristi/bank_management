node('lm.backend.test') {
            checkout scm
       docker.image('mysql:5').withRun('-e "MYSQL_ROOT_PASSWORD=my-secret-pw"'){ c ->
            docker.image('mysql:5').inside("--link ${c.id}:db") {
                bat 'service mysql start'
            }
            docker.image('dmstr/php-yii2').inside("--link ${c.id}:db") {
                 bat 'php -v'
                 bat 'mysql -v'

                 stage('Build') {
                     bat 'cd basic && composer dump-autoload && composer clear-cache && composer install'
                     }
                 stage('TEST API'){
                     bat 'cd basic && vendor/bin/codecept build && vendor/bin/codecept run'
                 }
            }
        }
        stage('Deploy'){
            echo 'Deploying....'
        }
    }
