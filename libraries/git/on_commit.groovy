void call(Map args = [:], body){

  // do nothing if not commit
  if (!env.GIT_BUILD_CAUSE.equals("commit")) 
    return
  
  def branch = env.BRANCH_NAME
  
  // Variable necesaria para artifactory
  env.ARTIFACTORY_BRANCH = branch
  
  // do nothing if branch doesn't match regex
  if (args.to)
  if (!(branch ==~ args.to))
    return
  
  println "running because of a commit to ${branch}"
  body()
  
}