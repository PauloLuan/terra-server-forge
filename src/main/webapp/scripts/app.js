'use strict';

angular.module('terramobileserver',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Addresses',{templateUrl:'views/Address/search.html',controller:'SearchAddressController'})
      .when('/Addresses/new',{templateUrl:'views/Address/detail.html',controller:'NewAddressController'})
      .when('/Addresses/edit/:AddressId',{templateUrl:'views/Address/detail.html',controller:'EditAddressController'})
      .when('/Forms',{templateUrl:'views/Form/search.html',controller:'SearchFormController'})
      .when('/Forms/new',{templateUrl:'views/Form/detail.html',controller:'NewFormController'})
      .when('/Forms/edit/:FormId',{templateUrl:'views/Form/detail.html',controller:'EditFormController'})
      .when('/Photos',{templateUrl:'views/Photo/search.html',controller:'SearchPhotoController'})
      .when('/Photos/new',{templateUrl:'views/Photo/detail.html',controller:'NewPhotoController'})
      .when('/Photos/edit/:PhotoId',{templateUrl:'views/Photo/detail.html',controller:'EditPhotoController'})
      .when('/Tasks',{templateUrl:'views/Task/search.html',controller:'SearchTaskController'})
      .when('/Tasks/new',{templateUrl:'views/Task/detail.html',controller:'NewTaskController'})
      .when('/Tasks/edit/:TaskId',{templateUrl:'views/Task/detail.html',controller:'EditTaskController'})
      .when('/Users',{templateUrl:'views/User/search.html',controller:'SearchUserController'})
      .when('/Users/new',{templateUrl:'views/User/detail.html',controller:'NewUserController'})
      .when('/Users/edit/:UserId',{templateUrl:'views/User/detail.html',controller:'EditUserController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
