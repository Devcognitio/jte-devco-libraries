void call(app_env){
    def branch_conditional = config.branch_conditional ?:
            "s"

    if(branch_conditional == "s" || branch_conditional == $GIT_BRANCH){
        stage ("Deploy to ${app_env.long_name}") {
            if(app_env.long_name.equals('Production') ){
                println('Deploy a Produccion DONE...')
            }else{
                println('Este ambiente no requiere deploy')
            }
        }
    }
}