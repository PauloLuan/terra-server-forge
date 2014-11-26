
angular.module('terramobileserver').controller('NewFormController', function ($scope, $location, locationParser, FormResource , FormSchemaResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.form = $scope.form || {};
    
    $scope.schemaList = FormSchemaResource.queryAll(function(items){
        $scope.schemaSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.name
            });
        });
    });
    $scope.$watch("schemaSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.form.schema = {};
            $scope.form.schema.id = selection.value;
        }
    });
    

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