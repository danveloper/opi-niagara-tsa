'use strict';

angular.module('opiApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'restangular'
])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/season/seasons.html',
        controller: 'SeasonCtrl'
      })
	    .when('/locations', {
		   templateUrl: 'views/locations/locations.html',
		   controller: 'LocationCtrl'
	    })
      .otherwise({
        redirectTo: '/'
      });
  });
