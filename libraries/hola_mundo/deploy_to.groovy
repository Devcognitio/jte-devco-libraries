void call(app_env){
    stage ("Deploy to ${app_env.long_name}") {
        if(app_env.long_name.equals('Production') ){
            println('Deploy a Produccion DONE...')
        }else{
            println('Este ambiente no requiere deploy')
        }
    }
}