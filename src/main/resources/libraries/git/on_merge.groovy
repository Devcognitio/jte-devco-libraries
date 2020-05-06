void call(Map args = [:], body){

  if (!env.GIT_BUILD_CAUSE.equals("merge"))
    return

  env.FEATURE_SHA = get_feature_branch_sha()

  def source_branch = get_merged_from()
  def target_branch = env.BRANCH_NAME

  println "source_branch: ${source_branch}"
  println "target_branch: ${target_branch}"

  if (args.from)
  if (!source_branch.collect{ it ==~ args.from}.contains(true))
    return

  if (args.to)
  if (!(target_branch ==~ args.to))
    return

  def mergedFrom = source_branch.join(", ")
  if(mergedFrom.contains(", ")) {
      def oxford = mergedFrom.lastIndexOf(", ")
      mergedFrom = mergedFrom.substring(0, oxford) + " and" + mergedFrom.substring(oxford + 1)
  }

  println "running because of a merge from ${mergedFrom} to ${target_branch}"
  body()
}

String get_merged_from(){
  node{
    def remote = env.GIT_URL
    def sourceShas = sh(
      script: "git rev-list HEAD --parents -1",
      returnStdout: true
    ).trim().split(" ")[2..-1]
    def branchNames = []
    for(sha in sourceShas) {
      def branch = sh(
        script: "git name-rev --name-only " + sha,
        returnStdout: true
      ).replaceFirst("remotes/origin/", "").trim()
      if(branch.contains("~"))
        branch = branch.substring(0, branch.lastIndexOf("~"))
      if(!branch.contains("^"))
        branchNames.add(branch)
    }
    return branchNames
  }
}

String get_feature_branch_sha(){
  node{
    //unstash "workspace"
    sh(
      script: "git rev-parse \$(git --no-pager log -n1 | grep Merge: | awk '{print \$3}')",
      returnStdout: true
     ).trim()
  }
}