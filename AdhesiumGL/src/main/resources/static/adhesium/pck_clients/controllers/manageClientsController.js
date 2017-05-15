'use strict';

var manageClientsController = angular.module('manageClientsController',[]);

manageClientsController.controller('manageClientsController', 
	['$scope','$rootScope','headerService','Upload', '$timeout','$http',
	function($scope,$rootScope,headerService,Upload, $timeout,$http){
	/*Menu SideBar*/
	$rootScope.SideBarshow = 2;
	$rootScope.SubSideBarshow = 22;
	/*Begin Controller*/
	$scope.motCle = "";
	$scope.client = {};
	$scope.contact = {};
	$scope.contacts = [];
	$scope.linkXampp = "http://localhost/temp/clients/";
	$scope.defaultImg = "default.png";
	$scope.client.logo = $scope.defaultImg;
	$scope.pageCourante = 0;
	$scope.pageCouranteText = 1;
	$scope.totalPages = 0;
	$scope.size = 10;
	$scope.pages = [];
	$scope.sortColumn = "idClient";
	$scope.$timeout = $timeout;
	$scope.sortOption = "ASC";
	$scope.showModalContact=false;
	
	$scope.orderby = function(clientOrder) {
		if ($scope.sortColumn == clientOrder && $scope.sortOption == "ASC") {
			$scope.sortColumn = clientOrder;
			$scope.sortOption = "DESC";
			$scope.getClients();
		} else {
			$scope.sortColumn = clientOrder;
			$scope.sortOption = "ASC";
			$scope.getClients();
		}
	}
	// Liste Des Clients
	$scope.listeDesClients = function() {
		$http.get(headerService.urlBase + "/clients/chercherClients?mc="
			+ $scope.motCle + "&sort=" + $scope.sortColumn + "&option=" + $scope.sortOption + "&page=" + (($scope.pageCourante <= 0) ? 0 : $scope.pageCourante) + "&size=" + $scope.size)
			.then(function(response) {
				$scope.clients = response.data.content;
				$scope.pages = new Array(response.data.totalPages);
				$scope.totalPages = response.data.totalPages;
			}, function(err) {
				sweetAlert("Error", err, "error");
				console.log(err);
			});
	}
	$scope.getClients = function() {
		$scope.pageCourante = 0;
		$scope.pageCouranteText = 1;
		$scope.listeDesClients();
	}
	$scope.gotoPage = function(p) {
		$scope.pageCourante = (p) < 0 ? 0 : ((p) < $scope.totalPages ? (p) : $scope.totalPages - 1);
		$scope.pageCouranteText = (p + 1) < 1 ? 1 : ((p + 1) <= $scope.totalPages ? (p + 1) : $scope.totalPages);
		$scope.listeDesClients();
	}
	$scope.nouveau = function() {
		$scope.client = {};
		$scope.client.logo = $scope.defaultImg;
		$scope.picFile =undefined;
		$('span.filename').text('Aucune image');
	}
	$scope.nouveauSearch = function() {
		$scope.motCle = "";
		$scope.getClients();
	}
	
	$scope.sauvgarde = function() {
		if (!$scope.client.idClient) {
			$http.post(headerService.urlBase + "/clients/", $scope.client)
				.then(function(response) {
					$scope.listeDesClients();
					//if($scope.contacts.length>0){
						$scope.addContactDB(response.data.idClient);
					//}
					$scope.nouveau();
					sweetAlert("Confirmation", "Votre Client a été ajouté avec succès", "success");
				}, function(err) {
					sweetAlert("Error", err, "error");
					console.log(err);
				});
		} else {
			$http.put(headerService.urlBase + "/clients/" + $scope.client.idClient, $scope.client)
				.then(function(response) {
					$scope.listeDesClients();
					$scope.nouveau();
					//if($scope.contacts.length>0){
						$scope.addContactDB(response.data.idClient);
					//}
					sweetAlert("Confirmation", "Votre Client a été modifié avec succès", "success");
				}, function(err) {
					sweetAlert("Error", err, "error");
					console.log(err.statusText);
				});
		}
	}
	$scope.addContactDB= function(idClient){
		$http.delete(headerService.urlBase + "/contacts/delContacts/" + idClient)
		.then(function(response) {
			$http.post(headerService.urlBase + "/contacts/"+ idClient, $scope.contacts);
			$scope.contacts=[];
		}, function(err) {
			console.log(err);
		});
	}
	$scope.editer = function(clientSel) {
		$scope.client = angular.copy(clientSel);
		$scope.client.nom = $scope.client.nom;
		$scope.client.telPortable = $scope.client.telPortable
		$scope.client.telFixe = $scope.client.telFixe;
		$scope.client.email = $scope.client.email;
		$scope.client.logo = $scope.client.logo;
		$scope.client.remarque = $scope.client.remarque;
		$scope.client.commentaire = $scope.client.commentaire;
		
		$http.get(headerService.urlBase + "/contacts/listContacts?idClient="+ $scope.client.idClient)
		.then(function(response) {
			$scope.contacts = response.data;
			});
	}
	$scope.supprimer = function(clientSel) {
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
			$http.delete(headerService.urlBase + "/clients/" + clientSel.idClient)
				.then(function(response) {
					$scope.listeDesClients();
					$scope.nouveau();
					swal("Confirmation", "Votre Client a été supprimé avec succès", "success");
				}, function(err) {
					sweetAlert("Error", err, "error");
					console.log(err);
				});
		});
	}
	$scope.addContact = function(){
		if($scope.indexContact == null)
		{
			$scope.contacts.push($scope.contact);
			$scope.showModalContact=false;
			$scope.contact = {};
			swal("Confirmation", "Votre Contact a été ajouté avec succès", "success");
		}
		else{
			$scope.contacts[$scope.indexContact] = $scope.contact;
			$scope.showModalContact=false;
			$scope.contact = {};
			$scope.indexContact = null;
			swal("Confirmation", "Votre Contact a été modifié avec succès", "success");
		}
	}
	$scope.removeContact = function($index){
		$scope.contacts.splice($index,1); 
		swal("Confirmation", "Votre Contact a été supprimé avec succès", "success");
	}
	$scope.editerContact = function($index,contactSel) {
		$scope.contact = angular.copy(contactSel);
		$scope.contact.nom = $scope.contact.nom;
		$scope.contact.telPortable = $scope.contact.telPortable
		$scope.contact.telFixe = $scope.contact.telFixe;
		$scope.contact.email = $scope.contact.email;
		$scope.indexContact = $index;
		$scope.showModalContact=true;
	}
	$scope.nouveauContact = function(){
		$scope.contact = {};
		$scope.indexContact = null;
	}
	$scope.closeContact = function(){
		$scope.contact = {};
		$scope.indexContact = null;
		$scope.showModalContact=false;
	}
	/* Begin upload image */
	 $scope.uploadPic = function(file) {
		 if(file){
		    file.upload = Upload.upload({
		      url: headerService.urlBase+'/api/clients',
		      data: {uploadFile: file},
		    });

		    file.upload.then(function (response) {
		      $timeout(function () {
		        file.result = response.data;
		        $scope.client.logo =  response.data[0];
		      });
		    }, function (response) {
		      if (response.status > 0)
		        $scope.errorMsg = response.status + ': ' + response.data;
		    });
		 }
		    }
	 $scope.uploadPicAnnuler = function(){
		 $scope.client.logo = $scope.defaultImg;
		 $scope.picFile =undefined;
		 $('span.filename').text('Aucune image');
	 }
	 
	/* End upload image */
}]);