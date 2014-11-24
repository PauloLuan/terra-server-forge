

angular.module('terramobileserver').controller('EditTaskController', function($scope, $routeParams, $location, TaskResource , AddressResource, UserResource, FormResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.task = new TaskResource(self.original);
            AddressResource.queryAll(function(items) {
                $scope.addressSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.name
                    };
                    if($scope.task.address && item.id == $scope.task.address.id) {
                        $scope.addressSelection = labelObject;
                        $scope.task.address = wrappedObject;
                        self.original.address = $scope.task.address;
                    }
                    return labelObject;
                });
            });
            UserResource.queryAll(function(items) {
                $scope.userSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.name
                    };
                    if($scope.task.user && item.id == $scope.task.user.id) {
                        $scope.userSelection = labelObject;
                        $scope.task.user = wrappedObject;
                        self.original.user = $scope.task.user;
                    }
                    return labelObject;
                });
            });
            FormResource.queryAll(function(items) {
                $scope.formSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.date
                    };
                    if($scope.task.form && item.id == $scope.task.form.id) {
                        $scope.formSelection = labelObject;
                        $scope.task.form = wrappedObject;
                        self.original.form = $scope.task.form;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Tasks");
        };
        TaskResource.get({TaskId:$routeParams.TaskId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.task);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.task.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Tasks");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Tasks");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.task.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("addressSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.task.address = {};
            $scope.task.address.id = selection.value;
        }
    });
    $scope.$watch("userSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.task.user = {};
            $scope.task.user.id = selection.value;
        }
    });
    $scope.$watch("formSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.task.form = {};
            $scope.task.form.id = selection.value;
        }
    });
    
    $scope.get();
});