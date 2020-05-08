void call(){
    def java_tool_name = config.java_tool ?:
            "java"
    def java = tool "$java_tool_name"

    stage("unit test gradle"){
        node{
            withEnv(["PATH=$java/bin:$PATH"]) {
                sh './gradlew test'
            }
        }                
    }  
}