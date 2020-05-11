void call(app_env){
    
    def branch_conditional = config.branch_conditional ?:
            "s"

    if(branch_conditional == "s" || branch_conditional == $env.BRANCH_NAME){
        stage ("Deploy Approve to ${app_env.long_name}") {
            echo "Branch: $env.BRANCH_NAME"
            if(app_env.long_name.equals('Production') ){
                timeout(time:1, unit:'DAYS'){  
                    input message: 'Can you approve for IT ??', submitter: app_env.approversIT                
                }
                timeout(time:1, unit:'DAYS'){
                    input message: 'Can you approve for Bussiness ??', submitter: app_env.approversBussiness
                }
            }else{
                timeout(time:1, unit:'HOURS'){
                    input message: 'Can you approve for IT ??', submitter: app_env.approversIT
                }
            }
        }
    }    
}