//This is how to handle errors in steps and inside scripts
pipeline {
  agent any
  stages {

    stage('Exception') {
      steps {
        script {
         try{
              sh 'asdfgh'
            }catch(Exception ex){
              //Catching most of the Exceptions generated (not errors)
              echo "Exception:"+ex
            }catch(Error e){
              //Catching java errors (not exceptions)
              echo "Error:"+
            } catch (Throwable t){
              //Catching everything throwable (both exceptions and errors)
              echo "Throwable:"+t
            }finally{
              echo 'This part is allways executed '
            }
        }
      }
    }
    stage('Error') {
      steps {
        script {
          echo 'Stage 2'
            try{
              do_things()
            }catch(Exception ex){
              //Catching most of the Exceptions generated (not errors)
              echo "Exception:"+ex
            }catch(Error e){
              //Catching java errors (not exceptions)
              echo "Error:"+
            } catch (Throwable t){
              //Catching everything throwable (both exceptions and errors)
              echo "Throwable:"+t
            }finally{
              echo 'This part is allways executed '
            }
          }
        }
      }
    }
    stage('Pass') {
      steps {
        script {
          echo 'Stage 3'
            try{
              sh 'df -h'
            }catch(Exception ex){
              //Catching most of the Exceptions generated (not errors)
              echo "Exception:"+ex
            }catch(Error e){
              //Catching java errors (not exceptions)
              echo "Error:"+
            } catch (Throwable t){
              //Catching everything throwable (both exceptions and errors)
              echo "Throwable:"+t
            }finally{
              echo 'This part is allways executed '
            }
          }
        }
      }
    }
  }
