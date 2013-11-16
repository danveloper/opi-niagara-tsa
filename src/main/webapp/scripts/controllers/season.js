'use strict';

angular.module('opiApp')
  .controller('SeasonCtrl', function ($scope, $location, Season ) {
    $scope.seasons = Season.getSeasons();

    $scope.select = function(id) {
		Season.setSelectedSeason(id)
    }
  });
