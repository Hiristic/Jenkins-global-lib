# Jenkins Pipeline Global Groovy Library  

Collection of Groovy scripts, Jenkins global variables and classes, shell scripts, gdsl file for IDEs and configurations.

## Why

To simplify automation and creation of Jenkins pipelines there is a need for a place to store and share common methods and scripts.

## Start

To learn more about how to create pipelines please read the official documentation[Pipelines](https://jenkins.io/doc/book/pipeline/getting-started/) and look at the demo projects stored in the demo folder. 

## Run

Global libraries are automatically imported to Jenkins so to access the global variables and classes in repository just write the name of the function and it will be directly accessable by Jenkins.

## Repo content
<pre>
|-jenkinsfiles    //This folder contains all job configuration files
  |---Demo        //This folder contains all demo examples
  |---Dev         //This folder contains jobs under construction or that will never be in production
  |---Prod        //This folder contians jods that are tested and ment to be run in production
  |---Test        //This folder contians jods that are used for verification
|-src           
  |---com
    |-----jenkins //This folder contians global Groovy classes that can be used in pipelines
|-vars            //This folder contians global Groovy variables that can be used in pipelines
|-resources       //This folder contians miscellaneous resources
</pre>