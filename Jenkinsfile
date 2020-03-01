pipeline { 
    agent any  
    stages {
        agent {
            docker {
            image 'mysql/mysql-server'
            args '--name some-mysql -e MYSQL_ROOT_PASSWORD=password -d'
            }
        }
       stage ('Build') {
          steps {
              bat 'mvn clean install'
          }
      }
    }
}
