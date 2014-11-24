angular.module('terramobileserver').factory('FormResource', function($resource){
    var resource = $resource('rest/forms/:FormId',{FormId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});