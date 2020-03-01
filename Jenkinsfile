podTemplate(label: "label",
  containers: [
    containerTemplate(name: 'maven', image: 'maven:3.5-jdk-8', ttyEnabled: true, command: 'cat'),
    containerTemplate(name: 'docker', image: 'docker:17.12', ttyEnabled: true, command: 'cat'),
    containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.8.8', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'mysql', image: 'mysql:5.7', envVars: [
      envVar(key: 'MYSQL_ROOT_PASSWORD', value: 'voilait2root'),
      envVar(key: 'MYSQL_DATABASE', value: 'voilait2'),
      envVar(key: 'MYSQL_USER', value: 'voilait2'),
      envVar(key: 'MYSQL_PASSWORD', value: 'voilait2')
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
  def imageVersion = "v${env.BUILD_NUMBER}"

  node(label) {

      // Execute test suite
      stage('Test') {
        container('maven') {
          bat 'mvn clean install'
        }
       }
  }
}
