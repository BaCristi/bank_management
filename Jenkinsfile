def label = "backend-builder-${UUID.randomUUID().toString()}"

podTemplate(label: label,
  containers: [
    containerTemplate(name: 'maven', image: 'maven:3.5-jdk-8', ttyEnabled: true, command: 'cat'),
    containerTemplate(name: 'docker', image: 'docker:17.12', ttyEnabled: true, command: 'cat'),
    containerTemplate(name: 'mysql', image: 'mysql:5.7', envVars: [
      envVar(key: 'MYSQL_ROOT_PASSWORD', value: 'admin'),
      envVar(key: 'MYSQL_DATABASE', value: 'admin'),
      envVar(key: 'MYSQL_USER', value: 'admin'),
      envVar(key: 'MYSQL_PASSWORD', value: 'admin')
    ],
    ports: [
      portMapping(name: 'mysql', containerPort: 3306, hostPort: 3306)
    ])
  ],
  volumes: [
    hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock'),
    hostPathVolume(hostPath: '/var/prod-config-daemonset/', mountPath: '/var/prod-config-daemonset/')
  ]
  ) {

  def imageName = "backend"
  def imageVersion = "v${env.BUILD_NUMBER}"

  node(label) {
    try {

      // Execute test suite
      stage('Test') {
        container('maven') {
          sh 'mvn -Dmaven.repo.local=/usr/.m2/repository --settings=settings.xml clean dependency:resolve'
          sh 'mvn -Dmaven.repo.local=/usr/.m2/repository --settings=settings.xml test'
        }
      }

      }
    } catch (e) {
    }
  }
}
