'use strict';

angular.module('opiApp')
  .controller('LocationCtrl', function ($scope, $location, Season ) {
    $scope.locations = [
	    { id: '_1', name: 'Cabin'},
	    { id: '_2', name: 'Condo'}
    ]

    $scope.select = function(id) {
//		Season.setSelectedSeason(id)
	    alert('Location: ' + id)
    }

	$scope.back = function() {
		$location.url('/')
	}
  });
