pipeline {
  agent any
  tools {
    maven 'M3'            // nom exact de votre installation Maven
  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }
  
  }
}



