void call(Map args = [:], body){

  if (!(env.GIT_BUILD_CAUSE in ["commit", "pr"])) 
    return
  
  def branch = env.BRANCH_NAME

  env.ARTIFACTORY_BRANCH = branch
    
  if (args.to)
  if (!(branch ==~ args.to))
    return
  
  println "running because of a change to ${branch}"
  body()
  
}