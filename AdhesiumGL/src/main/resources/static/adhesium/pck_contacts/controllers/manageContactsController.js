'use strict';

var manageContactsController = angular.module('manageContactsController',[]);

manageContactsController.controller('manageContactsController', ['$scope','$rootScope','headerService','Upload', '$timeout','$http', 
	function($scope,$rootScope,headerService,Upload, $timeout,$http){
	/*Menu SideBar*/
	$rootScope.SideBarshow = 3;
	$rootScope.SubSideBarshow = 33;
	/*Begin Controller*/
	$scope.motCle = "";
	$scope.contact = {};
	$scope.pageCourante = 0;
	$scope.pageCouranteText = 1;
	$scope.totalPages = 0;
	$scope.size = 10;
	$scope.pages = [];
	$scope.sortColumn = "idContact";
	$scope.$timeout = $timeout;
	$scope.sortOption = "ASC";


	$scope.orderby = function(contactOrder) {
		if ($scope.sortColumn == contactOrder && $scope.sortOption == "ASC") {
			$scope.sortColumn = contactOrder;
			$scope.sortOption = "DESC";
			$scope.getContacts();
		} else {
			$scope.sortColumn = contactOrder;
			$scope.sortOption = "ASC";
			$scope.getContacts();
		}
	}
	// Liste Des Contacts
	$scope.listeDesContacts = function() {
		$http.get(headerService.urlBase + "/contacts/chercherContacts?mc="
			+ $scope.motCle + "&sort=" + $scope.sortColumn + "&option=" + $scope.sortOption + "&page=" + (($scope.pageCourante <= 0) ? 0 : $scope.pageCourante) + "&size=" + $scope.size)
			.then(function(response) {
				$scope.contacts = response.data.content;
				$scope.pages = new Array(response.data.totalPages);
				$scope.totalPages = response.data.totalPages;
			}, function(err) {
				sweetAlert("Error", err, "error");
				console.log(err);
			});
	}
	$scope.getContacts = function() {
		$scope.pageCourante = 0;
		$scope.pageCouranteText = 1;
		$scope.listeDesContacts();
	}
	$scope.gotoPage = function(p) {
		$scope.pageCourante = (p) < 0 ? 0 : ((p) < $scope.totalPages ? (p) : $scope.totalPages - 1);
		$scope.pageCouranteText = (p + 1) < 1 ? 1 : ((p + 1) <= $scope.totalPages ? (p + 1) : $scope.totalPages);
		$scope.listeDesContacts();
	}
	$scope.nouveau = function() {
		$scope.contact = {};
	}
	$scope.nouveauSearch = function() {
		$scope.motCle = "";
		$scope.getContacts();
	}
	
	$scope.sauvgarde = function() {
		if (!$scope.contact.idContact) {
			$http.post(headerService.urlBase + "/contacts/", $scope.contact)
				.then(function(response) {
					$scope.listeDesContacts();
					$scope.nouveau();
					sweetAlert("Confirmation", "Votre Contact a été ajouté avec succès", "success");
				}, function(err) {
					sweetAlert("Error", err, "error");
					console.log(err);
				});
		} else {
			$http.put(headerService.urlBase + "/contacts/" + $scope.contact.idContact, $scope.contact)
				.then(function(response) {
					$scope.listeDesContacts();
					$scope.nouveau();
					sweetAlert("Confirmation", "Votre Contact a été modifié avec succès", "success");
				}, function(err) {
					sweetAlert("Error", err, "error");
					console.log(err.statusText);
				});
		}
	}
	$scope.editer = function(contactSel) {
		$scope.contact = angular.copy(contactSel);
		$scope.contact.nom = $scope.contact.nom;
		$scope.contact.telPortable = $scope.contact.telPortable
		$scope.contact.telFixe = $scope.contact.telFixe;
		$scope.contact.email = $scope.contact.email;
	}
	$scope.supprimer = function(contactSel) {
		swal({
			title : "Confirmation",
			text : "Voulez vous vraiment supprimer?",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Confirmer",
			cancelButtonText : "Annuler",
			closeOnConfirm : false
		}, function() {
			$http.delete(headerService.urlBase + "/contacts/" + contactSel.idContact)
				.then(function(response) {
					$scope.listeDesContacts();
					$scope.nouveau();
					swal("Confirmation", "Votre Contact a été supprimé avec succès", "success");
				}, function(err) {
					sweetAlert("Error", err, "error");
					console.log(err);
				});
		});
	}
	//Charge client recherche
	$http.get(headerService.urlBase + "/clients/chercherClients").then(function(response){
	    	$scope.clients = response.data.content;
	});
	
}]);