void call(){
    stage("build gradle"){
        node {
            sh 'chmod +x gradlew'
            sh './gradlew clean build -DskipTests=true'
            archiveArtifacts artifacts: 'build/libs/**/*.jar'
        }       
    }   
}