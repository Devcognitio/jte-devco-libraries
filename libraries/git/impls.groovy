def call(){
    List options = ['github', 'bitbucket', 'github_enterprise']
    String impl = config.source_type

    return options.contains(impl) ? getBinding().getStep(impl) :
            { error "git.config.source_type: ${impl} is not a valid option; should be one of: ${options.join(", ")}" } ()
}