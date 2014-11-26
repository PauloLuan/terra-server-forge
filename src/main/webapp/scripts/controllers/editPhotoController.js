

angular.module('terramobileserver').controller('EditPhotoController', function($scope, $routeParams, $location, PhotoResource , FormResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.photo = new PhotoResource(self.original);
            FormResource.queryAll(function(items) {
                $scope.formSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.date
                    };
                    if($scope.photo.form && item.id == $scope.photo.form.id) {
                        $scope.formSelection = labelObject;
                        $scope.photo.form = wrappedObject;
                        self.original.form = $scope.photo.form;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Photos");
        };
        PhotoResource.get({PhotoId:$routeParams.PhotoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.photo);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.photo.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Photos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Photos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.photo.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("formSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.photo.form = {};
            $scope.photo.form.id = selection.value;
        }
    });
    
    $scope.get();
});