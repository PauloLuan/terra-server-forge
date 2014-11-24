angular.module('terramobileserver').factory('PhotoResource', function($resource){
    var resource = $resource('rest/photos/:PhotoId',{PhotoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});