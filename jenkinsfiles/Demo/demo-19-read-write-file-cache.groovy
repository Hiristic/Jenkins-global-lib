import groovy.json.JsonSlurperClassic
@NonCPS
def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}

node {
  def file_exists = fileExists "dogs.json"
  if(!file_exists){
    dogs_query = "https://dog.ceo/api/breeds/list/all"
    response = httpRequest(dogs_query)
    dogs_json =  jsonParse(response.content)
    writeFile file: "dogs.json", text: groovy.json.JsonOutput.toJson(dogs_json)
  }

  dogs_json = readJSON file: "dogs.json"

  echo "File content"+dogs_json
}