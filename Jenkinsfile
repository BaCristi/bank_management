pipeline { 
    agent any  
    stages {
       stage ('Build') {
          agent {
               docker {
               image 'mysql/mysql-server'
               args '--name some-mysql -e MYSQL_ROOT_PASSWORD=password -d'
               }
          }
          steps {
              bat 'mvn clean install'
          }
      }
    }
}
