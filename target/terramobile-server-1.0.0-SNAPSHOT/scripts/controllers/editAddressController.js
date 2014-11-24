

angular.module('terramobileserver').controller('EditAddressController', function($scope, $routeParams, $location, AddressResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.address = new AddressResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Addresses");
        };
        AddressResource.get({AddressId:$routeParams.AddressId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.address);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.address.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Addresses");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Addresses");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.address.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});