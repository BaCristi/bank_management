@Library('testInParallel') _

properties([
    parameters([
        string(name: 'MULTIPLIER', defaultValue: '1', description: 'Factor by which to artificially slow down tests.'),
        string(name: 'SPLIT', defaultValue: '10', description: 'Number of buckets to split tests into.')
    ])
])

stage('Sources') {
  node {
    checkout scm
    stash name: 'sources', excludes: 'Jenkinsfile,target/'
  }
}

stage('Testing') {
  testInParallel(count(Integer.parseInt(params.SPLIT)), 'inclusions.txt', 'exclusions.txt', 'target/surefire-reports/TEST-*.xml', 'maven:3.5.0-jdk-8', {
    unstash 'sources'
  }, {
    configFileProvider([configFile(fileId: 'jenkins-mirror', variable: 'SETTINGS')]) {
      withEnv(["MULTIPLIER=$params.MULTIPLIER"]) {
        sh 'mvn -s $SETTINGS -B clean test -Dmaven.test.failure.ignore'
      }
    }
  })
}

stage('run-parallel-branches') {
  steps {
    parallel(
      a: {
        echo "This is branch a"
      },
      b: {
        echo "This is branch b"
      }
    )
  }
}
