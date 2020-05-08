void call(){
    
    def java_tool_name = config.java_tool ?:
            "java"
    def java = tool "$java_tool_name"

    stage("build gradle"){
        node {
            withEnv(["PATH=$java/bin:$PATH"]) {
                sh 'chmod +x gradlew'
                sh './gradlew clean build -DskipTests=true'
                archiveArtifacts artifacts: 'build/libs/**/*.jar'
            }
            
        }       
    }   
}