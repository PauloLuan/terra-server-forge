

angular.module('terramobileserver').controller('EditFormController', function($scope, $routeParams, $location, FormResource , FormSchemaResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.form = new FormResource(self.original);
            FormSchemaResource.queryAll(function(items) {
                $scope.schemaSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.name
                    };
                    if($scope.form.schema && item.id == $scope.form.schema.id) {
                        $scope.schemaSelection = labelObject;
                        $scope.form.schema = wrappedObject;
                        self.original.schema = $scope.form.schema;
                    }
                    return labelObject;
                });
            });
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
    
    $scope.$watch("schemaSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.form.schema = {};
            $scope.form.schema.id = selection.value;
        }
    });
    
    $scope.get();
});