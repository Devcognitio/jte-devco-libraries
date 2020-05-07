void call(){
    stage("unit test gradle"){
        node{
            sh './gradlew test'
        }                
    }  
}