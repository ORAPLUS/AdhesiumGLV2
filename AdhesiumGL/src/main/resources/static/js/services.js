'use strict';

/**
 * Services
 */

var services = angular.module('app.services', []);

services.service('headerService',function(){
  this.values = {};
  this.urlBase = "http://localhost:8080";

  this.getValues = function () {
    return this.values.title;
  };

  this.setValues = function (title) {
    this.values.title = title;
  };

});