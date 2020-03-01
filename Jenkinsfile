pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') { 
            agent {
                docker {
                image 'mysql/mysql-server'
                args '--name some-mysql -e MYSQL_ROOT_PASSWORD=password -d'}
            }
            steps {
                bat 'mvn test -DforkCount=0'
                bat '''
                    docker exec some-mysql bat -c 'exec mysql < ./db/dump.sql
                    '''
            }
        }
    }
}
