

angular.module('terramobileserver').controller('EditFormSchemaController', function($scope, $routeParams, $location, FormSchemaResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.formSchema = new FormSchemaResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/FormSchemas");
        };
        FormSchemaResource.get({FormSchemaId:$routeParams.FormSchemaId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.formSchema);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.formSchema.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/FormSchemas");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/FormSchemas");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.formSchema.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});