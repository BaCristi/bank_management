node {
    checkout scm

    stage('Test') {
        container('maven') {
          bat 'mvn clean install'
        }
      }
}
