def label = "backend-builder-${UUID.randomUUID().toString()}"

  node(label) {
    try {

      // Execute test suite
      stage('Test') {
        container('maven') {
          sh 'mvn -Dmaven.repo.local=/usr/.m2/repository --settings=settings.xml clean dependency:resolve'
          sh 'mvn -Dmaven.repo.local=/usr/.m2/repository --settings=settings.xml test'
        }
      }

    } catch (e) {
      slackSend channel: "#tech", color: "#FF9FA1", message: "${imageName}:${imageVersion} build and deploy updated to FAILED"
    }
  }
}
