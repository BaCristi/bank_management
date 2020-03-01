node {
    checkout scm

     stages {
            stage('Build stage') {
                steps {
                    bat 'mvn -B clean verify'
                }
            }
            stage('Test stage') {
                steps {
                    bat 'mvn test'
                }
            }
            stage('Package stage') {
                steps {
                    bat 'mvn package'
                }
            }
        }
}
