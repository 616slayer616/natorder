pipeline {

  options {
      timeout(time: 1, unit: 'HOURS')
      buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
  }

  agent {
    docker {
      image 'mxml/jdk-scp'
      alwaysPull true
    }
  }

  stages {

    stage('build') {
      steps {
        sh 'java -version'
        sh './gradlew clean assemble'
      }
    }
    stage('Test') {
      steps {
        sh './gradlew check'
      }
    }

    stage('Archive .jar'){
      steps {
        archiveArtifacts  'build/libs/**/*.jar'
      }
    }

    stage('release'){
      when{
        branch 'master'
      }
      steps {
        withCredentials([
        file(credentialsId: 'gradle-properties	', variable: 'FILE_PROPERTIES'),
        file(credentialsId: 'keyfile', variable: 'FILE_KEY')
        ]) {
          sh 'cp $FILE_PROPERTIES .'
          sh 'cp $FILE_KEY .'
        }
        sh './gradlew publish'
      }
    }
  }

  post {
    success {
      echo "Build successful"
    }
    always {
      junit 'build/test-results/**/*.xml'
      cleanWs()
    }
  }
}
