
angular.module('terramobileserver').controller('NewPhotoController', function ($scope, $location, locationParser, PhotoResource , FormResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.photo = $scope.photo || {};
    
    $scope.formList = FormResource.queryAll(function(items){
        $scope.formSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.date
            });
        });
    });
    $scope.$watch("formSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.photo.form = {};
            $scope.photo.form.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Photos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        PhotoResource.save($scope.photo, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Photos");
    };
});