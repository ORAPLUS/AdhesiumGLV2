'use strict';

var clientDirectives = angular.module('clientDirectives',[]);

clientDirectives.directive('clientDirectives',function(){
	return {
		restrict: 'E',
		templateUrl: "pck_clients/directives/partials/clientSearch.html"
	}
});