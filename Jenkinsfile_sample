pipeline{
    agent any
    
    stages{
        stage("Build"){
            steps{
                echo("build the project")
            }
        }
        stage("Unit Test"){
            steps{
                echo("Unit Testing")
            }
        }
        stage("Deploy to DEV"){
            steps{
                echo("Deploy to DEV")
            }
        }
        stage("Deploy to QA"){
            steps{
                echo("Deploy to QA")
            }
        }
        stage("Regression Test"){
            steps{
                echo("Regression testing")
            }
        }
        stage("Deploy to Stage"){
            steps{
                echo("Deploy to stage")
            }
        }
        stage("Sanity Test"){
            steps{
                echo("Sanity testing the project")
            }
        }
        stage("Deploy to PROD"){
            steps{
                echo("Deploy to PROD")
            }
        }
    }
}