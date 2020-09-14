node {
    echo "Creating a Json object string"
    json_obj_string = '{"user":"me","post_date":"2019-12-05T12:12:12","message":"something to say"}'
  
    echo "Writing Json string to file"
    writeFile file: "test.json", text: json_obj_string

    echo "Read json file and get an Json object (list of lists)"
    read_json_obj = readJSON file: "test.json"

    try {
        echo "Try writing extracted Json object to file"
        writeFile file: "test2.json", text: read_json_obj
    } catch (Exception ex){
        echo "Writing Json object to file failed: "+ex
    }

    echo "Convert Json object to string before write"
    json_str = read_json_obj.toString()
    writeFile file: "test2.json", text: json_str

    echo "Comparing the different types"
    echo "Json object string: "+json_obj_string
    echo "Json string: "+json_str
    echo "Read Json object: "+read_json_obj

    if(json_obj_string.equals(json_str)){
        echo "Extracted data is equal to written data"
    }else{
        echo "Extracted data is not equal to written data"
        currentBuild.result = "FAILURE"
    }

    echo "Reading single Json object value"
    echo "Extract user field: "+read_json_obj.user
    echo "Extract post field: "+read_json_obj.post

    echo "Reading all Json object values"
    read_json_obj.each{
        echo "Each value: "+it
    }
}