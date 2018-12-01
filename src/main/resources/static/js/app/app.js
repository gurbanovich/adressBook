/**
 * 
 */

var app = angular.module('adressBookList', ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider
	.when('/list', {templateUrl: 'views/list.html', controller: 'AppCtrl'})
	.when('/read/:id', {templateUrl: 'views/read.html', controller: 'PersonCtrl', controllerAs:'ctrl'})
	.when('/create', {templateUrl: 'views/create.html', controller: 'CreateCtrl', controllerAs:'ctrl'})
	.otherwise({redirectTo: '/list'});
});


app.controller("AppCtrl", function($scope, $http) {
	var self = this;
	self.adressBookForms = [];
	self.adressBookForm = ({
		personId : "",
		firstName : "",
		secondname : "",
		phoneNumbers : [],
		emails : []
	});
	
    loadListPersons();
	
	function loadListPersons() {
	$http.get('/show_persons')
	 .then(function(result) {
	 $scope.adressBookForms = result.data;
	 },
	 function(result) {
		 console.error("Error: " + result.status + " : " + result.data);
	 });
	
	}
	
	$scope.removePerson = function(id) {
		$http.delete('/delete_person/' +id)
		.then(function(result) {
		 loadListPersons();
	    },
	    function(result) {
	        var data = result.data;
	        var status = result.status;
	        alert("Error: " + status + ":" + data);
	    });	   
	}
	
	$scope.showPerson = function(id) {
		document.location.replace("#/read/"+id)
		.then(function() {
			console.log("Person is chosen");
		}, 
		function(result) {
	        var data = result.data;
	        var status = result.status;
	        alert("Error: " + status + ":" + data);
	    });	
	} 
});

app.controller("PersonCtrl", function($scope, $http, $routeParams) {
var self = this;
self.adressBookForm = {
		 personId : $routeParams.id,
		firstName : "",
		secondname : "",
		workPlace : "",
		adress : "",
		comments : "",
		phoneNumbers : [],
		emails : []
	};

self.phoneNumbers = /^((8|\+)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}?[\d\, +]+$/; 
self.emails = /^[a-zA-Z0-9 ,.\-:@"()]*?$/;

	getPerson($routeParams.id);
		
	$scope.save = function(adressBookForm) {
		$http.put('/edit_person/' + self.adressBookForm.personId,
				 $scope.adressBookForm) 
		 .then(function() { 
			  document.location.replace("#/list");
			  console.log("person is saved successfully! ")
		  },
		  function() {
		  console.error("Error with saveng information");
		  });
		  
	};
	
	function getPerson(id) {
		 $http.get('/show_person/' + id)
	 .then(function(result) {
	 $scope.adressBookForm = result.data;
	 },
	 function(result) {
		 console.error("Error: " + result.status + " : " + result.data);
	 });
	};	
});

app.controller("CreateCtrl", function($scope, $http) {
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
	
	self.phoneNumbers = /^((8|\+)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}?[\d\, +]+$/; 
	self.emails = /^[a-zA-Z0-9 ,.\-:@"()]*?$/;
	
	$scope.submit = function() {
		 $http.post('/add_person', $scope.adressBookForm)
		 .then(function() { 
			  document.location.replace("#/list");
			  console.log("person is saved successfully! ");
		  },
		  function() {
		  console.error("Error saving information ");
		  });
	};
});