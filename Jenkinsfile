pipeline {
  agent {
    docker {
      image 'maven:3.8.6-openjdk-11'
      args  '-v $HOME/.m2:/root/.m2'   // pour conserver le cache Maven local
    }
  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }
  
  }
}




