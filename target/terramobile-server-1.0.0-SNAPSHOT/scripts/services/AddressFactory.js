angular.module('terramobileserver').factory('AddressResource', function($resource){
    var resource = $resource('rest/addresses/:AddressId',{AddressId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});