'use strict';

/**
 * Controllers
 */

var controllers = 
angular.module('app.controllers', 
		      ['accueilController', 'sidebarController',
		       'manageClientsController','detailsClientController',
		       'manageContactsController','detailsContactController',
		       'manageProduitsController','detailsProduitController',
		       'managePaniersController','detailsPanierController',
			  ]
);
