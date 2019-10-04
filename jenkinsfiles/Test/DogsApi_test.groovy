import groovy.json.JsonSlurperClassic
@NonCPS
def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}

node ('master') {
  cleanWs()

    dogs_query = "https://dog.ceo/api/breeds/list/all"
    response = httpRequest(dogs_query)
    echo "Status: ${response.status}"
    dogs_json =  jsonParse(response.content)

  def variant = "english"
  def breed = "bulldog"

  echo "Whole reply: "+dogs_json.message
  echo "Print keyset: "+dogs_json.message.keySet()
  echo "Bulldog field: "+dogs_json.message.bulldog
  echo "Get breed field: "+dogs_json.message.get(breed)
  echo "Find and return key: "+dogs_json.message.find{ it.key == breed}
  echo "Find key and extract value: "+dogs_json.message.find{ it.key == breed}.value

  echo "all: "+dogsGet().all()
  echo "variants: "+dogsGet().variantes(breed)
  echo "breedes: "+dogsGet().breedes(variant)
  echo "combo exists: "+dogsGet().combo_exists(breed, variant)
}