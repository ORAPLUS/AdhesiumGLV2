'use strict';

/**
 * SideBar Controller
 */
 var sidebarController = angular.module('sidebarController',[]);

 sidebarController.controller('sidebarController', ['$scope','$rootScope','headerService', function($scope,$rootScope,headerService){

	$rootScope.SideBarshow = 1;
 	
 	$rootScope.getSideBar = function(index){
 		$rootScope.SideBarshow = index;
 		$rootScope.SubSideBarshow = null;
 	};

 	$rootScope.getSubSideBar = function(index,sindex){
 		$rootScope.SideBarshow = index;
 		$rootScope.SubSideBarshow = sindex;
 	};

 }]);