'use strict';

angular.module('opiApp')
  .service('Season', function Season($log, $location) {
		var seasons = [
			{ id: '_1', name: 'Fall 2013'},
			{ id: '_2', name: 'Summer 2014'}
		];

		var selectedSeasonId = {}

		return {
			getSeasons: function() {
				return seasons;
			},
			setSelectedSeason: function(id) {
				$log.info("Season Is set: " + id);
				selectedSeasonId = id;
				$location.url('/locations')
			}
		}
  });
