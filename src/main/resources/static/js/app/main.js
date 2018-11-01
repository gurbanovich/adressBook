/**
 * 
 */
var app = angular.module("adressBook", []);

app.controller("AppCtrl", function($scope, $http, id) {
	$scope.adressBookForms = [];
	$scope.adressBookForm = {};
	/*	personId : "",
		firstName : "",
		secondname : "",
		workPlace: "",
		adress: "",
		comments: "",
		phoneNumbers : "",
		emails : ""
	});*/
	
	
	$http.get('http://localhost:8080/show_person').success(function(data) {
		$scope.adressBookForm = data;
		});
	/*then(
            function (adressBookForm) {
                self.adressBookForm = adressBookForm}, _error);*/
	
	/*
	
	
		
		
		
		
		
	
	/*firstName: 'firstName',
	secondname: 'secondname',
	c
	phoneNumbers: 'phoneNumbers',
	emails: 'emails'
	// <td>{{person.personId}}</td>
	// <td>{{person.firstName}}</td>
	// <td>{{person.secondname}}</td>
	// <td>{{person.workPlace}}</td>
	// <td>{{person.adress}}</td>
	// <td>{{person.comments}}</td>
	// <td>{{person.phoneNumbers}}</td>
	// <td>[[phoneNumber.number]]</td>

	// <td>{{person.emails}}</td>
	// <td>[[email.email]]</td>*/
});
