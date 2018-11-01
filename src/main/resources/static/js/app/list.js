/**
 * 
 */
var app = angular.module("adressBookList", []);

app.controller("AppCtrl", function($scope, $http) {
	var self = this;
	self.adressBookForms = [];
	self.adressBookForm = ({
		personId : "",
		firstName : "",
		secondname : "",
		phoneNumbers : "",
		emails : ""
	});
	
	$http.get('http://localhost:8080/show_persons').success(function(data) {
		$scope.adressBookForms = data;
	});

	
	$scope.loadListPersons = function() {
	$http.get('http://localhost:8080/show_persons').success(function(data) {
		$scope.adressBookForms = data;
	});
	}
	
	$scope.removePerson = function(id) {
        $http.delete('http://localhost:8080/delete_person/' + id, self.adressBookForm.personId)
        .then($http.get('http://localhost:8080/show_persons'), _error);   
	}
	
	$scope.showPerson = function(id) {
	
		
		
		$http.get('http://localhost:8080/show_person/' + id, self.adressBookForm.personId)
        .then(_success, _error);   
		;
	}

   
});
