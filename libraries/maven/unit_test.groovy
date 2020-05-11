void call(){
    def java_tool_name = config.java_tool ?:
            "java"

    stage("unit test maven"){
        node{
            def java = tool "$java_tool_name"
            withEnv(["PATH=$java/bin:$PATH"]) {       
                sh './mvnw verify'
            }
        }                
    }  
}