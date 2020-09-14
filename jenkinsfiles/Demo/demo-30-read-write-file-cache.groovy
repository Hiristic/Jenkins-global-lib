node {
  def file_exists = fileExists "dogs.json"
  if(!file_exists){
    dogs_query = "https://dog.ceo/api/breeds/list/all"
    response = httpRequest(dogs_query)
    writeFile file: "dogs.json", text: response.content
  }
  dogs_json = readJSON file: "dogs.json"
  echo "File content: "+dogs_json
}