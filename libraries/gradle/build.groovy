void call(){
    
    stage("build gradle"){
        node {
            withEnv(["PATH=${tool 'java8'}/bin:$PATH"]) {
                sh 'chmod +x gradlew'
                sh './gradlew clean build -DskipTests=true'
                archiveArtifacts artifacts: 'build/libs/**/*.jar'
            
                stash name: 'workspace', includes: "**"
            }
            
        }       
    }   
}