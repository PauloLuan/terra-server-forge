
angular.module('terramobileserver').controller('NewTaskController', function ($scope, $location, locationParser, TaskResource , AddressResource, UserResource, FormResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.task = $scope.task || {};
    
    $scope.addressList = AddressResource.queryAll(function(items){
        $scope.addressSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.name
            });
        });
    });
    $scope.$watch("addressSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.task.address = {};
            $scope.task.address.id = selection.value;
        }
    });
    
    $scope.userList = UserResource.queryAll(function(items){
        $scope.userSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.name
            });
        });
    });
    $scope.$watch("userSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.task.user = {};
            $scope.task.user.id = selection.value;
        }
    });
    
    $scope.formList = FormResource.queryAll(function(items){
        $scope.formSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.schema
            });
        });
    });
    $scope.$watch("formSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.task.form = {};
            $scope.task.form.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Tasks/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        TaskResource.save($scope.task, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Tasks");
    };
});