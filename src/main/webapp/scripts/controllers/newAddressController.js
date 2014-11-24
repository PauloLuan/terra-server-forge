
angular.module('terramobileserver').controller('NewAddressController', function ($scope, $location, locationParser, AddressResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.address = $scope.address || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Addresses/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        AddressResource.save($scope.address, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Addresses");
    };
});