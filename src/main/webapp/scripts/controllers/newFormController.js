
angular.module('terramobileserver').controller('NewFormController', function ($scope, $location, locationParser, FormResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.form = $scope.form || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Forms/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        FormResource.save($scope.form, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Forms");
    };
});