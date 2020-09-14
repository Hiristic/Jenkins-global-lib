import groovy.json.JsonSlurperClassic
@NonCPS
def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json) //Convert a Json string to an Json object
}
node {
    // Creating a json object string
    json_string = '{"user":"me","post_date":"2019-12-05T12:12:12","message":"something to say"}'
   
    json_obj = jsonParse(json_string)

    echo "json_string: "+json_string
    echo "json_obj: "+json_obj
}