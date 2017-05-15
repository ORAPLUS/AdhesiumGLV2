'use strict';

/**
 * App
 */

var app = angular.module('app',['ui.router','ui.bootstrap','ngFileUpload','app.controllers','app.services','app.directives']);

app.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider) {
	$urlRouterProvider.otherwise("/pck_views/accueil");
	$stateProvider
	.state("utils",{
		abstract: true,
		views:{
			"header":{
				templateUrl:'pck_views/header.html'
			},
			"search":{
				templateUrl:'pck_views/search.html'
			},
			"navbar":{
				templateUrl:'pck_views/navbar.html'
			},
			"sidebar":{
				templateUrl:'pck_views/sidebar.html',
				controller: 'sidebarController'
			},
			"footer":{
				templateUrl:'pck_views/footer.html'
			}
		}
	})
	.state("accueil",{
		url: '/pck_views/accueil',
		parent:'utils',	
		views:{
			"content@":{
				templateUrl:'pck_views/accueil.html',
				controller:'accueilController'
			}
		}
	})
	.state("display_manage_clients",{
		url: '/pck_clients/display_manage_clients',
		parent:'utils',	
		views:{
			"content@":{
				templateUrl:'pck_clients/display_manage_clients.html',
				controller: 'manageClientsController'
			}
		}
	})
	.state("display_details_client",{
		url: '/pck_clients/display_details_client/:idClient',
		parent:'utils',	
		views:{
			"content@":{
				templateUrl:'pck_clients/display_details_client.html',
				controller: 'detailsClientController'
			}
		}
	})
	.state("display_manage_contacts",{
		url: '/pck_contacts/display_manage_contacts',
		parent:'utils',	
		views:{
			"content@":{
				templateUrl:'pck_contacts/display_manage_contacts.html',
				controller: 'manageContactsController'
			}
		}
	})
	.state("display_details_contact",{
		url: '/pck_contacts/display_details_contact',
		parent:'utils',	
		views:{
			"content@":{
				templateUrl:'pck_contacts/display_details_contact.html',
				controller: 'detailsContactController'
			}
		}
	})
	.state("display_manage_produits",{
		url: '/pck_produits/display_manage_produits',
		parent:'utils',	
		views:{
			"content@":{
				templateUrl:'pck_produits/display_manage_produits.html',
				controller: 'manageProduitsController'
			}
		}
	})
	.state("display_details_produit",{
		url: '/pck_produits/display_details_produit',
		parent:'utils',	
		views:{
			"content@":{
				templateUrl:'pck_produits/display_details_produit.html',
				controller: 'detailsProduitController'
			}
		}
	});
}]);