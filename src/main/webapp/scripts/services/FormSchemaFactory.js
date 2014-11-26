angular.module('terramobileserver').factory('FormSchemaResource', function($resource){
    var resource = $resource('rest/formschemas/:FormSchemaId',{FormSchemaId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});