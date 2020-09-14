import org.json.JSONObject
def mapToJsonString(LinkedHashMap json_obj){
     return new JSONObject(json_obj).toString()
}
node {
    def map = [:]
    echo "What have we created"
    echo "Type: "+map.getClass()

    map = [key1: "value1", key2: "value2", key3: 42]

    //Add
    map['key4'] = 45
    map.put('key5', 55)
    
    echo "Get"
    echo "map.key2: "+map.key2

    echo "Remove key4"
    echo "Before remove"+map
    map.remove('key4')
    echo "After remove"+map

    echo "Iterate over each elemant in map"
    map.each{
        echo "it: "+it
    }

    echo "Find in map"
    echo "Contains key3 with value 42: "
    assert map.find{it.key == "key3"}.value == 42
    
    echo "What values are strings in map: "+map.collect{it.value instanceof String}
    echo "Strings: "+map.collect{if(it.value instanceof String){it.value}}//.each{if(it){it.value}}
    echo "FindAll strings: "+map.findAll{it.value instanceof String}.collect{it.value}
    def list = []
    map.collect{if(it.value instanceof String){list.add(it.value)}}
    echo "Strings list: "+list


    //Nested maps
    map = [1: [key1: "https", key2: "value2"],
           2: [key1: "ssh", key2: "value2"]]

    echo "Get single values"
    echo "map[2]['key1']: "+map[2]['key1']
    echo "map[2]['key2']: "+map[2]['key2']

    echo "Extracting all elements of a map"
    map.each{
        echo "it: "+it
        echo "it.key: "+it.key
        echo "it.value: "+it.value
        echo "key1: "+it.value.key1
        echo "key2: "+it.value.key2
    }

    echo "Add new values"
    map.put(3, [key1: "http", key2: "value2"])
    echo "Size: "+map.size()

    echo "Identical objecta are removed"
    map.put(2, [key1: "ssh", key2: "value2"])
    echo "Size: "+map.size()

    echo "Collect all key1 values: "+map.collect{it.value.key1}
    

    echo "Internal representation of maps and printing"

    echo "Using print on a map object returns"
    print map
    echo "Using echo on a map object returns"
    echo "echo map: "+map
    
    echo "Using print on a stringified map object returns"
    print map.toString()
    echo "Using echo on a stringified map object returns"
    echo "echo  "+map.toString()

    echo "Converting To Json Object an stringifying it"
    print mapToJsonString(map)
    echo "echo Json String: "+mapToJsonString(map)
    //Do this conversion before bushing data to elasticsearch
}
