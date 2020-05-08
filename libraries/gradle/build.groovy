void call(){
    
    def java_tool_name = config.java_tool ?:
            "java"    

    stage("build gradle"){
        node {
            def java = tool "$java_tool_name"
            withEnv(["PATH=$java/bin:$PATH"]) {
                sh 'chmod +x gradlew'
                sh './gradlew clean build -DskipTests=true'
                archiveArtifacts artifacts: 'build/libs/**/*.jar'
            }
            
        }       
    }   
}