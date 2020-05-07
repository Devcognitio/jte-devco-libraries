void call(){
    stage("build gradle"){
        node {            
            sh 'chmod +x mvnw'
            sh './mvnw clean build -DskipTests=true'
            archiveArtifacts artifacts: 'build/libs/**/*.jar'
        }       
    }   
}