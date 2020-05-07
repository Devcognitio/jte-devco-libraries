void call(){
    stage("unit test gradle"){
        node{
            sh './mvnw test'
        }                
    }  
}