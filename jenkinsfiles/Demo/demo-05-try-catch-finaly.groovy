//This is how to handle errors in steps and inside scripts
pipeline {
  agent any
  stages {
    stage('Pass') {
      steps {
        script {
          echo 'Stage 1'
            try{
              sh 'df -h'
            }catch(Exception ex){
              //Catching most of the Exceptions generated (not errors)
              echo "Exception:"+ex
            }catch(Error e){
              //Catching java errors (not exceptions)
              echo "Error:"+e
            }catch(Throwable t){
              //Catching everything throwable (both exceptions and errors)
              echo "Throwable:"+t
            }finally{
              echo 'This part is always executed '
            }
          }
        }
      }
    stage('Exception') {
      steps {
        script {
          echo 'Stage 2'
         try{
              sh 'asdfgh'
         }catch(Exception ex){
              echo "Exception:"+ex
         }catch(Error e){
              echo "Error:"+e
         }catch(Throwable t){
              echo "Throwable:"+t
         }finally{
              echo 'This part is always executed '
         }
        }
      }
    }
    stage('Error') {
      steps {
        script {
          echo 'Stage 3'
            try{
              do_things()
            }catch(Exception ex){
              echo "Exception:"+ex
            }catch(Error e){
              echo "Error:"+e
	          // error("Java error found") // To abort further execution 
            }catch(Throwable t){
              echo "Throwable:"+t
            }finally{
              echo 'This part is always executed '
            }
          }
        }
      }
    }
}
