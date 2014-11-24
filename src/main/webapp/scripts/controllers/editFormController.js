

angular.module('terramobileserver').controller('EditFormController', function($scope, $routeParams, $location, FormResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.form = new FormResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Forms");
        };
        FormResource.get({FormId:$routeParams.FormId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.form);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.form.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Forms");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Forms");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.form.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});