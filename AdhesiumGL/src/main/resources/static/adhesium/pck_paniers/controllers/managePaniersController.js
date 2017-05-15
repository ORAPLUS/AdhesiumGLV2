'use strict';

var managePaniersController = angular.module('managePaniersController',[]);

managePaniersController.controller('managePaniersController', ['$scope','$rootScope','headerService','Upload', '$timeout','$http', 
	function($scope,$rootScope,headerService,Upload, $timeout,$http){
	/*Menu SideBar*/
	$rootScope.SideBarshow = 4;
	$rootScope.SubSideBarshow = 44;
	/*Begin Controller*/
	$scope.motCle = "";
	$scope.client = {};
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
					sweetAlert("Confirmation", "Votre Client a été modifié avec succès", "success");
				}, function(err) {
					sweetAlert("Error", err, "error");
					console.log(err.statusText);
				});
		}
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