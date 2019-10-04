import groovy.json.JsonSlurperClassic
@NonCPS
def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}

def call() {
  def file_exists = fileExists "dogs.json"
  if(!file_exists){
    dogs_query = "https://dog.ceo/api/breeds/list/all"
    response = httpRequest(dogs_query)
    dogs_json =  jsonParse(response.content)
    writeFile file: "dogs.json", text: groovy.json.JsonOutput.toJson(dogs_json)
  }

  dogs_json = readJSON file: "dogs.json"
  return this
}


def variantes(String breed){
  return dogs_json.message.get(breed)
}

def breedes(String variant){
  breedes = []
  dogs_json.message.collect({
      if(it.value.contains(variant)){
        breedes.add(it.key)
        echo "Found: "+variant+" "+it.key
      }
    })
    return breedes
}
def combo_exists(String breed, String variant){
  if (dogs_json.message.find{it.key==breed}.value.contains(variant)){exist=true}
  return exists
}
def all() {
  return dogs_json.message
}
