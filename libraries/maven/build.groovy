void call(){
    def java_tool_name = config.java_tool ?:
            "java"

    stage("build maven"){
        node { 
            def java = tool "$java_tool_name"
            withEnv(["PATH=$java/bin:$PATH"]) {           
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests=true'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }       
    }   
}