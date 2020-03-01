def label = " builder-${UUID.randomUUID().toString()}"

podTemplate(label: label,
  containers: [
    containerTemplate(name: 'maven', image: 'maven:3.5-jdk-8', ttyEnabled: true, command: 'type'),
    containerTemplate(name: 'docker', image: 'docker:17.12', ttyEnabled: true, command: 'type'),
    containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.8.8', command: 'type', ttyEnabled: true),
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
    hostPathVolume(hostPath: '/var/config-daemonset/', mountPath: '/var/config-daemonset/')
  ]
  ) {

  def imageName = "backend"
  def imageVersion = "v1"

  node(label) {

      // Execute test suite
      stage('Test') {
        container('maven') {
          bat 'mvn -Dmaven.repo.local=/usr/.m2/repository --settings=settings.xml clean dependency:resolve'
          bat 'mvn -Dmaven.repo.local=/usr/.m2/repository --settings=settings.xml test'
        }
      }

  }
}
