
angular.module('terramobileserver').controller('NewFormSchemaController', function ($scope, $location, locationParser, FormSchemaResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.formSchema = $scope.formSchema || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/FormSchemas/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        FormSchemaResource.save($scope.formSchema, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/FormSchemas");
    };
});