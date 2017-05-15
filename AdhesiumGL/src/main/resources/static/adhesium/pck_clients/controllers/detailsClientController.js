'use strict';

var detailsClientController = angular.module('detailsClientController',[]);

detailsClientController.controller('detailsClientController', 
		['$scope','$rootScope','headerService','Upload', '$timeout','$http', '$stateParams','$anchorScroll',
	    function($scope,$rootScope,headerService,Upload, $timeout,$http,$stateParams,$anchorScroll){
	/*Menu SideBar*/
	$rootScope.SideBarshow = 2;
	$rootScope.SubSideBarshow = 222;
	$anchorScroll();
	/*Begin Controller*/
	$scope.client = {};
	$scope.contacts = [];
	$scope.linkXampp = "http://localhost/temp/clients/";
	
	//Charge Page on Load
	$scope.chargePage= function(idClient){
		if(idClient){
		    $scope.showModal = true;
			$http.get(headerService.urlBase + "/clients/"+ idClient)
					.then(function(response) {
						$scope.client = response.data;
					}, function(err) {
						sweetAlert("Error", err, "error");
						console.log(err);
					});
			
			$http.get(headerService.urlBase + "/contacts/listContacts?idClient="+ idClient)
			.then(function(response) {
				$scope.contacts = response.data;
				});
		}else{
			$scope.showModal = false;
		}
	}
	//Call chargePage
	$scope.chargePage($stateParams.idClient);
	//Charge client recherche
	$http.get(headerService.urlBase + "/clients/chercherClients").then(function(response){
	    	$scope.clients = response.data.content;
	});
	$scope.onSelect = function ($item, $model, $label) {
	    $scope.chargePage($item.idClient);
	};
}]);