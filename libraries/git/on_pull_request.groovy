void call(Map args = [:], body){

  if (!env.GIT_BUILD_CAUSE.equals("pr")) 
    return
  
  def source_branch = get_source_branch()
  def target_branch = env.CHANGE_TARGET

  env.ARTIFACTORY_BRANCH = source_branch

  if (args.from)
  if (!(source_branch ==~ (~args.from) ))
    return

  if (args.to)
  if (!(target_branch ==~ (~args.to) ))
    return
  
  println "running because of a PR from ${source_branch} to ${target_branch}"
  body()  

}

// TODO: pendiente modificar para bitbucket
def get_source_branch(){
  return impls().get_source_branch()  
}