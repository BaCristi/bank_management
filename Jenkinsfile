def label = "backend-builder-${UUID.randomUUID().toString()}"

  node(label) {

      // Execute test suite
      stage('Test') {
        container('maven') {
          sh 'mvn -Dmaven.repo.local=/usr/.m2/repository --settings=settings.xml clean dependency:resolve'
          sh 'mvn -Dmaven.repo.local=/usr/.m2/repository --settings=settings.xml test'
        }
  }
}
