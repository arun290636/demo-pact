def gitUrl = 'https://github.com/arun290636/demo-pact.git'

// Main build and deploy job for consumer and provider each (continuous deployment case)
['MagentoWeb', 'user-service'].each {
    def app = it
    pipelineJob("$app-build-and-deploy") {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(gitUrl)
                        }
                        branch('master')
                        extensions {}
                    }
                }
                scriptPath("$app/jenkins/cd/Jenkinsfile")
            }
        }
    }
}

// Separate build and deploy jobs for consumer and provider each (non-continuous deployment case)
['MagentoWeb', 'user-service'].each {
    def app = it
    ['build', 'deploy'].each {
        def phase = it
        pipelineJob("$app-$phase") {
            definition {
                cpsScm {
                    scm {
                        git {
                            remote {
                                url(gitUrl)
                            }
                            branch('master')
                            extensions {}
                        }
                    }
                    scriptPath("$app/jenkins/without-cd/Jenkinsfile-$phase")
                }
            }
        }
    }
}
