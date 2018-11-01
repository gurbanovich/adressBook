/**
 * 
 */

var app = angular.module("adressBookCreate", []);

app.controller("AppCtrl", function($scope, $http) {
	var self = this;
	self.adressBookForm = {
		firstName : "",
		secondname : "",
		workPlace : "",
		adress : "",
		comments : "",
		phoneNumbers : [],
		emails : []
	};

	$scope.submit = function() {
		$http.post('http://localhost:8080/add_person', $scope.adressBookForm)
				.then(_success, _error);
	};

});